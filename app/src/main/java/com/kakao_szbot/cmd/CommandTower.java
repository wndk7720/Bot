package com.kakao_szbot.cmd;

import static com.kakao_szbot.KakaoNotificationListener.KakaoSendReply;
import static com.kakao_szbot.cmd.MainCommandChecker.checkCommnadList;
import static com.kakao_szbot.lib.CommonLibrary.findNum;
import static com.kakao_szbot.lib.CommonLibrary.patternIndexOf;
import static com.kakao_szbot.lib.CommonLibrary.patternLastIndexOf;

import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.kakao_szbot.lib.FileLibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CommandTower {
    public final static String TAG = "CommandTower";

    public static String[] TOWER_ATTEND_CMD = {
            "참가"
    };

    public static String[] TOWER_DICE_CMD = {
            "주사위"
    };


    public static String[] TOWER_INFO_CMD = {
            "정보"
    };
    public static String[] TOWER_HELP_CMD = {
            "도움말", "헬프", "help", "-h"
    };
    public static String[] TOWER_RULE_CMD = {
            "룰", "규칙"
    };
    public static String[] TOWER_START_CMD = {
            "시작"
    };

    private static String TOWER_DATA_BASE = "towerData.csv";
    private static int TOWER_FLOOR_MAX = 100;
    private static int TOWER_FLOOR_THRESHOLD = 70;
    private static int TOWER_DICE_NUM = 16;
    public static int[][] TOWER_DICE = {
            {0,0,0,0,0,0},
            {1,2,3,4,5,6},
            {0,1,2,4,6,8},
            {3,3,3,3,4,4},
            {0,4,4,4,4,4},
            {-2,-2,5,5,7,7},
            {-2,-2,6,6,6,6},
            {0,0,7,7,7,0,0},
            {-4,8,6,8,6,-4},
            {0,0,1,1,9,9},
            {-4,-4,-4,10,10,10},
            {1,1,1,1,1,11},
            {-4,-3,-2,-1,12,12},
            {-17,5,6,7,8,9},
            {-1,-1,-1,-1,-1,14},
            {-15,-5,-1,1,5,15},
            {0,0,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,30},
    };

    public static String[] TOWER_DICE_NAME = {
            "사용안함",
            "일반",
            "짝수 위주",
            "너무 안전한",
            "너의 거짓말",
            "부끄러운",
            "내고향",
            "럭키세븐 (7면체)",
            "에이티식스",
            "구구",
            "천국과 지옥",
            "빼빼로",
            "잼민이",
            "나락만 피하자",
            "가시 밭길",
            "모 아니면 도",
            "진짜진짜 비상시에만 쓰시오 (16면체)",
    };

    private static int TOWER_CYCLE_TIME = 1620 * 1000; // 1620 * 1000 = 27분;
    private static int TOWER_READY_TIME = 180 * 1000; // 180 * 1000 = 3분;
    private static int[] TOWER_FLOOR_PRINT_FLAG = {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    };

    public static int tower_start = 0;
    public static int tower_ready = 0;

    private class TowerPlayer implements Comparable<CommandTower.TowerPlayer> {
        private String player_name;
        private String participant_name;
        private Integer dice_type;
        private Integer prev_dice_type;
        private int floor_num;
        private int fluctuation;

        @Override
        public int compareTo(CommandTower.TowerPlayer o) {
            return o.floor_num - floor_num;
        }
    }

    private static List<TowerPlayer> player_list = new ArrayList<TowerPlayer>();
    private static List<TowerPlayer> last_list = new ArrayList<TowerPlayer>();

    public String helpMessage() {
        String replyMessage =
                "[명령어 종류]\n" +
                " - 도움말\n" +
                " - 참가\n" +
                " - 룰, 규칙\n" +
                " - 정보\n" +
                " - 주사위\n" +
                " - 주사위 종류";
        return replyMessage;
    }

    public String defaultMessage() {
        String replyMessage = "[2024 설 이벤트 - 다덕의 탑]\n" +
                " - 본 이벤트는 탑 등반 컨셉으로 기획되었습니다.\n" +
                " - 신청은 이 곳에 '참가' 라고만 입력해 주시면 됩니다.\n" +
                " - 꼭 다덕임 오픈채팅방에서 사용중인 프로필로 설정 후에 참가 부탁드립니다.\n\n" +
                helpMessage();
        return replyMessage;
    }

    private String diceHelpMessage() {
        return "※ 주사위 설정법 및 종류 확인\n" +
                " - 주사위 1 ~ " + TOWER_DICE_NUM + "\n" +
                "   ( 예시 : 주사위 1 )\n" +
                " - 주사위 종류";
    }

    private String detailhelpMessage(String sender) {
        String player_name = null;

        player_name = getPlayerName(sender);
        if (player_name == null)
            return "개발자에게 문의 바랍니다.\n" +
                    "(프로필 형식을 안맞춰서 실패했을 가능성이 많습니다!)";

        if (getTowerPlayer(player_name) == null)
            return "참가 먼저 부탁드립니다.";

        String replyMessage = "[다덕의 탑 룰북]\n" +
                " - 다덕의 탑 " + TOWER_FLOOR_MAX + "층에 먼저 도착하는 자가 최종 승자\n" +
                " - 설날 당일(9시부터) 동안 단톡방에서 턴제 방식으로 자동으로 진행\n" +
                " - 30분마다 각자 설정한 주사위가 굴려져서 해당 숫자만큼 등반\n" +
                " - 등반 시작 후에는 참가 불가\n" +
                " - 주사위 교체는 매 턴 시작 3분전까지 가능\n" +
                " - " + TOWER_FLOOR_THRESHOLD + "층 진입시 주사위 교체 불가\n" +
                " - 마이너스가 있는 주사위의 경우 탑의 지하실 존재하니 유의\n\n" +
                diceHelpMessage();
        return replyMessage;
    }

    private String getDiceInfo(int num) {
        int i = 0;
        String replyMessage = TOWER_DICE_NAME[num] + " 주사위\n   [ ";
        for (i = 0; i < TOWER_DICE[num].length - 1 ; i++) {
            replyMessage += TOWER_DICE[num][i] + ", ";
        }
        replyMessage += TOWER_DICE[num][i] + " ]";

        return replyMessage;
    }

    private String getParticipantInfo(TowerPlayer player) {
        int i = 0;
        String replyMessage = "[" + player.player_name + " 님의 최애 정보]\n" +
                " - 최애 : " + player.participant_name + "\n" +
                " - 주사위 타입 : " + getDiceInfo(player.dice_type);

        return replyMessage;
    }

    private synchronized int getTowerStart() {
        return tower_start;
    }

    private synchronized void setTowerStart(int start) {
        tower_start = start;
    }

    private synchronized int getTowerReady() {
        return tower_ready;
    }

    private synchronized void setTowerReady(int ready) {
        tower_ready = ready;
    }

    private String getResultMessage() {
        String result = "[다덕의 탑 순위]";
        TowerPlayer win_player = null;

        Collections.sort(player_list);
        for (TowerPlayer p : player_list) {
            result += "\n - " + p.player_name + "의 " + p.participant_name + " : " + p.floor_num + "층";

            if (p.floor_num >= TOWER_FLOOR_MAX) {
                win_player = p;
                last_list.add(p);
            }
        }

        if (last_list.size() > 1) {
            result += "\n\n이런! 동시에 여러명이 100층을 넘기셨군요! 추후 추첨을 진행하겠습니다!\n" +
                    " ⭐ To Be Continued.. ⭐";
        } else {
            result += "\n\n※ 최종 우승자 : " + win_player.player_name + "님\n" +
                    " ⭐ 진심으로 축하드립니다! ⭐";
        }

        last_list.clear();

        return result;
    }

    private int setDice(String msg, TowerPlayer player, StatusBarNotification sbn) {
        int diceNum = findNum(msg);

        if (diceNum <= 0 || diceNum > TOWER_DICE_NUM) {
            String replyMessage = "[주사위 종류]\n";
            for (int i = 1; i <= TOWER_DICE_NUM; i++) {
                replyMessage += " - " + i + ". " + getDiceInfo(i) + "\n";
            }
            replyMessage += "\n※ 주사위는 1 ~ " + TOWER_DICE_NUM + " 사이로 설정 부탁드립니다.";
            
            KakaoSendReply(replyMessage, sbn);
            return -1;
        }

        player.dice_type = diceNum;

        FileLibrary csv = new FileLibrary();
        csv.changeDiceTowerCSV(TOWER_DATA_BASE,
                player.player_name,
                player.dice_type);
        KakaoSendReply("주사위 설정 성공했습니다!", sbn);
        return 0;
    }

    private synchronized String setDiceMessage(String msg, String sender, StatusBarNotification sbn) {
        TowerPlayer player = null;
        String player_name = null;

        player_name = getPlayerName(sender);
        if (player_name == null)
            return "정보 조회를 실패하였습니다. 개발자에게 문의 바랍니다.\n" +
                    "(프로필 형식을 안맞춰서 실패했을 가능성이 많습니다!)";

        player = getTowerPlayer(player_name);
        if (player == null)
            return "참가 먼저 부탁드립니다.";

        if (player.floor_num >= TOWER_FLOOR_THRESHOLD) {
            return TOWER_FLOOR_THRESHOLD + "층에 진입하셔서 명령어 실행 불가입니다 ㅠ.ㅠ";
        }

        if (setDice(msg, player, sbn) == 0) {
            return getParticipantInfo(player);
        }

        return "다시 말해주세요.\n" +
                diceHelpMessage();
    }

    private String participantInfoMessage(String sender) {
        TowerPlayer player = null;
        String player_name = null;

        player_name = getPlayerName(sender);
        if (player_name == null)
            return "정보 조회를 실패하였습니다. 개발자에게 문의 바랍니다.\n" +
                    "(프로필 형식을 안맞춰서 실패했을 가능성이 많습니다!)";

        player = getTowerPlayer(player_name);
        if (player == null)
            return "참가 먼저 부탁드립니다.";

        return getParticipantInfo(player);
    }

    private String getPlayerName(String sender) {
        int patternIndex = 0;

        patternIndex = patternIndexOf(sender, "[0-9`~!@#$%^&*()-_=+\\|\\[\\]{};:'\",.<>/? ]");
        Log.d(TAG, "patternIndexOf : " + patternIndex);
        if (patternIndex != 0)
            return sender.substring(0, patternIndex);

        return null;
    }

    private String getParticipantName(String sender) {
        int patternIndex = 0;

        patternIndex = patternLastIndexOf(sender, "[0-9`~!@#$%^&*()-_=+\\|\\[\\]{};:'\",.<>/? ]");
        Log.d(TAG, "patternLastIndexOf : " + patternIndex);
        if (patternIndex != 0)
            return sender.substring(patternIndex, sender.length());

        return null;
    }

    public synchronized String attend(String sender, StatusBarNotification sbn) throws InterruptedException {
        String player_name = null, participant_name = null;

        player_name = getPlayerName(sender);
        if (player_name == null)
            return "참가에 실패하였습니다. 개발자에게 문의 바랍니다.\n" +
                    "(프로필 형식을 안맞춰서 실패했을 가능성이 많습니다!)";

        participant_name = getParticipantName(sender);
        if (participant_name == null)
            return "참가에 실패하였습니다. 개발자에게 문의 바랍니다.\n" +
                    "(프로필 형식을 안맞춰서 실패했을 가능성이 많습니다!)";

        if (getTowerPlayer(player_name) != null)
            return "이미 참가한 최애가 있습니다.";

        FileLibrary csv = new FileLibrary();
        csv.WriteTowerCSV(TOWER_DATA_BASE, player_name, participant_name, 1);

        TowerPlayer player = new TowerPlayer();

        player.player_name = player_name;
        player.participant_name = participant_name;
        player.dice_type = 1;
        player.floor_num = 0;

        player_list.add(player);

        KakaoSendReply(participantInfoMessage(sender), sbn);
        Thread.sleep(3000);
        String result = "다덕의 탑에 참가하신 것을 환영합니다.\n" +
                "자세한 설명은 '규칙, 룰' 명령어로 확인할 수 있습니다.\n" +
                "원하는 주사위로 설정 해주세요.\n\n"
                + diceHelpMessage();

        return result;
    }

    private TowerPlayer getTowerPlayer(String name) {
        for (TowerPlayer p : player_list) {
            if (p.player_name.equals(name)) {
                return p;
            }
        }

        return null;
    }

    private void getParticipantList(StatusBarNotification sbn) throws InterruptedException {
        String battle_info = "[다덕의 탑 참가자 명단]";
        for (TowerPlayer p : player_list) {
            battle_info += "\n - " + p.participant_name + " : " + TOWER_DICE_NAME[p.dice_type] + " 주사위";
            p.prev_dice_type = p.dice_type;
        }
        KakaoSendReply(battle_info, sbn);
    }

    private void getParticipantReadyList(int phase, StatusBarNotification sbn) throws InterruptedException {
        String battle_info = "[다덕의 탑 준비 페이스 " + phase + "]";
        boolean dice_change = false;
        for (TowerPlayer p : player_list) {
            if (p.prev_dice_type != p.dice_type) {
                battle_info += "\n - " + p.participant_name + " : " +
                        TOWER_DICE_NAME[p.prev_dice_type] + " 주사위 -> " +
                        TOWER_DICE_NAME[p.dice_type] + " 주사위로 교체";
                dice_change = true;
            }
        }

        if (dice_change == false) {
            battle_info += "\n - 아무도 주사위를 변경하지 않았습니다.";
        }
        battle_info += "\n\n※ 3분뒤 다음 턴 시작합니다.";

        KakaoSendReply(battle_info, sbn);
    }

    private String printFloor(int floor_num, boolean first_floor)
    {
        String battle_info = "";

        if (floor_num < -100) {
            if (TOWER_FLOOR_PRINT_FLAG[11] == 0) {
                if (first_floor == false) { battle_info += "\n"; }
                battle_info += "\n **** -100층 \uD83D\uDDA4 ******";
                TOWER_FLOOR_PRINT_FLAG[11] = 1;
            }
        } else if (floor_num < -80) {
            if (TOWER_FLOOR_PRINT_FLAG[10] == 0) {
                if (first_floor == false) { battle_info += "\n"; }
                battle_info += "\n ***** -80층 \uD83E\uDDDF\u200D♂ ******";
                TOWER_FLOOR_PRINT_FLAG[10] = 1;
            }
        } else if (floor_num < -60) {
            if (TOWER_FLOOR_PRINT_FLAG[9] == 0) {
                if (first_floor == false) { battle_info += "\n"; }
                battle_info += "\n ***** -60층 \uD83D\uDC64 ******";
                TOWER_FLOOR_PRINT_FLAG[9] = 1;
            }
        } else if (floor_num < -40) {
            if (TOWER_FLOOR_PRINT_FLAG[8] == 0) {
                if (first_floor == false) { battle_info += "\n"; }
                battle_info += "\n ***** -40층 \uD83D\uDD77 ******";
                TOWER_FLOOR_PRINT_FLAG[8] = 1;
            }
        } else if (floor_num < -20) {
            if (TOWER_FLOOR_PRINT_FLAG[0] == 0) {
                if (first_floor == false) { battle_info += "\n"; }
                battle_info += "\n ***** -20층 \uD83D\uDD78 ******";
                TOWER_FLOOR_PRINT_FLAG[0] = 1;
            }
        } else if (floor_num < 0) {
            if (TOWER_FLOOR_PRINT_FLAG[1] == 0) {
                if (first_floor == false) { battle_info += "\n"; }
                battle_info += "\n ******* 0층 \uD83E\uDEB9 ******";
                TOWER_FLOOR_PRINT_FLAG[1] = 1;
            }
        } else if (floor_num < 20) {
            if (TOWER_FLOOR_PRINT_FLAG[2] == 0) {
                if (first_floor == false) { battle_info += "\n"; }
                battle_info += "\n ****** 20층 \uD83E\uDEBA ******";
                TOWER_FLOOR_PRINT_FLAG[2] = 1;
            }
        } else if (floor_num < 40) {
            if (TOWER_FLOOR_PRINT_FLAG[3] == 0) {
                if (first_floor == false) { battle_info += "\n"; }
                battle_info += "\n ****** 40층 \uD83E\uDD8E ******";
                TOWER_FLOOR_PRINT_FLAG[3] = 1;
            }
        } else if (floor_num < 60) {
            if (TOWER_FLOOR_PRINT_FLAG[4] == 0) {
                if (first_floor == false) { battle_info += "\n"; }
                battle_info += "\n ****** 60층 \uD83D\uDC0D ******";
                TOWER_FLOOR_PRINT_FLAG[4] = 1;
            }
        } else if (floor_num < 80) {
            if (TOWER_FLOOR_PRINT_FLAG[5] == 0) {
                if (first_floor == false) { battle_info += "\n"; }
                battle_info += "\n ****** 80층 \uD83E\uDD96 ******";
                TOWER_FLOOR_PRINT_FLAG[5] = 1;
            }
        } else if (floor_num < 100) {
            if (TOWER_FLOOR_PRINT_FLAG[6] == 0) {
                if (first_floor == false) { battle_info += "\n"; }
                battle_info += "\n ***** 100층 \uD83D\uDC09 ******";
                TOWER_FLOOR_PRINT_FLAG[6] = 1;
            }
        } else if (floor_num < 200) {
            if (TOWER_FLOOR_PRINT_FLAG[7] == 0) {
                if (first_floor == false) { battle_info += "\n"; }
                battle_info += "\n ***** Goal \uD83C\uDFC1 ******";
                TOWER_FLOOR_PRINT_FLAG[7] = 1;
            }
        }

        return battle_info;
    }

    private int playClimb(int phase, StatusBarNotification sbn) throws InterruptedException {
        Random random = new Random();
        int rand_num = 0;
        boolean game_over = false;
        boolean first_floor = true;

        for (TowerPlayer p : player_list) {
            rand_num = random.nextInt(TOWER_DICE[p.dice_type].length);
            p.fluctuation = TOWER_DICE[p.dice_type][rand_num];
            p.prev_dice_type = p.dice_type;
            p.floor_num += p.fluctuation;

            if (p.floor_num >= TOWER_FLOOR_MAX)
                game_over = true;
        }

        Collections.sort(player_list);
        String battle_info = "[다덕의 탑\uD83D\uDDFC 페이스 " + phase + "]";
        for (TowerPlayer p : player_list) {
            battle_info += printFloor(p.floor_num, first_floor);
            first_floor = false;

            battle_info += "\n - " + p.participant_name + " : " + p.floor_num + "층";
            battle_info += " (";
            if (p.fluctuation >= 0) {
                battle_info += "+";
            }
            battle_info += p.fluctuation + ")";
            if (p.floor_num < -100) {
                battle_info += "\n※ -100층 달성 이벤트로 다음 턴에 50층부터 시작합니다.";
                p.floor_num = 50;
            }
        }
        KakaoSendReply(battle_info, sbn);

        for (int i = 0; i < TOWER_FLOOR_PRINT_FLAG.length; i++) {
            TOWER_FLOOR_PRINT_FLAG[i] = 0;
        }

        if (game_over)
            return 1;

        return 0;
    }

    private void makeTowerCommand(StatusBarNotification sbn) throws InterruptedException {
        String battle_info;
        int phase = 1;

        battle_info = "[다덕의 탑 공지문]\n" +
                " - 안녕하세요. 다덕의 탑에 참여해주신 여러분들을 진심으로 환영합니다.\n" +
                " - 다덕의 탑은 참가자 분들의 최애와 함께하는 등반 게임이며 최후의 승자 1명을 가리게 됩니다.\n" +
                " - 이번 게임에는 총 " + player_list.size() + "명의 참가자 분들이 출전해 주셨습니다.\n\n" +
                " - 등반은 매 정각 및 30분에 주사위가 굴려지며 등반이 진행됩니다.\n" +
                " - 탑의 최종 우승자는 " + TOWER_FLOOR_MAX + "층을 먼저 도착한 참가자가 됩니다.\n\n" +
                " - 참고로 매 턴 시작 3분전까지는 주사위를 교체할 수 있으니 상황에 따라 활용하셔도 좋습니다.\n" +
                "   (다만, " + TOWER_FLOOR_THRESHOLD + "층 진입시 부터는 주사위 교체 불가합니다.)\n" +
                " - 그럼 모두 행운을 빕니다.";
        KakaoSendReply(battle_info, sbn);
        getParticipantList(sbn);

        while (true) {
            Thread.sleep(TOWER_CYCLE_TIME);
            setTowerReady(1);
            getParticipantReadyList(phase, sbn);
            Thread.sleep(TOWER_READY_TIME);

            if (playClimb(phase++, sbn) != 0) {
                setTowerReady(0);
                break;
            }

            setTowerReady(0);
        }

        KakaoSendReply(getResultMessage(), sbn);

        /* clean up */
        for (int i = 0; i < player_list.size(); i++) {
            player_list.get(i).floor_num = 0;
        }
    }

    public String startTowerCommand(StatusBarNotification sbn) throws InterruptedException {
        if (getTowerStart() == 1)
            return "다덕의 탑이 이미 시작 되었습니다.";

        setTowerStart(1);
        makeTowerCommand(sbn);
        setTowerStart(0);

        return null;
    }

    public String mainTowerCommand(String msg, String sender, StatusBarNotification sbn) {
        try {
            /*
            if (checkCommnadList(msg, TOWER_START_CMD) == 0) {
                return startTowerCommand(sbn);
            }
            */
            if (checkCommnadList(msg, TOWER_HELP_CMD) == 0) {
                return helpMessage();
            }
            if (checkCommnadList(msg, TOWER_RULE_CMD) == 0) {
                return detailhelpMessage(sender);
            }
            if (checkCommnadList(msg, TOWER_INFO_CMD) == 0) {
                return participantInfoMessage(sender);
            }
            if (checkCommnadList(msg, TOWER_ATTEND_CMD) == 0) {
                if (getTowerStart() == 1)
                    return "다덕의 탑 행사가 시작되어 명령어 실행 불가입니다 ㅠ.ㅠ";

                return attend(sender, sbn);
            }
            if (checkCommnadList(msg, TOWER_DICE_CMD) == 0) {
                if (getTowerReady() == 1)
                    return "턴 시작 3분전이라 명령어 실행 불가입니다 ㅠ.ㅠ";

                return setDiceMessage(msg, sender, sbn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return defaultMessage();
    }

    public synchronized void loadTowerData() {
        FileLibrary csv = new FileLibrary();
        String allData = csv.ReadCSV(TOWER_DATA_BASE);
        if (allData == null)
            return;

        String[] parts = allData.split("\n");
        for (String part : parts) {
            String[] data = part.split(",");

            TowerPlayer player = new TowerPlayer();

            player.player_name = data[0];
            player.participant_name = data[1];
            player.dice_type = Integer.parseInt(data[2]);
            player.floor_num = 0;

            player_list.add(player);
        }

        Log.d(TAG, "전체 참가자 수 : " + player_list.size());
    }
}
