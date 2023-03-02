package com.kakao_szbot.cmd;

import static com.kakao_szbot.KakaoNotificationListener.KakaoSendReply;
import static com.kakao_szbot.KakaoNotificationListener.getSbn;

import android.util.Log;

import com.kakao_szbot.R;
import com.kakao_szbot.csv.LibraryCSV;

import java.io.IOException;
import java.util.Random;

public class CommandQuiz {
    public final static String TAG = "CommandQuiz";
    private static int TEN_MIN_PER_SEC = 600;

    public static String ani_quiz_name;
    public static int ani_quiz_start = 0;
    public static int ani_quiz_answer_flag = 1;


    public static String convertToConsonants(String word) {
        final int BASE_CODE = 44032; // The base code of Hangul syllables
        final int CHOSUNG_INTERVAL = 588; // The interval between initial consonants
        final char[] CHOSUNG_LIST = {
                'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ',
                'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
        }; // The list of initial consonants

        StringBuilder result = new StringBuilder();
        for (char ch : word.toCharArray()) {
            if (ch >= '가' && ch <= '힣') { // Check if the character is a Hangul syllable
                int index = ch - BASE_CODE; // Calculate the index of the syllable
                int chosungIndex = index / CHOSUNG_INTERVAL; // Calculate the index of the initial consonant
                result.append(CHOSUNG_LIST[chosungIndex]); // Add the initial consonant to the result
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

            LibraryCSV csv = new LibraryCSV();
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
                    "\n(띄어쓰기 없이 입력해주세요!)";

            new Thread() {
                public void run() {
                    String result = null;
                    int count = 0;

                    try {
                        while (true) {
                            Thread.sleep(100);

                            if (count++ > 10000)
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
                    result = sender +"님 정답입니다!";
                    ani_quiz_answer_flag = 1;
                    ani_quiz_start = 0;
                    return result;
                }
            }
        }

        return result;
    }
}
