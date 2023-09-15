package com.kakao_szbot.cmd;

import static com.kakao_szbot.KakaoNotificationListener.KakaoSendReply;
import static com.kakao_szbot.cmd.MainCommandChecker.checkCommnadList;
import static com.kakao_szbot.lib.CommonLibrary.patternIndexOf;
import static com.kakao_szbot.lib.CommonLibrary.patternLastIndexOf;

import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.kakao_szbot.lib.FileLibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CommandSurvival {
    public final static String TAG = "CommandSurvival";

    public static String[] SURVIVAL_SUMMON_CMD = {
            "소환"
    };

    public static String[] SURVIVAL_SKILL_CMD = {
            "바위", "보", "가위", "체력"
    };

    public static String[] SURVIVAL_INFO_CMD = {
            "정보"
    };
    public static String[] SURVIVAL_HELP_CMD = {
            "도움말", "헬프", "help", "-h"
    };
    public static String[] SURVIVAL_RULE_CMD = {
            "룰", "규칙"
    };
    public static String[] SURVIVAL_START_CMD = {
            "시작"
    };
    public static String[] SURVIVAL_BETTING_CMD = {
            "최애"
    };

    private static String SURVIVAL_DATA_BASE = "survivalData.csv";
    private static int SURVIVAL_STAT_POINT_MAX = 10;

    private static String[] SURVIVAL_SUMMON_EMOJI = {
            "\uD83E\uDEB7",
            "\uD83C\uDF38",
            "⭐",
            "\uD83D\uDD2E",
            "\uD83C\uDF87"};

    private static int SURVIVAL_ATTACK_ROCK = 0;
    private static int SURVIVAL_ATTACK_PAPER = 1;
    private static int SURVIVAL_ATTACK_SCISSORS = 2;
    private static int SURVIVAL_ATTACK_MAX = 3;
    private static String[] SURVIVAL_ATTACK_EMOJI = {
            "\uD83D\uDC4A",
            "\uD83D\uDD90",
            "\uD83D\uDC49"};

    private static int SURVIVAL_BATTLE_DRAW = 0;
    private static int SURVIVAL_BATTLE_FRONT_WIN = 1;
    private static int SURVIVAL_BATTLE_BACK_WIN = 2;
    private static int[][] SURVIVAL_BATTLE_RESULT_ARRAY = {
            {SURVIVAL_BATTLE_DRAW, SURVIVAL_BATTLE_BACK_WIN, SURVIVAL_BATTLE_FRONT_WIN},
            {SURVIVAL_BATTLE_FRONT_WIN, SURVIVAL_BATTLE_DRAW, SURVIVAL_BATTLE_BACK_WIN},
            {SURVIVAL_BATTLE_BACK_WIN, SURVIVAL_BATTLE_FRONT_WIN, SURVIVAL_BATTLE_DRAW}
    };


    private static int SURVIVAL_DEAD = 0;
    private static int SURVIVAL_ALIVE = 1;
    private static int SURVIVAL_BETTING_NONE = 0;
    private static int SURVIVAL_BETTING_FRONT = 1;
    private static int SURVIVAL_BETTING_BACK = 2;

    private static int SURVIVAL_CYCLE_TIME = 1800 * 1000; // 1800 * 1000;
    private static int SURVIVAL_BATTING_TIME = 600 * 1000; // 600 * 1000;
    private static int SURVIVAL_BATTING_AFTER_TIME = 1200 * 1000; // 1200 * 1000;
    private static int SURVIVAL_RESULT_TIME = 180 * 1000; // 180 * 1000;
    private static int SURVIVAL_MSG_MAX = 99999;

    public static int survival_start = 0;
    public static int betting_time = 0;
    public static int total_battle_num = 0;
    private static int current_hoshi_num = 0;

    private class SurvivalPlayer implements Comparable<SurvivalPlayer> {
        private String player_name;
        private String servant_name;
        private Integer servant_health;
        private int[] attack_damage = new int[SURVIVAL_ATTACK_MAX];
        private String[] attack_name = new String[SURVIVAL_ATTACK_MAX];
        private int battle_survive;
        private int battle_group;
        private int hoshi_num;
        private int betting_num;

        @Override
        public int compareTo(SurvivalPlayer o) {
            return o.hoshi_num - hoshi_num;
        }
    }

    private static List<SurvivalPlayer> player_list = new ArrayList<SurvivalPlayer>();
    private static List<SurvivalPlayer> last_list = new ArrayList<SurvivalPlayer>();
    private static SurvivalPlayer player_front, player_back;

    public String helpMessage() {
        String replyMessage =
                "[명령어 종류]\n" +
                " - 도움말\n" +
                " - 소환\n" +
                " - 룰, 규칙\n" +
                " - 정보\n" +
                " - 가위, 바위, 보, 체력";
        return replyMessage;
    }

    public String defaultMessage() {
        String replyMessage = "[2023 추석 이벤트 - 최애의 전쟁]\n" +
                " - 본 이벤트는 서바이벌 컨셉으로 기획되었습니다.\n" +
                " - 참가 신청은 이 곳에 '소환' 이라고만 입력해 주시면 됩니다.\n" +
                " - 꼭 다덕임 오픈채팅방에서 사용중인 프로필로 설정 후에 소환 부탁드립니다.\n\n" +
                helpMessage();
        return replyMessage;
    }

    private String skillhelpMessage() {
        return "※ 스텟 포인트 분배법\n" +
                " - 체력 업/다운, 가위/바위/보 업/다운\n" +
                "   ( 예시 : 가위 업 )\n\n" +
                "※ 공격 이름 설정법\n" +
                " - 가위/바위/보 [원하는 공격 이름]\n" +
                "   ( 예시 : 가위 [용사 펀치!] )";
    }

    private String detailhelpMessage(String sender) {
        String player_name = null;
        int index;

        player_name = getPlayerName(sender);
        if (player_name == null)
            return "개발자에게 문의 바랍니다.\n" +
                    "(프로필 형식을 안맞춰서 실패했을 가능성이 많습니다!)";

        if (getSurvivalPlayer(player_name) == null)
            return "소환 먼저 부탁드립니다.";

        String replyMessage = "[최애의 전쟁 룰북]\n" +
                " - ⭐(호시)가 많은 사람이 최종 승자\n" +
                " - 호시 획득 조건 : \n   > 서번트의 전투 승리 (+?)\n   > 매 전투마다 승자 맞추기 (+?)\n\n" +
                " - 모든 서번트는 랜덤 가위/바위/보로 승부\n" +
                " - 이기면 해당 공격력 만큼 데미지\n" +
                " - 무승부는 무효\n\n" +
                " - 추석 당일(9시부터) 동안 단톡방에서 토너먼트 형식으로 모든 전투는 자동으로 진행\n" +
                " - 기본 잔여 스텟 포인트 2개 등 분배 필요\n" +
                " - 토너먼트 시작 후에는 스텟 수정 불가\n\n" +
                skillhelpMessage();
        return replyMessage;
    }

    private String getServantInfo(SurvivalPlayer player) {
        String replyMessage = "[" + player.player_name + " 마스터님의 서번트 정보]\n" +
                " - 서번트 : " + player.servant_name + "\n" +
                " - 체력 : " + player.servant_health + "\n" +
                " - " + SURVIVAL_ATTACK_EMOJI[SURVIVAL_ATTACK_ROCK] + "(" + player.attack_name[SURVIVAL_ATTACK_ROCK] + ") : " + player.attack_damage[SURVIVAL_ATTACK_ROCK] + "\n" +
                " - " + SURVIVAL_ATTACK_EMOJI[SURVIVAL_ATTACK_PAPER] + "(" + player.attack_name[SURVIVAL_ATTACK_PAPER] + ") : " + player.attack_damage[SURVIVAL_ATTACK_PAPER] + "\n" +
                " - " + SURVIVAL_ATTACK_EMOJI[SURVIVAL_ATTACK_SCISSORS] + "(" + player.attack_name[SURVIVAL_ATTACK_SCISSORS] + ") : " + player.attack_damage[SURVIVAL_ATTACK_SCISSORS] + "\n";

        if (survival_start == 1) {
            replyMessage += " - ⭐(호시) : " + player.hoshi_num;
        } else {
            replyMessage += " - 잔여 스텟 포인트 : " + getStatPoint(player);
        }

        return replyMessage;
    }

    private String getServantSummaryInfo(SurvivalPlayer player) {
        String replyMessage = player.servant_name + "\n" +
                "\uD83D\uDC95(" + player.servant_health + "), " +
                SURVIVAL_ATTACK_EMOJI[SURVIVAL_ATTACK_ROCK] + "(" + player.attack_damage[SURVIVAL_ATTACK_ROCK] + "), " +
                SURVIVAL_ATTACK_EMOJI[SURVIVAL_ATTACK_PAPER] + "(" + player.attack_damage[SURVIVAL_ATTACK_PAPER] + "), " +
                SURVIVAL_ATTACK_EMOJI[SURVIVAL_ATTACK_SCISSORS] + "(" + player.attack_damage[SURVIVAL_ATTACK_SCISSORS] + ")";

        return replyMessage;
    }

    private synchronized int getSurvivalStart() {
        return survival_start;
    }

    private synchronized void setSurvivalStart(int start) {
        survival_start = start;
    }

    public synchronized String voteServant(String msg, String sender) {
        SurvivalPlayer player;
        String player_name;
        int betting_num;

        if (betting_time != 1) {
            return "배팅시간이 아닙니다.";
        }

        player_name = getPlayerName(sender);
        if (player_name == null)
            return "정보 조회를 실패하였습니다. 개발자에게 문의 바랍니다.\n" +
                    "(프로필 형식을 안맞춰서 실패했을 가능성이 많습니다!)";

        player = getSurvivalPlayer(player_name);
        if (player == null)
            return player_name + "님은 최애의 전쟁 참전자가 아니라 배팅이 불가합니다.";

        if (player_front.player_name.equals(player.player_name) ||
                player_back.player_name.equals(player.player_name))
            return player_name + " 마스터님, 현재 전투 준비중인 마스터는 배팅이 불가합니다.";

        if (msg.indexOf("1") >= 0) {
            betting_num = 1;
        } else if (msg.indexOf("2") >= 0) {
            betting_num = 2;
        } else {
            return player_name + " 마스터님, 1번 또는 2번 번호를 골라주세요.";
        }

        player.betting_num = betting_num;

        return player_name + " 마스터님, " + betting_num + "번에 배팅했습니다.";
    }

    private synchronized void setBettingNum(int bet) {
        betting_time = bet;
    }

    private synchronized void settlementBetting(int betting_num) {
        for (SurvivalPlayer p : player_list) {
            if (p.betting_num == betting_num) {
                p.hoshi_num += current_hoshi_num;
            } else if (p.betting_num != SURVIVAL_BETTING_NONE) {
                if (p.hoshi_num < current_hoshi_num)
                    p.hoshi_num = 0;
                else
                    p.hoshi_num -= current_hoshi_num;
            }
            p.betting_num = SURVIVAL_BETTING_NONE;
        }
    }

    private String getHoshiResultMessage() {
        String result = "[최애의 전쟁 ⭐(호시) 순위]";
        SurvivalPlayer win_player = null;
        int hoshi_max = 0;

        Collections.sort(player_list);
        for (SurvivalPlayer p : player_list) {
            result += "\n - " + p.player_name + " 마스터님 : " + p.hoshi_num;

            if (hoshi_max == 0) {
                win_player = p;
                hoshi_max = p.hoshi_num;
            }

            if (hoshi_max == p.hoshi_num) {
                last_list.add(p);
            }
        }

        if (last_list.size() > 1) {
            result += "\n\n이런! 동점자가 생겼군요! 내일 최후의 우승자가 가려집니다!\n" +
                    " ⭐ To Be Continued.. ⭐";
        } else {
            result += "\n\n※ 최종 우승자 : " + win_player.player_name + " 마스터님\n" +
                    " ⭐ 진심으로 축하드립니다! ⭐";
        }

        last_list.clear();

        return result;
    }

    private int getTotalDamage(SurvivalPlayer player) {
        return player.attack_damage[SURVIVAL_ATTACK_ROCK] +
                player.attack_damage[SURVIVAL_ATTACK_PAPER] +
                player.attack_damage[SURVIVAL_ATTACK_SCISSORS];
    }

    private int getStatPoint(SurvivalPlayer player) {
        int stat_point = SURVIVAL_STAT_POINT_MAX;

        stat_point -= player.servant_health;
        stat_point -= player.attack_damage[SURVIVAL_ATTACK_ROCK];
        stat_point -= player.attack_damage[SURVIVAL_ATTACK_PAPER];
        stat_point -= player.attack_damage[SURVIVAL_ATTACK_SCISSORS];

        return stat_point;
    }

    private String parseSkillName(String msg) {
        int first_msg_start_index = msg.indexOf('[') + 1;
        int first_msg_end_index = msg.indexOf(']');
        if ((first_msg_start_index != -1 && first_msg_end_index != -1) &&
                (first_msg_start_index < first_msg_end_index)) {
            return msg.substring(first_msg_start_index, first_msg_end_index);
        }

        return null;
    }

    private int checkSkillCommand(String msg) {
        int rock_index = msg.indexOf("바위");
        int paper_index = msg.indexOf("보");
        int scissors_index = msg.indexOf("가위");

        if (rock_index < 0 && paper_index < 0 && scissors_index < 0)
            return -1;

        if (rock_index < 0)
            rock_index = SURVIVAL_MSG_MAX;

        if (paper_index < 0)
            paper_index = SURVIVAL_MSG_MAX;

        if (scissors_index < 0)
            scissors_index = SURVIVAL_MSG_MAX;

        if (rock_index < paper_index) {
            if (rock_index < scissors_index)
                return SURVIVAL_ATTACK_ROCK;
        } else {
            if (paper_index < scissors_index)
                return SURVIVAL_ATTACK_PAPER;
        }

        return SURVIVAL_ATTACK_SCISSORS;
    }

    private int setSkill(String msg, SurvivalPlayer player, StatusBarNotification sbn) {
        String skill_name;
        int skill_num;

        skill_num = checkSkillCommand(msg);
        if (skill_num >= 0) {
            skill_name = parseSkillName(msg);
            if (skill_name == null) {
                if (msg.indexOf("업") >= 0) {
                    if (getStatPoint(player) == 0) {
                        KakaoSendReply("잔여 스텟 포인트가 없습니다.", sbn);
                        return 0;
                    }
                    player.attack_damage[skill_num] = player.attack_damage[skill_num] + 1;
                } else if (msg.indexOf("다운") >= 0) {
                    if (player.attack_damage[skill_num] == 0) {
                        KakaoSendReply("공격력은 0 이하가 될 수 없습니다.", sbn);
                        return 0;
                    }
                    if (getTotalDamage(player) == 1) {
                        KakaoSendReply("총 공격력은 1 이상이여야 합니다.", sbn);
                        return 0;
                    }
                    player.attack_damage[skill_num] = player.attack_damage[skill_num] - 1;
                } else {
                    return -1;
                }

                FileLibrary csv = new FileLibrary();
                csv.changeSkillStatSurvivalCSV(SURVIVAL_DATA_BASE,
                        player.player_name,
                        player.attack_damage[skill_num],
                        skill_num);
                KakaoSendReply("공격력 적용 성공했습니다!", sbn);
                return 0;
            }

            if (skill_name.indexOf(",") >= 0) {
                KakaoSendReply("공격 이름에 쉼표(,)는 들어갈 수 없습니다 ㅠ.ㅠ", sbn);
                return -1;
            }

            player.attack_name[skill_num] = skill_name;
            FileLibrary csv = new FileLibrary();
            csv.changeSkillNameSurvivalCSV(SURVIVAL_DATA_BASE,
                    player.player_name,
                    player.attack_name[skill_num],
                    skill_num);
            KakaoSendReply("공격 이름 적용 성공했습니다!", sbn);
            return 0;
        }

        return -1;
    }

    private int setHealth(String msg, SurvivalPlayer player, StatusBarNotification sbn) {
        if (msg.indexOf("체력") < 0) {
            return -1;
        }

        if (msg.indexOf("업") >= 0) {
            if (getStatPoint(player) == 0) {
                KakaoSendReply("잔여 스텟 포인트가 없습니다.", sbn);
                return 0;
            }
            player.servant_health = player.servant_health + 1;
        } else if (msg.indexOf("다운") >= 0) {
            if (player.servant_health == 1) {
                KakaoSendReply("체력은 1 이하가 될 수 없습니다.", sbn);
                return 0;
            }
            player.servant_health = player.servant_health - 1;
        } else {
            return -1;
        }

        FileLibrary csv = new FileLibrary();
        csv.changeHealthSurvivalCSV(SURVIVAL_DATA_BASE,
                player.player_name,
                player.servant_health);
        KakaoSendReply("체력 적용 성공했습니다!", sbn);
        return 0;
    }

    private synchronized String setStatMessage(String msg, String sender, StatusBarNotification sbn) {
        SurvivalPlayer player = null;
        String player_name = null;

        player_name = getPlayerName(sender);
        if (player_name == null)
            return "정보 조회를 실패하였습니다. 개발자에게 문의 바랍니다.\n" +
                    "(프로필 형식을 안맞춰서 실패했을 가능성이 많습니다!)";

        player = getSurvivalPlayer(player_name);
        if (player == null)
            return "소환 먼저 부탁드립니다.";

        if (setSkill(msg, player, sbn) == 0) {
            return getServantInfo(player);
        }

        if (setHealth(msg, player, sbn) == 0) {
            return getServantInfo(player);
        }

        return "다시 말해주세요.\n" +
                skillhelpMessage();
    }

    private String servantInfoMessage(String sender) {
        SurvivalPlayer player = null;
        String player_name = null;

        player_name = getPlayerName(sender);
        if (player_name == null)
            return "정보 조회를 실패하였습니다. 개발자에게 문의 바랍니다.\n" +
                    "(프로필 형식을 안맞춰서 실패했을 가능성이 많습니다!)";

        player = getSurvivalPlayer(player_name);
        if (player == null)
            return "소환 먼저 부탁드립니다.";

        return getServantInfo(player);
    }

    private String getPlayerName(String sender) {
        int patternIndex = 0;

        patternIndex = patternIndexOf(sender, "[0-9`~!@#$%^&*()-_=+\\|\\[\\]{};:'\",.<>/? ]");
        Log.d(TAG, "patternIndexOf : " + patternIndex);
        if (patternIndex != 0)
            return sender.substring(0, patternIndex);

        return null;
    }

    private String getServantName(String sender) {
        int patternIndex = 0;

        patternIndex = patternLastIndexOf(sender, "[0-9`~!@#$%^&*()-_=+\\|\\[\\]{};:'\",.<>/? ]");
        Log.d(TAG, "patternLastIndexOf : " + patternIndex);
        if (patternIndex != 0)
            return sender.substring(patternIndex, sender.length());

        return null;
    }

    public synchronized String summonServant(String sender, StatusBarNotification sbn) throws InterruptedException {
        String player_name = null, survant_name = null;

        player_name = getPlayerName(sender);
        if (player_name == null)
            return "소환에 실패하였습니다. 개발자에게 문의 바랍니다.\n" +
                    "(프로필 형식을 안맞춰서 실패했을 가능성이 많습니다!)";

        survant_name = getServantName(sender);
        if (survant_name == null)
            return "소환에 실패하였습니다. 개발자에게 문의 바랍니다.\n" +
                    "(프로필 형식을 안맞춰서 실패했을 가능성이 많습니다!)";

        if (getSurvivalPlayer(player_name) != null)
            return "이미 소환된 서번트가 있습니다.";

        FileLibrary csv = new FileLibrary();
        csv.WriteSurvivalCSV(SURVIVAL_DATA_BASE, player_name, survant_name, 5, 1, 1, 1);

        SurvivalPlayer player = new SurvivalPlayer();

        player.player_name = player_name;
        player.servant_name = survant_name;
        player.servant_health = 5;
        player.attack_damage[SURVIVAL_ATTACK_ROCK] = 1;
        player.attack_damage[SURVIVAL_ATTACK_PAPER] = 1;
        player.attack_damage[SURVIVAL_ATTACK_SCISSORS] = 1;
        player.attack_name[SURVIVAL_ATTACK_ROCK] = "바위";
        player.attack_name[SURVIVAL_ATTACK_PAPER] = "보";
        player.attack_name[SURVIVAL_ATTACK_SCISSORS] = "가위";
        player.battle_survive = SURVIVAL_ALIVE;
        player.battle_group = 0;
        player.hoshi_num = 0;
        player.betting_num = SURVIVAL_BETTING_NONE;

        player_list.add(player);


        Random random = new Random();
        KakaoSendReply(SURVIVAL_SUMMON_EMOJI[random.nextInt(SURVIVAL_SUMMON_EMOJI.length)], sbn);
        Thread.sleep(3000);
        String ment = "\" 서번트 「" + survant_name + "」 소환에 응해 찾아왔습니다. " + player_name + "님, 당신이 나의 마스터입니까? \"";
        KakaoSendReply(ment, sbn);
        Thread.sleep(3000);
        KakaoSendReply(servantInfoMessage(sender), sbn);
        Thread.sleep(3000);
        String result = "최애의 전쟁에 참가하신 것을 환영합니다.\n" +
                "자세한 설명은 '규칙, 룰' 명령어로 확인할 수 있습니다.\n" +
                "먼저 잔여 스텟 포인트를 사용 해주세요.\n\n"
                + skillhelpMessage();

        return result;
    }

    private SurvivalPlayer getSurvivalPlayer(String name) {
        for (SurvivalPlayer p : player_list) {
            if (p.player_name.equals(name)) {
                return p;
            }
        }

        return null;
    }

    private SurvivalPlayer findFrontGroupPlayer(int battle_num) {
        for (SurvivalPlayer p : player_list) {
            if (p.battle_group == battle_num && p.battle_survive == SURVIVAL_ALIVE) {
                return p;
            }
        }

        return null;
    }

    private SurvivalPlayer findBackGroupPlayer(int battle_num) {
        int front_skip = 0;

        for (SurvivalPlayer p : player_list) {
            if (p.battle_group == battle_num && p.battle_survive == SURVIVAL_ALIVE) {
                if (front_skip == 0) {
                    front_skip = 1;
                } else {
                    return p;
                }
            }
        }

        return null;
    }

    private SurvivalPlayer playBattle(int battle_group, int battle_round, StatusBarNotification sbn) throws InterruptedException {
        int i = 0;
        Random random = new Random();
        SurvivalPlayer player_win;
        int rand_front, rand_back;
        int hp_front, hp_back;


        player_front = findFrontGroupPlayer(battle_group);
        player_back = findBackGroupPlayer(battle_group);
        hp_front = player_front.servant_health;
        hp_back = player_back.servant_health;

        String battle_info = "[최애의 전쟁 배틀 시작 준비]\n" +
                "<1번> 서번트 : " + getServantSummaryInfo(player_front) +
                "\n\n<2번> 서번트 : " + getServantSummaryInfo(player_back) +
                "\n\n잠시 후.. 10분 뒤 전투가 시작됩니다." +
                "\n승리할 것 같은 서번트에게 투표해주세요." +
                "\n\n※ 승리, 적중 : ⭐(+" + current_hoshi_num + "), 미적중 : ⭐(-" + current_hoshi_num + ")" +
                "\n - " + CommandList.BOT_NAME + " 최애 1번 or " + CommandList.BOT_NAME + " 최애 2번";
        KakaoSendReply(battle_info, sbn);
        setBettingNum(1);

        Thread.sleep(SURVIVAL_BATTING_TIME);
        setBettingNum(0);

        /* Battle */
        battle_info = "[전투 상세 내역]\n0. " +
                player_front.servant_name + " VS " + player_back.servant_name;
        while (true) {
            rand_front = random.nextInt(SURVIVAL_ATTACK_MAX);
            rand_back = random.nextInt(SURVIVAL_ATTACK_MAX);

            i++;
            battle_info += "\n" + i + ". ";
            battle_info += SURVIVAL_ATTACK_EMOJI[rand_front] + "(" +
                    player_front.attack_damage[rand_front] + ") VS " +
                    SURVIVAL_ATTACK_EMOJI[rand_back] + "(" +
                    player_back.attack_damage[rand_back] + ") : ";

            int result = SURVIVAL_BATTLE_RESULT_ARRAY[rand_front][rand_back];
            if (result == SURVIVAL_BATTLE_FRONT_WIN) {
                hp_back -= player_front.attack_damage[rand_front];
                battle_info += player_front.servant_name + " 승";
            } else if (result == SURVIVAL_BATTLE_BACK_WIN) {
                hp_front -= player_back.attack_damage[rand_back];
                battle_info += player_back.servant_name + " 승";
            } else {
                battle_info += "무승부";
                /*
                int damage = player_front.attack_damage[rand_front] - player_back.attack_damage[rand_back];
                if (damage > 0) {
                    hp_back -= damage;
                } else if (damage == 0) {
                    //battle_info += "그러나 아무 일도 일어나지 않았다";
                } else {
                    hp_front += damage;
                }
                */
            }

            if (hp_front <= 0) {
                battle_info += "\n\n※ " + player_back.servant_name + "의 「" +
                        player_back.attack_name[rand_back] +
                        "」 최후의 일격!";
                battle_info += "\n - " +
                        player_front.servant_name + " (체력 : " + hp_front + ") 쓰러졌다.";
                player_front.battle_survive = SURVIVAL_DEAD;
                player_win = player_back;
                settlementBetting(SURVIVAL_BETTING_BACK);
                break;
            }

            if (hp_back <= 0) {
                battle_info += "\n\n※ " + player_front.servant_name + "의 「" +
                        player_front.attack_name[rand_front] +
                        "」 최후의 일격!";
                battle_info += "\n - " +
                        player_back.servant_name + " (체력 : " + hp_back + ") 쓰러졌다.";
                player_back.battle_survive = SURVIVAL_DEAD;
                player_win = player_front;
                settlementBetting(SURVIVAL_BETTING_FRONT);
                break;
            }
        }

        /* Result */
        if (battle_round != 2) {
            battle_info += "\n\n[최애의 전쟁 " + battle_round + "강 - " + battle_group + "라운드 결과]\n" +
                    " - " + player_win.servant_name + " 승리. \uD83C\uDF89";
        } else {

            battle_info += "\n\n[최애의 전쟁 결승전 결과]\n" +
                    " - " + player_win.servant_name +
                    " (" + player_win.player_name+ " 마스터님) 우승. \uD83C\uDF8A\n\n" +
                    "잠시 후 3분 뒤 최후의 승리자를 공개합니다!";
        }
        KakaoSendReply(battle_info, sbn);

        return player_win;
    }

    private void selectGroupForceCommand(SurvivalPlayer player, int battle_num) {
        player.battle_group = battle_num;
    }

    private void selectGroupCommand(int battle_num) {
        Random random = new Random();
        int rand;

        rand = random.nextInt(player_list.size());
        while(true) {
            if (player_list.get(rand).battle_group == 0 &&
                    player_list.get(rand).battle_survive == SURVIVAL_ALIVE)
                break;

            rand++;
            if (rand == player_list.size())
                rand = 0;
        }
        player_list.get(rand).battle_group = battle_num;
    }

    private void makeBattleCommand(int battle_num, SurvivalPlayer player) {
        while (battle_num > 0) {
            if (player == null) {
                selectGroupCommand(battle_num);
            } else {
                selectGroupForceCommand(player, battle_num);
                player = null;
            }
            selectGroupCommand(battle_num);

            battle_num--;
        }
    }

    private String calcEndTime(int battle_num) {
        String result = "";
        int half = battle_num % 2;
        int hour = battle_num / 2;

        hour += 9;
        result += hour;
        if (half != 0) {
            result += ":30";
        } else {
            result += ":00";
        }

        return result;
    }

    private void makeTournamentCommand(StatusBarNotification sbn) throws InterruptedException {
        int prev_battle_num = player_list.size();
        int temp_battle_num;
        String battle_info;
        SurvivalPlayer win_by_default = null;

        while (prev_battle_num > 1) {
            temp_battle_num = (prev_battle_num / 2);
            total_battle_num += temp_battle_num;

            if (prev_battle_num != 2 && prev_battle_num % 2 == 1) {
                temp_battle_num++;
            }

            prev_battle_num = temp_battle_num;
        }

        Log.d(TAG, "총 배틀 라운드 수 : " + total_battle_num);

        battle_info = "[최애의 전쟁 공지문]\n" +
                " - 안녕하세요. 최애의 전쟁에 참여해주신 마스터 여러분들을 진심으로 환영합니다.\n" +
                " - 최애의 전쟁은 마스터 분들의 최애와 함께하는 RPS(Rock Paper Scissors) 게임이며 최후의 승자 1명을 가리게 됩니다.\n" +
                " - 이번 전쟁에는 총 " + player_list.size() + "명의 마스터 분들이 출전해 주셨습니다.\n\n" +
                " - 승부는 토너먼트로 진행되며, 매 정각 및 30분에 출전자 소개와 배팅을 받고 10분 뒤 승부가 진행됩니다.\n" +
                " - 마지막 경기는 " + calcEndTime(total_battle_num) + " 에 치뤄지게 됩니다.\n\n" +
                " - 전쟁의 최종 승자는 토너먼트 우승자가 아닌 ⭐(호시)가 많은 사람이 최종 승자이니 유의 바랍니다.\n" +
                "   (호시는 승리 또는 배팅시 획득 가능 하므로.. 배팅을 잘 하는 것도 중요하겠지요..)\n" +
                " - 그럼 모두 행운을 빕니다.";
        KakaoSendReply(battle_info, sbn);
        Thread.sleep(SURVIVAL_CYCLE_TIME);

        prev_battle_num = player_list.size();
        while (prev_battle_num > 1) {
            temp_battle_num = (prev_battle_num / 2);

            makeBattleCommand(temp_battle_num, win_by_default);


            if (prev_battle_num != 2) {
                battle_info = "[최애의 전쟁 " + prev_battle_num + "강 대진표]\n" +
                        "※ 총 라운드 수 : " + temp_battle_num;

                for (int i = 1; i <= temp_battle_num; i++) {
                    battle_info += "\n - " + i + " 라운드 : " +
                            findFrontGroupPlayer(i).servant_name +
                            " VS " +
                            findBackGroupPlayer(i).servant_name;
                }
            } else {
                battle_info = "[최애의 전쟁 결승전]\n" +
                        findFrontGroupPlayer(1).servant_name +
                        " VS " +
                        findBackGroupPlayer(1).servant_name;
            }

            win_by_default = null;
            current_hoshi_num++;
            if (prev_battle_num % 2 == 1) {
                win_by_default = findFrontGroupPlayer(0);
                battle_info += "\n - 부전승 : " + win_by_default.servant_name;
                win_by_default.hoshi_num += current_hoshi_num;
            }

            KakaoSendReply(battle_info, sbn);

            if (prev_battle_num != 2) {
                for (int i = 1; i <= temp_battle_num; i++) {
                    SurvivalPlayer win_player = playBattle(i, prev_battle_num, sbn);
                    win_player.hoshi_num += current_hoshi_num;
                    Thread.sleep(SURVIVAL_BATTING_AFTER_TIME);
                }
            } else {
                SurvivalPlayer win_player = playBattle(1, prev_battle_num, sbn);
                win_player.hoshi_num += current_hoshi_num;
                Thread.sleep(SURVIVAL_RESULT_TIME);
            }

            for (int i = 0; i < player_list.size(); i++) {
                player_list.get(i).battle_group = 0;
            }

            if (prev_battle_num % 2 == 1) {
                temp_battle_num++;
            }

            prev_battle_num = temp_battle_num;
        }

        KakaoSendReply(getHoshiResultMessage(), sbn);

        /* clean up */
        for (int i = 0; i < player_list.size(); i++) {
            player_list.get(i).battle_survive = SURVIVAL_ALIVE;
            player_list.get(i).hoshi_num = 0;
        }
        current_hoshi_num = 0;
    }

    public String startSurvivalCommand(StatusBarNotification sbn) throws InterruptedException {
        if (getSurvivalStart() == 1)
            return "최애의 전쟁이 이미 시작 되었습니다.";

        setSurvivalStart(1);
        makeTournamentCommand(sbn);
        setSurvivalStart(0);

        return null;
    }

    public String mainSurvivalCommand(String msg, String sender, StatusBarNotification sbn) {
        try {
            /*
            if (checkCommnadList(msg, SURVIVAL_START_CMD) == 0) {
                return startSurvivalCommand(sbn);
            }
            */
            if (checkCommnadList(msg, SURVIVAL_BETTING_CMD) == 0) {
                return voteServant(msg, sender);
            }
            if (checkCommnadList(msg, SURVIVAL_HELP_CMD) == 0) {
                return helpMessage();
            }
            if (checkCommnadList(msg, SURVIVAL_RULE_CMD) == 0) {
                return detailhelpMessage(sender);
            }
            if (checkCommnadList(msg, SURVIVAL_INFO_CMD) == 0) {
                return servantInfoMessage(sender);
            }
            if (checkCommnadList(msg, SURVIVAL_SUMMON_CMD) == 0) {
                if (getSurvivalStart() == 1)
                    return "최애의 전쟁이 시작되어 명령어 실행 불가입니다 ㅠ.ㅠ";

                return summonServant(sender, sbn);
            }
            if (checkCommnadList(msg, SURVIVAL_SKILL_CMD) == 0) {
                if (getSurvivalStart() == 1)
                    return "최애의 전쟁이 시작되어 명령어 실행 불가입니다 ㅠ.ㅠ";

                return setStatMessage(msg, sender, sbn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return defaultMessage();
    }

    public synchronized void loadSurvivalData() {
        FileLibrary csv = new FileLibrary();
        String allData = csv.ReadCSV(SURVIVAL_DATA_BASE);
        if (allData == null)
            return;

        String[] parts = allData.split("\n");
        for (String part : parts) {
            String[] data = part.split(",");

            SurvivalPlayer player = new SurvivalPlayer();

            player.player_name = data[0];
            player.servant_name = data[1];
            player.servant_health = Integer.parseInt(data[2]);
            player.attack_damage[SURVIVAL_ATTACK_ROCK] = Integer.parseInt(data[3]);
            player.attack_damage[SURVIVAL_ATTACK_PAPER] = Integer.parseInt(data[4]);
            player.attack_damage[SURVIVAL_ATTACK_SCISSORS] = Integer.parseInt(data[5]);
            player.attack_name[SURVIVAL_ATTACK_ROCK] = data[6];
            player.attack_name[SURVIVAL_ATTACK_PAPER] = data[7];
            player.attack_name[SURVIVAL_ATTACK_SCISSORS] = data[8];
            player.battle_survive = SURVIVAL_ALIVE;
            player.battle_group = 0;
            player.hoshi_num = 0;
            player.betting_num = SURVIVAL_BETTING_NONE;

            player_list.add(player);
        }

        Log.d(TAG, "전체 서버트 수 : " + player_list.size());
    }
}
