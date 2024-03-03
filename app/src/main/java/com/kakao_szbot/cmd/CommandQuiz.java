package com.kakao_szbot.cmd;

import static com.kakao_szbot.KakaoNotificationListener.KakaoSendReply;
import static com.kakao_szbot.KakaoNotificationListener.getSbn;
import static com.kakao_szbot.lib.CommonLibrary.patternIndexOf;

import android.util.Log;

import com.kakao_szbot.lib.FileLibrary;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CommandQuiz {
    public final static String TAG = "CommandQuiz";

    private static int FIFTEEN_MIN_PER_SEC = 900; // Default 900
    private static int FIVE_MIN_PER_SEC = 300; // Default 300
    private static int TEN_MIN_PER_SEC = 600; // Default 600
    private static int BLIND_FREQUENCY = 7;

    public static String ani_quiz_name;
    public static JSONObject ani_quiz_object;
    public static int ani_quiz_start = 0;
    public static int ani_quiz_answer_flag = 1;

    public final static String ANI_QUIZ1_POINT_FILE_NAME = "quizPointList.csv";
    public final static String ANI_QUIZ2_POINT_FILE_NAME = "quiz2PointList.csv";
    public static int total_quiz1_point = 0;
    public static int total_quiz2_point = 0;
    private static Map<String, Integer> player1 = new HashMap<>();
    private static Map<String, Integer> player2 = new HashMap<>();


    public static String convertToConsonants(String word, int hard_mode) {
        final int BASE_CODE = 44032; // The base code of Hangul syllables
        final int CHOSUNG_INTERVAL = 588; // The interval between initial consonants
        final char[] CHOSUNG_LIST = {
                'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ',
                'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
        }; // The list of initial consonants
        Random random = new Random();
        int rand = random.nextInt(CommandList.RAND_MAX);
        int blind_index;
        int i = 0;

        blind_index = rand % BLIND_FREQUENCY;
        blind_index %= word.length();
        if (hard_mode == 1 && blind_index == 0) {
            blind_index++;
        }

        StringBuilder result = new StringBuilder();
        for (char ch : word.toCharArray()) {
            if (ch >= '가' && ch <= '힣') { // Check if the character is a Hangul syllable
                int index = ch - BASE_CODE; // Calculate the index of the syllable
                int chosungIndex = index / CHOSUNG_INTERVAL; // Calculate the index of the initial consonant

                if (i == blind_index || (hard_mode == 1 && i == 0)) {
                    result.append('■');
                } else {
                    result.append(CHOSUNG_LIST[chosungIndex]); // Add the initial consonant to the result
                }

                if ((++i % BLIND_FREQUENCY) == 0) {
                    i = 0;
                }
            } else if (ch == ' '){
                result.append(ch); // Add ' ' space characters to the result as is
            } else {
                if (i == blind_index || (hard_mode == 1 && i == 0)) {
                    result.append('■');
                } else {
                    result.append(ch); // Add non-Hangul characters to the result as is
                }

                if ((++i % BLIND_FREQUENCY) == 0) {
                    i = 0;
                }
            }
        }
        return result.toString();
    }

    public String printQuizResult() throws Exception {
        String result = "";

        result += " - " + ani_quiz_object.getString("subject");
        result += " (" + ani_quiz_object.getString("genres") + ")\n";
        result += "  > 방영일 : " + ani_quiz_object.getString("startDate") + "\n";
        result += "  > " + ani_quiz_object.getString("website");

        return result;
    }

    public String quiz2Message(String msg) throws Exception {
        String ani_quiz_name_consonants = null;
        String result = null;

        if (ani_quiz_start == 0) {
            ani_quiz_start = 1;
            ani_quiz_answer_flag = 0;

            JSONObject select_quiz;
            String select_quiz_name, emptyCheckMsg;
            select_quiz = new CommandCrawling().getAniObject();
            select_quiz_name = select_quiz.getString("subject");
            select_quiz_name = select_quiz_name.replaceAll("[^ ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "");
            if (select_quiz_name.length() == 0) {
                return "퀴즈 출제 실패☆";
            }

            Random random = new Random();
            int rand = random.nextInt(CommandList.RAND_MAX);
            int hard_mode = 0;
            if (select_quiz_name.length() > 2 && rand < (CommandList.RAND_MAX / 4)) {
                hard_mode = 1;
            }

            hard_mode = 0;
            ani_quiz_name_consonants = convertToConsonants(select_quiz_name, hard_mode);

            ani_quiz_object = select_quiz;
            select_quiz_name = select_quiz_name.toLowerCase();
            ani_quiz_name = select_quiz_name.replaceAll(" ", "");
            Log.d(TAG, "퀴즈 이름: " + ani_quiz_name + " (" + select_quiz.getString("subject") + ")");

            result = "[애니 자음 퀴즈 시즌2]\n - "
                    + ani_quiz_name_consonants
                    + "\n\n * 15분뒤 정답 공개!\n * 띄어쓰기, 대소문자 상관 없음!";

            new Thread() {
                public void run() {
                    String result = null;
                    int count = 0;
                    boolean hint_1 = false, hint_2 = false;

                    try {
                        while (true) {
                            Thread.sleep(100);
                            count++;

                            if (count > (FIFTEEN_MIN_PER_SEC * 10))
                                break;

                            if (count > (FIVE_MIN_PER_SEC * 10) && hint_1 == false) {
                                result = "[애니 자음 퀴즈 시즌2]\n - 힌트 1 : " + select_quiz.getString("genres");
                                KakaoSendReply(result, getSbn());
                                hint_1 = true;
                            }

                            if (count > (TEN_MIN_PER_SEC * 10) && hint_2 == false) {
                                result = "[애니 자음 퀴즈 시즌2]\n - 힌트 2 : " + select_quiz.getString("startDate");
                                KakaoSendReply(result, getSbn());
                                hint_2 = true;
                            }

                            if (ani_quiz_answer_flag == 1)
                                return;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    if (ani_quiz_answer_flag == 0) {
                        ani_quiz_answer_flag = 1;
                        ani_quiz_start = 0;

                        result = "정답은 바로바로 다음과 같습니다!\n\n";
                        try {
                            result += printQuizResult();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                    KakaoSendReply(result, getSbn());
                }
            }.start();

            return result;
        } else {
            result = "이미 퀴즈 진행중이에요!";
        }

        return result;
    }

    public String quizMessage(String msg) throws IOException {
        String ani_quiz_name_consonants = null;
        String result = null;

        if (ani_quiz_start == 0) {
            ani_quiz_start = 1;
            ani_quiz_answer_flag = 0;

            FileLibrary csv = new FileLibrary();
            String allData = csv.ReadAssetsCSV("ani_quiz.csv");
            if (allData == null)
                return null;

            Random random = new Random();
            int rand = random.nextInt(CommandList.RAND_MAX);

            String[] parts = allData.split("\r\n");
            ani_quiz_name = parts[rand % parts.length];

            rand = random.nextInt(CommandList.RAND_MAX);
            int hard_mode = 0;
            if (ani_quiz_name.length() > 2 && rand < (CommandList.RAND_MAX / 4)) {
                hard_mode = 1;
            }

            ani_quiz_name_consonants = convertToConsonants(ani_quiz_name, hard_mode);

            if (hard_mode == 0) {
                result = "맞춰보세요! 15분뒤 정답을 공개!\n - "
                        + ani_quiz_name_consonants;
            } else {
                result = "맞춰보세요! 15분뒤 정답을 공개!\n - "
                        + ani_quiz_name_consonants;
            }

            new Thread() {
                public void run() {
                    String result = null;
                    int count = 0;

                    try {
                        while (true) {
                            Thread.sleep(100);

                            if (count++ > (FIFTEEN_MIN_PER_SEC * 10))
                                break;

                            if (ani_quiz_answer_flag == 1)
                                return;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (ani_quiz_answer_flag == 0) {
                        ani_quiz_answer_flag = 1;
                        ani_quiz_start = 0;
                        result = "정답은 [" + ani_quiz_name + "] 였답니다!";
                    }

                    KakaoSendReply(result, getSbn());
                }
            }.start();

            return result;
        } else {
            result = "이미 퀴즈 진행중이에요!";
        }

        return result;
    }

    public String getPointTitle(int score) {
        String result = "";

        if (score < 2) {
            result += " (칭호 :\uD83D\uDC23)";
        } else if (score < 3) {
            result += " (칭호 :\uD83D\uDC24)";
        } else if (score < 4) {
            result += " (칭호 :\uD83D\uDC25)";
        } else if (score < 5) {
            result += " (칭호 :\uD83C\uDF31)";
        } else if (score < 10) {
            result += " (칭호 :\uD83C\uDF3F)";
        } else if (score < 15) {
            result += " (칭호 :\uD83C\uDF40)";
        } else if (score < 20) {
            result += " (칭호 :\uD83C\uDF37)";
        } else if (score < 25) {
            result += " (칭호 :\uD83C\uDF3A)";
        } else if (score < 30) {
            result += " (칭호 :\uD83E\uDEB7)";
        } else if (score < 40) {
            result += " (칭호 :\uD83E\uDD49)";
        } else if (score < 50) {
            result += " (칭호 :\uD83E\uDD48)";
        } else if (score < 60) {
            result += " (칭호 :\uD83E\uDD47)";
        } else if (score < 70) {
            result += " (칭호 :\uD83C\uDFC5)";
        } else if (score < 80) {
            result += " (칭호 :\uD83C\uDF96)";
        } else if (score < 90) {
            result += " (칭호 :\uD83C\uDFC6)";
        } else if (score < 100) {
            result += " (칭호 :\uD83D\uDDFD)";
        } else {
            result += " (칭호 :\uD83D\uDC79)";
        }

        return result;
    }

    public String answerQuizMessage(String msg, String sender) {
        String result = null;
        String answer;

        if (ani_quiz_start != 0) {
            if (ani_quiz_answer_flag == 0) {
                answer = msg.replaceAll("[^ ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "");
                answer = answer.replaceAll(" ", "");
                answer = answer.toLowerCase();
                if (answer.indexOf(ani_quiz_name) != -1) {
                    ani_quiz_answer_flag = 1;
                    ani_quiz_start = 0;

                    String resultSender = sender;
                    int patternIndex = patternIndexOf(sender, "[0-9`~!@#$%^&*()-_=+\\|\\[\\]{};:'\",.<>/? ]");
                    if (patternIndex != 0) sender = sender.substring(0, patternIndex);

                    total_quiz2_point++;
                    if (player2.containsKey(sender)) {
                        player2.put(sender, player2.get(sender) + 1);
                    } else {
                        player2.put(sender, 1);
                    }

                    FileLibrary csv = new FileLibrary();
                    csv.writePointCSV(ANI_QUIZ2_POINT_FILE_NAME, sender, player2.get(sender));

                    result = resultSender + "님 정답입니다!\n" +
                            " - 누적 점수 : " + player2.get(sender);

                    result += getPointTitle(player2.get(sender));

                    try {
                        result += "\n\n";
                        result += printQuizResult();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    return result;
                }
            }
        }

        return result;
    }

    public String printQuiz2PointList(int top_num) {
        String result = null;
        String result_msg = "";
        int i = 0;

        List<String> keySet = new ArrayList<>(player2.keySet());
        keySet.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return player2.get(o1).compareTo(player2.get(o2));
            }
        });
        keySet.sort((o1, o2) -> player2.get(o2).compareTo(player2.get(o1)));

        if (top_num == 0) {
            for (String key : keySet) {
                result_msg += "\n - " + key + "님의 점수: "
                        + player2.get(key)
                        + getPointTitle(player2.get(key));
            }

            result = "[애니 퀴즈 시즌2 랭킹]\n * 총 점수 : " + total_quiz2_point + "\n"
                    + result_msg;
        } else {
            for (String key : keySet) {
                if (i >= top_num)
                    break;

                result_msg += "\n - " + key + "님의 점수: "
                        + player2.get(key)
                        + getPointTitle(player2.get(key));
                i++;
            }

            result = "[애니 퀴즈 시즌2 랭킹]\n * TOP " + top_num + result_msg;
        }
        return result;
    }

    public String printQuiz1PointList(int top_num) {
        String result = null;
        String result_msg = "";
        int i = 0;

        List<String> keySet = new ArrayList<>(player1.keySet());
        keySet.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return player1.get(o1).compareTo(player1.get(o2));
            }
        });
        keySet.sort((o1, o2) -> player1.get(o2).compareTo(player1.get(o1)));

        if (top_num == 0) {
            for (String key : keySet) {
                result_msg += "\n - " + key + "님의 점수: "
                        + player1.get(key)
                        + getPointTitle(player1.get(key));
            }

            result = "[애니 퀴즈 시즌1 명예의 전당]\n * 총 점수 : " + total_quiz1_point + "\n"
                    + result_msg;
        } else {
            for (String key : keySet) {
                if (i >= top_num)
                    break;

                result_msg += "\n - " + key + "님의 점수: "
                        + player1.get(key)
                        + getPointTitle(player1.get(key));
                i++;
            }

            result = "[애니 퀴즈 시즌1 명예의 전당]\n * TOP " + top_num + result_msg;
        }
        return result;
    }

    public void loadQuiz2PointList() {
        FileLibrary csv = new FileLibrary();
        String allData = csv.ReadCSV(ANI_QUIZ2_POINT_FILE_NAME);
        if (allData == null)
            return;

        String[] parts = allData.split("\n");
        for (String part : parts) {
            String[] data = part.split(",");
            player2.put(data[0], Integer.parseInt(data[1]));
            total_quiz2_point += Integer.parseInt(data[1]);
        }

        Log.d(TAG, "퀴즈2 총 점수 : " + total_quiz2_point);
    }

    public void loadQuizPointList() {
        FileLibrary csv = new FileLibrary();
        String allData = csv.ReadCSV(ANI_QUIZ1_POINT_FILE_NAME);
        if (allData == null)
            return;

        String[] parts = allData.split("\n");
        for (String part : parts) {
            String[] data = part.split(",");
            player1.put(data[0], Integer.parseInt(data[1]));
            total_quiz1_point += Integer.parseInt(data[1]);
        }

        Log.d(TAG, "퀴즈1 총 점수 : " + total_quiz1_point);
    }
}
