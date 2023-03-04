package com.kakao_szbot.cmd;

import static com.kakao_szbot.KakaoNotificationListener.KakaoSendReply;
import static com.kakao_szbot.KakaoNotificationListener.getSbn;
import static com.kakao_szbot.cmd.MainCommandChecker.checkCommnadList;
import static com.kakao_szbot.lib.CommonLibrary.findNum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommandInvest {
    public final static String TAG = "CommandInvest";

    public static int INVEST_END_TIME_MIN = 15;
    public static int INVEST_SHIFT_TIME_MIN = 1;
    public static int INVEST_SEED_MONEY = 20000;

    public static int invest_goods_index = 0;
    public static int invest_game_start = 0;
    public static String[] invest_goods = {
            "우마무스메 피규어", "포켓몬 피규어", "버튜버 피규어", "앙스타 피규어", "모노가타리 피규어",
            "토르 피규어", "안한수 피규어", "러브라이브 피규어", "봇치 피규어", "아냐 피규어"};
    public static int invest_goods_price = 1000;
    public static int invest_player_num = 0;
    public static List<String> invest_player = new ArrayList<String>();
    public static List<Integer> invest_money = new ArrayList<Integer>();
    public static List<Integer> invest_purchase = new ArrayList<Integer>();
    public static List<Integer> invest_purchase_num = new ArrayList<Integer>();

    public static String[] INVEST_PURCHASE_CMD = {"굿즈"};
    public static String[] INVEST_BUY_CMD = {"구매", "산다", "사줘", "살게", "사겠", "매수"};
    public static String[] INVEST_SELL_CMD = {"판매", "판다", "팔아", "팔게", "팔겠", "매도"};
    public static String[] INVEST_STATUS_CMD = {"현황", "상황", "순위"};

    private int changePrice(double price) {
        Random random = new Random();
        int up_down_rand = random.nextInt(CommandList.RAND_MAX);
        int price_rand = random.nextInt(CommandList.RAND_MAX);
        double calc_price;

        if (price < 300) {
            if (up_down_rand > (CommandList.RAND_MAX / 2)) {
                price += price_rand;
            }
            else {
                price_rand %= 300;
                price -= price_rand;
            }
        }
        else {
            calc_price = price / 100;

            if (up_down_rand > (CommandList.RAND_MAX / 2)) {
                calc_price *= (price_rand % 100);
                price += calc_price;
            }
            else {
                calc_price *= (price_rand % 50);
                price -= calc_price;
            }
        }

        price = Math.ceil(price);

        return (int)price;
    }

    public String investMessage(String msg) throws InterruptedException {
        String result = null;
        String emoji = null;

        if (invest_game_start != 0) {
            result = "이미 굿즈 투자 게임 진행중입니다";
            return result;
        }
        invest_game_start = 1;

        Random random = new Random();
        int rand = random.nextInt(CommandList.RAND_MAX);

        int play_time = 0;
        int next_goods_index = 0;
        int prev_goods_price = 0;
        int prev_percent = 0;

        invest_goods_price = 1000;
        invest_player_num = 0;
        invest_player.clear();
        invest_money.clear();
        invest_purchase.clear();
        invest_purchase_num.clear();
        invest_goods_index = rand % invest_goods.length;

        /* introduction investment game */
        result = "[천하제일 굿즈 투자 게임]\n"
                + " - 적당한 시기에 굿즈를 구매와 판매를 반복해서 부자가 되세요!\n"
                + " - 명령어는 [쿄코 굿즈 N개 구매], [쿄코 굿즈 N개 판매], [쿄코 굿즈 순위] 로 가능합니다.\n"
                + " - 굿즈의 가격은 " + INVEST_SHIFT_TIME_MIN + "분마다 변경됩니다.\n"
                + " - 지금부터 " + INVEST_END_TIME_MIN + "분동안 진행됩니다.\n"
                + " - 초기자금은 모두 " + INVEST_SEED_MONEY + "원으로 시작합니다.\n"
                + " - 거래 횟수가 1번이라도 있어야 참가처리가 됩니다.\n"
                + " - 재미로만 즐겨주세요~ 행운을 빕니다!";
        KakaoSendReply(result, getSbn());

        /* playing investment game */
        while (play_time <= INVEST_END_TIME_MIN) {
            prev_goods_price = invest_goods_price;
            invest_goods_price = changePrice(invest_goods_price);
            if (invest_goods_price <= 0) {
                prev_goods_price = 1000;
                invest_goods_price = changePrice(1000);
                next_goods_index = (invest_goods_index + 1) % invest_goods.length;


                for (int i=0; i < invest_purchase.size(); i++) {
                    invest_purchase.set(i, 0);
                }

                result = invest_goods[invest_goods_index]
                        + "가 상장 폐지 되었습니다. \uD83E\uDDE8\n"
                        + invest_goods[next_goods_index]
                        + " 매물이 새로 올라왔습니다.";
                KakaoSendReply(result, getSbn());

                invest_goods_index = next_goods_index;
            }

            prev_percent = ((invest_goods_price * 100) / prev_goods_price) - 100;
            if (prev_percent < 0) {
                emoji = "\uD83C\uDFA2";
            } else {
                emoji = "\uD83C\uDF89";
            }

            result = "\uD83D\uDE4F [굿즈 시세 현황] \uD83D\uDE4F\n - "
                    + invest_goods[invest_goods_index] + ": "
                    + invest_goods_price + "원 ("
                    + prev_percent + "%)\n"
                    + " - 기존 가격: " + prev_goods_price + "원 " + emoji;
            KakaoSendReply(result, getSbn());
            if (invest_goods_price < 300) {
                result = " * 투자 주의! 상폐 위험!\n   But, 대 상승 확률 업!";
                KakaoSendReply(result, getSbn());
            }

            if ((play_time + INVEST_SHIFT_TIME_MIN) == INVEST_END_TIME_MIN) {
                result = "곧 게임이 종료됩니다! (최후의 1턴 남음!)\n"
                        + "남은 굿즈는 다음 시세대로 처분되어 계산됩니다.";
                KakaoSendReply(result, getSbn());
            }

            if ((play_time + INVEST_SHIFT_TIME_MIN) > INVEST_END_TIME_MIN) {
                break;
            }

            Thread.sleep(INVEST_SHIFT_TIME_MIN * 1000 * 60);
            play_time += INVEST_SHIFT_TIME_MIN;
        }

        /* end investment game */
        String result_msg = "\n";
        int best_price = 0;
        int best_player_index = 0;
        for (int i=0; i < invest_player.size(); i++) {
            invest_money.set(i, (invest_money.get(i) + (invest_purchase.get(i) * invest_goods_price)));
            result_msg += " - " + invest_player.get(i) + "님: "
                    + invest_money.get(i) + "원 (거래 횟수: "
                    + invest_purchase_num.get(i) + ")\n";

            if (best_price < invest_money.get(i)) {
                best_price = invest_money.get(i);
                best_player_index = i;
            }
        }

        result = "[천하제일 굿즈 투자 게임 결과]"
                + result_msg
                + "\n * " + invest_player.get(best_player_index) + "님 "
                + invest_money.get(best_player_index) + "원으로 우승하셨습니다. 축하드립니다!";

        invest_game_start = 0;

        return result;
    }

    private String printInvestStatus() {
        String result = null;
        String result_msg = "";
        int temp = 0;

        for (int i=0; i < invest_player.size(); i++) {
            temp = invest_money.get(i) + (invest_purchase.get(i) * invest_goods_price);
            result_msg += "\n - " + invest_player.get(i) + "님: "
                    + temp + "원";
        }

        result = "[예상 자산 현황 (현시세 기준 판매시)]" + result_msg;
        return result;
    }



    private int findInvestPlayer(String sender) {
        int player_index = -1;

        for (int i=0; i < invest_player.size(); i++) {
            if (invest_player.get(i).equals(sender)) {
                player_index = i;
            }
        }

        if (player_index < 0) {
            player_index = invest_player_num++; // need lock
            invest_player.add(sender);
            invest_money.add(INVEST_SEED_MONEY);
            invest_purchase.add(0);
            invest_purchase_num.add(0);
        }

        return player_index;
    }

    public String investPurchaseMessage(String msg, String sender) {
        String result = null;
        if (invest_game_start == 0) return result;

        int player_index;
        int goods_num = 1;

        if (checkCommnadList(msg, INVEST_BUY_CMD) == 0) {
            player_index = findInvestPlayer(sender);
            goods_num = findNum(msg);
            if (goods_num == 0) goods_num = 1;

            if (invest_money.get(player_index) < (invest_goods_price * goods_num)) {
                result = sender + "님 "
                        + "굿즈 구매 금액(" + (invest_goods_price * goods_num) + "원)이 부족합니다.\n"
                        + " - 자금 현황: " + invest_money.get(player_index) + "원\n"
                        + " - 굿즈 갯수 현황: " + invest_purchase.get(player_index) + "개";
                return result;
            }

            invest_purchase_num.set(player_index, invest_purchase_num.get(player_index) + 1);
            invest_money.set(player_index, invest_money.get(player_index) - (invest_goods_price * goods_num));
            invest_purchase.set(player_index, invest_purchase.get(player_index) + goods_num);

            result = sender + "님 "
                    + goods_num + "개(시세: " + invest_goods_price + "원) 구매 완료.\n"
                    + " - 자금 현황: " + invest_money.get(player_index) + "원\n"
                    + " - 굿즈 갯수 현황: " + invest_purchase.get(player_index) + "개";
            return result;
        }

        if (checkCommnadList(msg, INVEST_SELL_CMD) == 0) {
            player_index = findInvestPlayer(sender);
            goods_num = findNum(msg);
            if (goods_num == 0) goods_num = 1;

            if (invest_purchase.get(player_index) < goods_num) {
                result = sender + "님 "
                        + "굿즈 판매 갯수(" + goods_num + "개)가 부족합니다.\n"
                        + " - 자금 현황: " + invest_money.get(player_index) + "원\n"
                        + " - 굿즈 갯수 현황: " + invest_purchase.get(player_index) + "개";
                return result;
            }

            invest_purchase_num.set(player_index, invest_purchase_num.get(player_index) + 1);
            invest_purchase.set(player_index, invest_purchase.get(player_index) - goods_num);
            invest_money.set(player_index, invest_money.get(player_index) + (invest_goods_price * goods_num));

            result = sender + "님 "
                    + goods_num + "개(시세: " + invest_goods_price + "원) 판매 완료.\n"
                    + " - 자금 현황: " + invest_money.get(player_index) + "원\n"
                    + " - 굿즈 갯수 현황: " + invest_purchase.get(player_index) + "개";

            return result;
        }

        if (checkCommnadList(msg, INVEST_STATUS_CMD) == 0) {
            result = printInvestStatus();
            return result;
        }

        return result;
    }
}
