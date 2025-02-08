package com.kakao_szbot.cmd;

import static com.kakao_szbot.KakaoNotificationListener.KakaoSendReply;
import static com.kakao_szbot.cmd.MainCommandChecker.checkCommnadList;
import static com.kakao_szbot.lib.CommonLibrary.findNum;
import static com.kakao_szbot.lib.CommonLibrary.patternIndexOf;
import static com.kakao_szbot.lib.CommonLibrary.patternLastIndexOf;

import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.kakao_szbot.lib.FileLibrary;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CommandTwentyQuestions {
    public final static String TAG = "CommandTQ";

    public static String[] TQ_CMD = {
            "스무고개"
    };
    public static String[] TQ_READY_CMD = {
            "준비"
    };
    public static String[] TQ_START_CMD = {
            "시작"
    };
    public static String[] TQ_QUESTION_CMD = {
            "질문"
    };
    public static String[] TQ_ANSWER_CMD = {
            "정답"
    };
    public static String[] TQ_SCORE_CMD = {
            "순위"
    };
    public static String[] TQ_LOG_CMD = {
            "현황"
    };

    private static String TQ_DATA_BASE = "twentyQuestionsData.csv";

    public static String[][] TQ_QUESTIONS_LIST = {
            {"아리마 코세이", "4월은 너의 거짓말"},
            {"시즈키 소쥬로", "마법사의 밤"},
            {"타니노 김렛", "우마무스메 PRETTY DERBY"},
            {"페른", "장송의 프리렌"},
            {"몽키디루피", "원피스"},
            {"니토 나즈나", "앙상블 스타즈!!"},
            {"오오가미 코가", "앙상블 스타즈!!"},
            {"토도로키 하지메", "홀로라이브"},
            {"카후", "Virtual Witch Phenomenon"},
            {"아리마 카나", "최애의 아이"},
            {"에멜리아", "Re: 제로부터 시작하는 이세계 생활"},
            {"페코린느", "프린세스 커넥트! Re:Dive"},
            {"아카바네 카르마", "암살교실"},
            {"갈리카", "메타포 리판타지오"},
            {"아카네 리제", "스텔라이브"},
            {"도은호", "PLAVE"},
            {"키티", "산리오"},
            {"예준", "PLAVE"},
            {"토모에 히요리", "앙상블 스타즈!!"},
            {"코노에 카나타", "러브 라이브! 니지가사키 학원 스쿨 아이돌 동호회"},
            {"쟈바미 유메코", "카케구루이"},
            {"오사라기", "사카모토 데이즈"},
            {"사쿠마 레이", "앙상블 스타즈!!"},
            {"이세리 니나", "걸즈 밴드 크라이"},
            {"아냐 포저", "스파이 패밀리"},
            {"버틴", "리버스: 1999"},
            {"코코 헥마티아르", "요르문간드"},
            {"키타가와 마린", "그 비스크 돌은 사랑을 한다"},
            {"를르슈 람페르지", "코드기어스"},
            {"오키테가미 쿄코", "오키테가미 쿄코의 비망록"},
            {"토르", "고바야시네 메이드래곤"},
            {"나단비", "아따아따"},
            {"바이올렛 에버가든", "바이올렛 에버가든"},
            {"카마도 네즈코", "귀멸의 칼날"},
            {"메구밍", "이 멋진 세계에 축복을!"},
            {"서태웅", "슬램덩크"},
            {"미카게 레오", "블루록"},
            {"나가토 유키", "스즈미야 하루히"},
            {"마츠다 토다", "데스노트"},
            {"삐에용", "최애의 아이"},
            {"마츠카와 잇세이", "하이큐"},
            {"요로이즈카 미조레", "리즈와 파랑새"},
            {"마들렌맛 쿠키", "쿠키런"},
            {"리바이 아커만", "진격의 거인"},
            {"바쿠고 카츠키", "나의 히어로 아카데미아"},
            {"카이만", "도로헤도로"},
            {"이토 마코토", "스쿨데이즈"},
            {"스마트 팔콘", "우마무스메 프리티 더비"},
            {"히가시야마 코베니", "체인소맨"},
            {"타카나시 타로", "시로바코"},
            {"쿠죠 죠타로", "죠죠의 기묘한 모험 스타더스트 크루세이더즈"},
            {"클로로 루실후르", "헌터헌터"},
            {"셰릴 놈", "마크로스 프론티어"},
            {"알렉스 루이 암스트롱", "강철의 연금술사"}
    };

    private static int TQ_CYCLE_TIME = 600 * 1000; // 600 * 1000 = 10분;
    private static int TQ_QUESTION_READY_TIME = 600 * 1000; // 600 * 1000 = 10분;
    private static int TQ_GAME_READY_TIME = 1800 * 1000; // 1800 * 1000 = 30분;
    private static int TQ_PLAY_DAYS = 3;
    private static int[] TQ_PLAY_NUM_PER_DAYS = {10, 10, 10};

    public static int tq_game_start = 0;
    public static int tq_question_start = 0;
    public static int tq_progress_day = 3;
    public static int tq_progress_question_number = 24;
    public static String tq_progress_question_answer;
    public static String tq_progress_question_theme;
    public static int tq_question_number_max = 5;
    public static int tq_answer_number_max = 3;

    private class TQPlayer implements Comparable<CommandTwentyQuestions.TQPlayer> {
        private String player_name;
        private int point;
        private int question_asked;
        private int answer_number;

        @Override
        public int compareTo(CommandTwentyQuestions.TQPlayer o) {
            return o.point - point;
        }
    }

    private static List<TQPlayer> player_list = new ArrayList<TQPlayer>();
    private static List<TQPlayer> last_list = new ArrayList<TQPlayer>();
    private static List<String> player_question_list = new ArrayList<String>();
    private static List<String[]>[] quiz_list = new ArrayList[TQ_PLAY_DAYS];


    public String defaultMessage() {
        String replyMessage = null;
        /*
        if (tq_progress_day == 1) {
            replyMessage = "[2025 설 이벤트 - 다덕임 스무고개]\n" +
                    " - 본 이벤트는 스무고개 컨셉으로 만들어졌습니다.\n\n" +
                    " - \"" + CommandList.BOT_NAME[0] + " 질문 내용\" 명령어로 질문이 가능하며, 예 or 아니오 답변이 나오게 됩니다.\n" +
                    "   > '캐릭터 이름' 문제가 출제 되며 한 문제당 각자 " + tq_question_number_max + "번의 질문이 가능합니다.\n" +
                    "   > 주관식의 답장이 아니게 질문해주세요! 소중한 기회를 날려버릴수도!\n" +
                    "   > 하지만 참고로 오답을 말해줄 수도 있습니다.. ^^!\n\n" +
                    " - \"" + CommandList.BOT_NAME[0] + " 정답 내용\" 명령어로 정답이 가능합니다.\n" +
                    "   > 정답은 문제당 최대 " + tq_answer_number_max + "번까지만 가능합니다.\n\n" +
                    " - 출제시간은 3일간(21일 ~ 23일), 10시 ~ 20시 사이 1시간마다, 총 30개의 문제가 한시간 간격으로 출제될 예정입니다.\n" +
                    "    > 문제에 대한 점수는 1점부터 시작해 +1점씩 추가되어 마지막 문제는 30점짜리 문제가 되게됩니다.\n\n" +
                    " - 우승자는 역시나 다음 봇 캐릭 선택권이 주어집니다.\n" +
                    "    > 혹시나 동점으로 우승자가 결정될 경우, 매일 진행되었던 애니퀴즈 점수가 많은 분이 우승자가 되게됩니다.\n\n" +
                    " - 이번에도 많은 참여 부탁드립니다! 감사합니다!";
        } else {
         */
            replyMessage = "[2025 설 이벤트 - 다덕임 스무고개]\n" +
                    " - " + tq_progress_day + "일차, 10시부터 시작합니다 ^^";
        //}
        return replyMessage;
    }

    private String defaultHelpMessage() {
        String replyMessage =
                " - \"" + CommandList.BOT_NAME[0] + " 질문 내용\" 명령어로 질문.\n" +
                " - \"" + CommandList.BOT_NAME[0] + " 정답 내용\" 명령어로 정답.";
        return replyMessage;
    }

    public static synchronized int getTQStart() {
        return tq_game_start;
    }
    private synchronized void setTQStart(int start) {
        tq_game_start = start;
    }
    public static synchronized int getTQQuestionStart() {
        return tq_question_start;
    }
    private synchronized void setTQQuestionStart(int start) {
        tq_question_start = start;
    }

    private String getScoreMessage() {
        String result = "[다덕임 스무고개 순위]";

        Collections.sort(player_list);
        for (TQPlayer p : player_list) {
            result += "\n - " + p.player_name + " : " + p.point + "점";
        }

        return result;
    }

    private String getQuestionLogMessage() {
        String info = "[다덕임 스무고개 질문 현황]";
        if (player_question_list.size() == 0)
            info += "\n - 없음";

        for (String log : player_question_list) {
            info += log;
        }

        return info;
    }

    private String getResultMessage() {
        String result = "[다덕임 스무고개 순위]";
        TQPlayer win_player = null;
        int max_point = 0;

        Collections.sort(player_list);
        for (TQPlayer p : player_list) {
            result += "\n - " + p.player_name + " : " + p.point + "점";


            if (p.point >= max_point) {
                max_point = p.point;
                win_player = p;
                last_list.add(p);
            }
        }

        if (last_list.size() > 1) {
            result += "\n\n이런! 동점으로 우승하셨군요! 추후 애니퀴즈 점수를 확인하여 알려드리겠습니다!\n" +
                    " ⭐ To Be Continued.. ⭐";
        } else if (win_player == null) {
            result += "\n\n아무 참가자도 없었군요!";
        } else {
            result += "\n\n※ 최종 우승자 : " + win_player.player_name + "님\n" +
                    " ⭐ 진심으로 축하드립니다! ⭐";
        }

        last_list.clear();

        return result;
    }

    private void clearQuestionPlayer() {
        for (TQPlayer p : player_list) {
            p.question_asked = 0;
            p.answer_number = 0;
        }
    }

    private void twentyQuestionsGameStart(StatusBarNotification sbn) throws InterruptedException, JSONException, IOException {
        String info, hint;
        int quiz_index = 0;

        info = "[다덕임 스무고개 패치노트]\n" +
                " - 문제당 질문 최대 갯수를 5개로 상향하였습니다.\n" +
                " - '박신 스무고개 순위' 명령어를 추가하였습니다.\n" +
                " - 문제 출제 범위를 소폭 늘렸습니다.\n" +
                " - 힌트 및 답변에 정답이 나오지 않도록 수정하였습니다.\n" +
                " - 마지막 날이니 만큼 문제 난이도가 소.폭? 상승하였습니다.\n" +
                " - '박신 스무고개 현황' 명령어를 추가하였습니다." +
                " 지금까지의 질문 현황을 확인 할 수 있습니다.\n" +
                " - 난이도 소.폭? 상승으로 힌트 갯수를 하나 더 늘렸습니다. (총 5개)\n";
        KakaoSendReply(info, sbn);

        info = defaultMessage();
        KakaoSendReply(info, sbn);
        Thread.sleep(TQ_GAME_READY_TIME);

        while (quiz_index < quiz_list[tq_progress_day - 1].size()) {
            setTQQuestionStart(1);

            tq_progress_question_answer = quiz_list[tq_progress_day - 1].get(quiz_index)[0];
            tq_progress_question_theme = quiz_list[tq_progress_day - 1].get(quiz_index)[1];

            info = "[다덕임 스무고개 " + tq_progress_question_number + "번째 문제]\n" +
                    " - 걸린 점수 : " + tq_progress_question_number + "\n" +
                    "   > 시작합니다! 질문 해주세요!\n   > 50분뒤 정답 공개!";
            KakaoSendReply(info, sbn);

            if (getTQQuestionStart() == 1) {
                hint = new CommandGPT().generateTQQuestionHint1Text(
                        tq_progress_question_answer,
                        tq_progress_question_theme);
                hint = "[다덕임 스무고개 첫 힌트]\n - " + hint;
                KakaoSendReply(hint, sbn);
            }

            Thread.sleep(TQ_CYCLE_TIME);

            if (getTQQuestionStart() == 1) {
                hint = new CommandGPT().generateTQQuestionHint2Text(
                        tq_progress_question_answer,
                        tq_progress_question_theme);
                hint = "[다덕임 스무고개 중간 힌트 1]\n" + hint;
                KakaoSendReply(hint, sbn);
            }

            Thread.sleep(TQ_CYCLE_TIME);

            if (getTQQuestionStart() == 1) {
                hint = new CommandGPT().generateTQQuestionHint3Text(
                        tq_progress_question_answer,
                        tq_progress_question_theme);
                hint = "[다덕임 스무고개 중간 힌트 2]\n - " + hint;
                KakaoSendReply(hint, sbn);
            }

            Thread.sleep(TQ_CYCLE_TIME);

            if (getTQQuestionStart() == 1) {
                hint = new CommandGPT().generateTQQuestionHint4Text(
                        tq_progress_question_answer,
                        tq_progress_question_theme);
                hint = "[다덕임 스무고개 중간 힌트 3]\n" + hint;
                KakaoSendReply(hint, sbn);
            }

            Thread.sleep(TQ_CYCLE_TIME);

            if (getTQQuestionStart() == 1) {
                hint = "[다덕임 스무고개 마지막 힌트]\n - 출연 작품은 " +
                        tq_progress_question_theme + "입니다.";
                KakaoSendReply(hint, sbn);
            }

            Thread.sleep(TQ_CYCLE_TIME);

            if (getTQQuestionStart() == 1) {
                setTQQuestionStart(0);
                info = "[다덕임 스무고개 " + tq_progress_question_number + "번째 문제]\n" +
                        " - 정답 : " + tq_progress_question_answer + "\n" +
                        "   > 아무도 못 맞추다니 너무 아쉽네요 ㅠㅠ";
                KakaoSendReply(info, sbn);
            }

            clearQuestionPlayer();
            player_question_list.clear();
            quiz_index++;
            tq_progress_question_number++;

            Thread.sleep(TQ_QUESTION_READY_TIME);
        }

        if (tq_progress_day == TQ_PLAY_DAYS) {
            KakaoSendReply(getResultMessage(), sbn);

            /* clean up */
            clearTQPlayer();
            tq_progress_day = 1;
            tq_progress_question_number = 1;
        } else {
            info = "[2025 설 이벤트 - 다덕임 스무고개]\n" +
                " - " + tq_progress_day + "일차 게임 종료되었습니다. 모두 고생하셨습니다.";
            KakaoSendReply(info, sbn);
            tq_progress_day++;
        }
    }

    public String startTQCommand(StatusBarNotification sbn) throws InterruptedException, JSONException, IOException {
        if (getTQStart() == 1)
            return "다덕임 스무고개 게임이 이미 시작 되었습니다.";

        setTQStart(1);
        twentyQuestionsGameStart(sbn);
        setTQStart(0);

        return null;
    }

    public String readyTQCommand(StatusBarNotification sbn) throws InterruptedException, JSONException, IOException {
        if (getTQStart() == 1)
            return "다덕임 스무고개 게임이 이미 시작 되었습니다.";

        List<String[]> temp = new ArrayList<>();
        Collections.addAll(temp, TQ_QUESTIONS_LIST);
        Collections.shuffle(temp);

        for (int i = 0; i < quiz_list.length; i++) {
            quiz_list[i] = new ArrayList<>();
        }

        // Add questions to each list
        int skip_index = 0;
        for (int i = 0; i < quiz_list.length; i++) {
            for (int j = 0; j < TQ_PLAY_NUM_PER_DAYS[i]; j++) {
                quiz_list[i].add(temp.get(j + skip_index));
            }

            skip_index += TQ_PLAY_NUM_PER_DAYS[i];
        }

        for (int i = 0; i < quiz_list.length; i++) {
            Collections.shuffle(quiz_list[i]);
        }

        // Access questions3
        Log.d(TAG, "스무고개 퀴즈 목록");
        for (int i = 0; i < quiz_list.length; i++) {
            Log.d(TAG, i + " 일차");
            for (int j = 0; j < quiz_list[i].size(); j++) {
                Log.d(TAG, "- : " + quiz_list[i].get(j)[0] + " : " + quiz_list[i].get(j)[1]);
            }
        }

        return null;
    }

    private void clearTQPlayer() {
        for (CommandTwentyQuestions.TQPlayer p : player_list) {
            p.point = 0;
            p.question_asked = 0;
            p.answer_number = 0;

            FileLibrary csv = new FileLibrary();
            csv.changeTQPointCSV(TQ_DATA_BASE, p.player_name, p.point);
        }
    }

    public synchronized CommandTwentyQuestions.TQPlayer setTQPlayer(String sender) {
        FileLibrary csv = new FileLibrary();
        csv.WriteTQCSV(TQ_DATA_BASE, sender, 0);

        TQPlayer player = new TQPlayer();

        player.player_name = sender;
        player.point = 0;
        player.question_asked = 0;
        player.answer_number = 0;

        player_list.add(player);

        return player;
    }

    private CommandTwentyQuestions.TQPlayer getTQPlayer(String name) {
        name = name.replaceAll(",", "");

        for (CommandTwentyQuestions.TQPlayer p : player_list) {
            if (p.player_name.equals(name)) {
                return p;
            }
        }

        return setTQPlayer(name);
    }

    public String getContent(String msg) {
        String result = msg;

        for (int i = 0; i < TQ_QUESTION_CMD.length; i++) {
            result = result.replace(TQ_QUESTION_CMD[i], "");
        }

        for (int i = 0; i < TQ_ANSWER_CMD.length; i++) {
            result = result.replace(TQ_ANSWER_CMD[i], "");
        }

        if (result == null || result.trim().isEmpty())
            return null;

        return result;
    }

    public String questionTQCommand(String msg, String sender) throws JSONException, IOException {
        String question = getContent(msg);
        if (question == null)
            return defaultHelpMessage();

        TQPlayer player = getTQPlayer(sender);
        if (player.question_asked >= tq_question_number_max) {
            return sender + "님 질문은 문제당 " + tq_question_number_max + "번만 가능합니다.";
        }

        player.question_asked++;

        String answer = new CommandGPT().generateTQQuestionText(question,
                tq_progress_question_answer,
                tq_progress_question_theme);

        question = question.replaceFirst("^\\s+", "");

        String logging = "\n - " + question + " : " + answer;
        player_question_list.add(logging);

        return answer;
    }

    public String answerTQCommand(String msg, String sender) {
        String answer = getContent(msg);
        if (answer == null)
            return defaultHelpMessage();

        TQPlayer player = getTQPlayer(sender);
        if (player.answer_number >= tq_answer_number_max) {
            return sender + "님 정답 횟수를 모두 사용하셨습니다.";
        }

        player.answer_number++;

        answer = answer.replaceAll("[^ ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "");
        answer = answer.replaceAll(" ", "");
        String question = tq_progress_question_answer.replaceAll(" ", "");

        if (answer.equals(question)) {
            setTQQuestionStart(0);
            player.point += tq_progress_question_number;

            FileLibrary csv = new FileLibrary();
            csv.changeTQPointCSV(TQ_DATA_BASE, player.player_name, player.point);

            return sender + "님 \"" + answer + "\" 정답! (+" +
                    tq_progress_question_number + "점)\n" +
                    " - 현.재 점수 : " + player.point;
        }

        return sender + "님 \"" + answer + "\" 오답! (정답 횟수 : " +
                player.answer_number + "/" + tq_answer_number_max + ")";
    }

    public String mainTQCommand(String msg, String sender, StatusBarNotification sbn) {
        String requestMsg = msg;
        for (int i = 0; i < CommandList.BOT_NAME.length; i++) {
            requestMsg = requestMsg.replace(CommandList.BOT_NAME[i], "");
        }

        for (int i = 0; i < CommandList.TQ_GAME_CMD.length; i++) {
            requestMsg = requestMsg.replace(CommandList.TQ_GAME_CMD[i], "");
        }

        try {
            if (checkCommnadList(requestMsg, TQ_READY_CMD) == 0) {
                if (sender.contains("방장")) {
                    return readyTQCommand(sbn);
                }
            }

            if (checkCommnadList(requestMsg, TQ_START_CMD) == 0) {
                if (sender.contains("방장")) {
                    return startTQCommand(sbn);
                }
            }

            if (checkCommnadList(requestMsg, TQ_SCORE_CMD) == 0) {
                return getScoreMessage();
            }

            if (getTQStart() == 0)
                return "스무고개 게임이 시작되지 않았습니다.";

            if (getTQQuestionStart() == 0)
                return "아직 스무고개 질문이 시작되지 않았습니다!";

            if (checkCommnadList(requestMsg, TQ_LOG_CMD) == 0) {
                return getQuestionLogMessage();
            }

            if (checkCommnadList(requestMsg, TQ_QUESTION_CMD) == 0) {
                return questionTQCommand(requestMsg, sender);
            }

            if (checkCommnadList(requestMsg, TQ_ANSWER_CMD) == 0) {
                return answerTQCommand(requestMsg, sender);
            }

            return defaultHelpMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public synchronized void loadTQData() {
        FileLibrary csv = new FileLibrary();
        String allData = csv.ReadCSV(TQ_DATA_BASE);
        if (allData == null)
            return;

        String[] parts = allData.split("\n");
        for (String part : parts) {
            String[] data = part.split(",");

            CommandTwentyQuestions.TQPlayer player = new TQPlayer();

            player.player_name = data[0];
            player.point = Integer.parseInt(data[1]);
            player.question_asked = 0;
            player.answer_number = 0;

            player_list.add(player);
        }

        Log.d(TAG, "전체 TQ 참가자 수 : " + player_list.size());
    }
}
