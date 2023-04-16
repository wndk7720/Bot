package com.kakao_szbot.cmd;

import static com.kakao_szbot.KakaoNotificationListener.KakaoSendReply;
import static com.kakao_szbot.KakaoNotificationListener.getSbn;
import static com.kakao_szbot.lib.CommonLibrary.patternIndexOf;

import android.util.Log;

import com.kakao_szbot.lib.FileLibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CommandQuiz {
    public final static String TAG = "CommandQuiz";
    private static int FIFTEEN_MIN_PER_SEC = 900;
    private static int BLIND_FREQUENCY = 7;

    public static String ani_quiz_name;
    public static int ani_quiz_start = 0;
    public static int ani_quiz_answer_flag = 1;

    private static Map<String, Integer> player = new HashMap<>();


    public static String convertToConsonants(String word) {
        final int BASE_CODE = 44032; // The base code of Hangul syllables
        final int CHOSUNG_INTERVAL = 588; // The interval between initial consonants
        final char[] CHOSUNG_LIST = {
                'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ',
                'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
        }; // The list of initial consonants
        Random random = new Random();
        int rand = random.nextInt(CommandList.RAND_MAX);
        int i = 0;

        rand %= BLIND_FREQUENCY;
        rand %= word.length();

        StringBuilder result = new StringBuilder();
        for (char ch : word.toCharArray()) {
            if (ch >= '가' && ch <= '힣') { // Check if the character is a Hangul syllable
                int index = ch - BASE_CODE; // Calculate the index of the syllable
                int chosungIndex = index / CHOSUNG_INTERVAL; // Calculate the index of the initial consonant

                if (i != rand) {
                    result.append(CHOSUNG_LIST[chosungIndex]); // Add the initial consonant to the result
                } else {
                    result.append('■'); // Add the initial consonant to the result
                }

                if ((++i % BLIND_FREQUENCY) == 0) {
                    i = 0;
                }
            } else {
                result.append(ch); // Add non-Hangul characters to the result as is
            }
        }
        return result.toString();
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
            ani_quiz_name_consonants = convertToConsonants(ani_quiz_name);

            result = "맞춰보세요!\n\n"
                    + ani_quiz_name_consonants +
                    "\n(띄어쓰기 없이 입력해주세요! 15분뒤 정답 공개!)";

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
                        result = "정답은 [" + ani_quiz_name + "] 입니다!";
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

    public String answerQuizMessage(String msg, String sender) {
        String result = null;

        if (ani_quiz_start != 0) {
            if (ani_quiz_answer_flag == 0) {
                if (msg.indexOf(ani_quiz_name) != -1) {
                    ani_quiz_answer_flag = 1;
                    ani_quiz_start = 0;

                    String resultSender = sender;
                    int patternIndex = patternIndexOf(sender, "[0-9`~!@#$%^&*()-_=+\\|\\[\\]{};:'\",.<>/? ]");
                    if (patternIndex != 0) sender = sender.substring(0, patternIndex);

                    if (player.containsKey(sender)) {
                        player.put(sender, player.get(sender) + 1);
                    } else {
                        player.put(sender, 1);
                    }

                    FileLibrary csv = new FileLibrary();
                    csv.writePointCSV("quizPointList.csv", sender, player.get(sender));

                    result = resultSender + "님 정답입니다!\n" +
                            " - 누적 점수 : " + player.get(sender);

                    return result;
                }
            }
        }

        return result;
    }

    public String printQuizPointList() {
        String result = null;
        String result_msg = "";

        List<String> keySet = new ArrayList<>(player.keySet());
        keySet.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return player.get(o1).compareTo(player.get(o2));
            }
        });
        keySet.sort((o1, o2) -> player.get(o2).compareTo(player.get(o1)));

        for (String key : keySet) {
            result_msg += "\n - " + key + "님의 점수: "
                    + player.get(key);
        }

        result = "[애니 퀴즈 명예의 전당]" + result_msg;
        return result;
    }

    public void loadQuizPointList() {
        FileLibrary csv = new FileLibrary();
        String allData = csv.ReadCSV("quizPointList.csv");
        if (allData == null)
            return;

        String[] parts = allData.split("\n");
        for (String part : parts) {
            String[] data = part.split(",");
            player.put(data[0], Integer.parseInt(data[1]));
        }
    }
}
