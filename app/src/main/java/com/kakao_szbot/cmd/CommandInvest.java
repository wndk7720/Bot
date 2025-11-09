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

    public static int INVEST_END_TIME_MIN = 480;
    public static int INVEST_SHIFT_TIME_MIN = 30;
    public static int INVEST_SEED_MONEY = 30000;
    public static int INVEST_TAX_SEPARATE_PRICE = 5000;
    public static int INVEST_DELISTED_PRICE = 200;

    public static int invest_goods_index = 0;
    public static int invest_game_start = 0;
    public static String[] invest_goods = {
            "아베무지카 피규어", "부덕의 길드 피규어"};
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
    public static String[][] INVEST_UP_INFORMATION_PRINT_CONTENTS = {
            {
                "\uD83D\uDCE3",
                "\uD83C\uDFBD",
                "✈️",
                "\uD83D\uDC81\u200D♀️",
                "\uD83C\uDFAC",
                "\uD83E\uDD14",
                "\uD83D\uDE42",
                "\uD83C\uDF86",
                "\uD83D\uDCA1",
                "\uD83E\uDD64"
            },
            {
                "SNS, 트위터, 유튜브 등을 통한 2차 창작 활동의 급증이 굿즈 수요에 영향을 주고 있으며, 특정 굿즈가 밈화되며 가격이 상승 가능성이 관찰되고 있습니다.\n",
                "카페, 브랜드, 의류업체 등과의 콜라보 굿즈는 현장 구매 한정 또는 수량 제한으로 인해, 이차 시장에서 높은 프리미엄이 형성될 가능성이 보입니다.\n",
                "어느 날 외국인이 \"이 캐릭터... SO CUTE!!!\" 하더니 20개씩 구매해 갔습니다. 해외 직구 수요 폭발 할수도?\n",
                "유명 성우의 출연 및 성우 이벤트 참여 소식이 전해져 해당 캐릭터 굿즈의 수요가 증가하는 모습을 보이고 있습니다.\n",
                "공식 후속작 제작 소식이 발표되면서, 이전 시즌의 관련 굿즈 가격이 재평가될 수 있는 기회로 보입니다.\n",
                "방영 중인 애니메이션의 화제성 증가, 높은 시청률, 또는 완결 후 입소문을 통한 재조명이 되고있습니다.\n",
                "유튜브 리뷰어들이 해당 굿즈를 집중적으로 소개하면서, 구매욕구가 급증하는 트렌드가 형성되고 있습니다.\n",
                "한정판 굿즈가 출시되자마자 구매 대기열이 폭발, 서버가 잠시 다운되기도 했습니다.\n",
                "갑자기 “유레카! 이 굿즈가 이렇게 인기 있는 이유를 알았다!” 라고, 한 팬이 말했습니다.\n",
                "“커피 한 잔과 굿즈 한 개, 이 조합이 이렇게 폭발적인 인기를 끌 줄 몰랐어요!”라는 팬들의 인증샷이 SNS를 강타 중입니다.\n"
            }
    };
    public static String[][] INVEST_DOWN_INFORMATION_PRINT_CONTENTS = {
            {
                "\uD83E\uDD14",
                "\uD83E\uDEE0",
                "\uD83E\uDD76",
                "\uD83D\uDE4A",
                "\uD83D\uDC7B",
                "\uD83D\uDC7A",
                "\uD83D\uDE31",
                "\uD83C\uDF0A",
                "\uD83C\uDCCF",
                "☣️"
            },
            {
                "거창한 사건도, 충격적인 논란도 없이 평범한 일상이 지나가고 있습니다.\n",
                "작품의 완결 이후 관심 감소, 혹평, 스토리 전개 논란 등으로 인해 관련 수요가 감소 예정으로 보입니다.\n",
                "무분별한 굿즈 출시, 중복 디자인, 품질 저하 등으로 소비자 피로도가 높아지고 있어, 브랜드의 타격이 예상됩니다.\n",
                "3세트, 5세트, 10세트를 질러도 계속 같은 캐릭터만 나옵니다. 해당 캐릭터 굿즈를 대량 방출하여 시세하락이 예상됩니다.\n",
                "작가, 성우, 제작사 관계자의 스캔들, 범죄, 부적절한 발언 등이 드러나, 굿즈 가치도 급속히 하락할 수 있습니다.\n",
                "긴 생머리로 인기 있던 캐릭터가 갑작스레 단발로 변신하면서, '롱헤어 시절'의 인기가 급감했습니다. 팬들은 “이건 다른 사람”이라며 지갑을 닫기 시작했습니다.\n",
                "장기간의 침체기와 무관심 속에, 관련 굿즈의 인기가 서서히 하락하고 있습니다.\n",
                "팬덤 내 불화와 논란이 일어나면서 굿즈 시장에 불안한 파도가 일고 있습니다.\n",
                "반복되는 실망 속에, 이 굿즈가 마지막이라는 인식이 확산되며 구매 의욕이 급감하고 있습니다.\n",
                "“굿즈 바이러스 경보 발령!” 팬들이 너무 많이 사서 ‘재고 바이러스’가 돌고 있어 가격 하락 우려 중입니다.\n"
            }
    };

    private int determinatePriceProbability() {
        Random random = new Random();
        int proba = random.nextInt(60) + 20;
        return proba;
    }

    private int determinateDepthProbability(int min) {
        Random random = new Random();
        int proba = random.nextInt(50) + (min * 5);
        return proba;
    }

    private String printProbability(String result, int rise_proba, int depth_proba) {
        Random random = new Random();
        int index = random.nextInt(10) % 10;

        String content = "";
        if (rise_proba > 50) {
            content = INVEST_UP_INFORMATION_PRINT_CONTENTS[0][index] + " [다덕 정보 통통] " + INVEST_UP_INFORMATION_PRINT_CONTENTS[0][index] + "\n"
                    + INVEST_UP_INFORMATION_PRINT_CONTENTS[1][index];
        } else {
            content = INVEST_DOWN_INFORMATION_PRINT_CONTENTS[0][index] + " [다덕 정보 통통] " + INVEST_DOWN_INFORMATION_PRINT_CONTENTS[0][index] + "\n"
                    + INVEST_DOWN_INFORMATION_PRINT_CONTENTS[1][index];
        }

        result += "\n\n"
                + content
                + " - 상승 확률: " + rise_proba + "% (Up or Down)\n"
                + " - 변동성 높을 확률: " + depth_proba + "%\n  (변동성이 높을 경우 30~99%로 굿즈가격 변화)";

        return result;
    }

    private int changePrice(double price, int rise_proba, int depth_proba) {
        Random random = new Random();
        int up_down_rand = random.nextInt(CommandList.RAND_MAX);
        int depth_rand = random.nextInt(CommandList.RAND_MAX);
        int price_rand = 0;
        double price_one_percent = price / 100;

        int rp = (CommandList.RAND_MAX * rise_proba) / 100;
        int pp = (CommandList.RAND_MAX * depth_proba) / 100;


        if (up_down_rand < rp) {
            if (depth_rand < pp) {
                price_rand = random.nextInt(70) + 30;
            } else {
                price_rand = random.nextInt(30);
            }
            price_one_percent *= price_rand;
            price += price_one_percent;
        }
        else {
            if (depth_rand < pp) {
                if (price < INVEST_DELISTED_PRICE) {
                    return 0;
                }

                price_rand = random.nextInt(35) + 16;
            } else {
                price_rand = random.nextInt(16);
            }
            price_one_percent *= price_rand;
            price -= price_one_percent;
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
        int play_round = 0;
        int next_goods_index = 0;
        int prev_goods_price = 0;
        int prev_percent = 0;
        int rise_proba = 50;
        int depth_proba = 0;

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
                + " - 명령어는 [레이 굿즈 N개 구매], [레이 굿즈 N개 판매], [레이 굿즈 순위] 로 가능합니다.\n"
                + " - 굿즈의 가격은 " + INVEST_SHIFT_TIME_MIN + "분마다 변경됩니다.\n"
                + " - 거래 횟수 리스크를 위하여 굿즈 구매시 개인 자산에 따라 수수료가 차등 적용 됩니다.\n"
                + " - 현.재 보유 자산(현금 + 굿즈) " + INVEST_TAX_SEPARATE_PRICE + "원당 수수료 1%로 계산합니다. (예시, " + INVEST_SEED_MONEY + "원의 수수료는 " + (INVEST_SEED_MONEY / INVEST_TAX_SEPARATE_PRICE) + "%)\n"
                + " - 지금부터 " + INVEST_END_TIME_MIN + "분동안 진행됩니다.\n"
                + " - 초기자금은 모두 " + INVEST_SEED_MONEY + "원으로 시작합니다.\n"
                + " - 거래 횟수가 1번이라도 있어야 참가처리가 됩니다.\n"
                + " - 재미로만 즐겨주세요~ 행운을 빕니다!";
        KakaoSendReply(result, getSbn());

        /* playing investment game */
        while (play_time <= INVEST_END_TIME_MIN) {
            prev_goods_price = invest_goods_price;
            invest_goods_price = changePrice(invest_goods_price, rise_proba, depth_proba);
            if (invest_goods_price <= 0) {
                rise_proba = 50;
                depth_proba = 0;
                prev_goods_price = 1000;
                invest_goods_price = changePrice(1000, rise_proba, depth_proba);
                next_goods_index = (invest_goods_index + 1) % invest_goods.length;


                for (int i = 0; i < invest_purchase.size(); i++) {
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

            play_round++;
            rise_proba = determinatePriceProbability();
            depth_proba = determinateDepthProbability(play_round);

            result = "\uD83D\uDE4F [굿즈 시세 현황] \uD83D\uDE4F\n"
                    + invest_goods[invest_goods_index] + ": "
                    + invest_goods_price + "원 ("
                    + prev_percent + "%)\n"
                    + " - 기존 가격: " + prev_goods_price + "원 " + emoji;
            result = printProbability(result, rise_proba, depth_proba);
            KakaoSendReply(result, getSbn());
            if (invest_goods_price < INVEST_DELISTED_PRICE) {
                result = " * 투자 주의! 상폐 위험!";
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
                    + temp + "원 (TAX: " + (temp / INVEST_TAX_SEPARATE_PRICE) + "%)";
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

    public String printGoodsEmoji() {
        String[] emoji = {"🧸","🪆","🪅","🎎","💎","🪪","🗽","🎠","🗼"};
        Random random = new Random();
        int emoji_rand = random.nextInt(CommandList.RAND_MAX) % emoji.length;
        return emoji[emoji_rand];
    }

    public int calcTAXPercent(int index) {
        int money = invest_money.get(index) + (invest_purchase.get(index) * invest_goods_price);
        int tax = money / INVEST_TAX_SEPARATE_PRICE;
        return tax;
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

            int tax_percent = calcTAXPercent(player_index);
            if (invest_money.get(player_index) < (invest_goods_price * goods_num) + (((invest_goods_price * goods_num) * tax_percent) / 100)) {
                result = sender + "님 "
                        + "굿즈 구매 금액(" + ((invest_goods_price * goods_num) + (((invest_goods_price * goods_num) * tax_percent) / 100)) + "원)이 부족합니다.\n"
                        + " - 시세: " + invest_goods_price + " + TAX " + tax_percent + "% = 개당 " + (invest_goods_price + ((invest_goods_price * tax_percent) / 100)) + "원\n"
                        + " - 보유 자산: \uD83D\uDCB5 " + invest_money.get(player_index) + "원, " + printGoodsEmoji() + " " + invest_purchase.get(player_index) + "개";
                return result;
            }

            invest_purchase_num.set(player_index, invest_purchase_num.get(player_index) + 1);
            invest_money.set(player_index, invest_money.get(player_index) - ((invest_goods_price * goods_num) + (((invest_goods_price * goods_num) * tax_percent) / 100)));
            invest_purchase.set(player_index, invest_purchase.get(player_index) + goods_num);

            result = sender + "님 "
                    + goods_num + "개(" + ((invest_goods_price * goods_num) + (((invest_goods_price * goods_num) * tax_percent) / 100)) + "원) 구매 완료.\n"
                    + " - 시세: " + invest_goods_price + " + TAX " + tax_percent + "% = 개당 " + (invest_goods_price + ((invest_goods_price * tax_percent) / 100)) + "원\n"
                    + " - 보유 자산: \uD83D\uDCB5 " + invest_money.get(player_index) + "원, " + printGoodsEmoji() + " " + invest_purchase.get(player_index) + "개";
            return result;
        }

        if (checkCommnadList(msg, INVEST_SELL_CMD) == 0) {
            player_index = findInvestPlayer(sender);
            goods_num = findNum(msg);
            if (goods_num == 0) goods_num = 1;

            if (invest_purchase.get(player_index) < goods_num) {
                result = sender + "님 "
                        + "굿즈 판매 갯수(" + goods_num + "개)가 부족합니다.\n"
                        + " - 보유 자산: \uD83D\uDCB5 " + invest_money.get(player_index) + "원, " + printGoodsEmoji() + " " + invest_purchase.get(player_index) + "개";
                return result;
            }

            invest_purchase_num.set(player_index, invest_purchase_num.get(player_index) + 1);
            invest_purchase.set(player_index, invest_purchase.get(player_index) - goods_num);
            invest_money.set(player_index, invest_money.get(player_index) + (invest_goods_price * goods_num));

            result = sender + "님 "
                    + goods_num + "개(시세: " + invest_goods_price + "원) 판매 완료.\n"
                    + " - 보유 자산: \uD83D\uDCB5 " + invest_money.get(player_index) + "원, " + printGoodsEmoji() + " " + invest_purchase.get(player_index) + "개";

            return result;
        }

        if (checkCommnadList(msg, INVEST_STATUS_CMD) == 0) {
            result = printInvestStatus();
            return result;
        }

        return result;
    }
}
