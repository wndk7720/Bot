package com.kakao_szbot.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommandSampling {
    public final static String TAG = "CommandSampling";
    private static int SAMPLING_THRESHOLD = 5;
    private static int SAMPLING_RECENT_THRESHOLD = 10;
    private static int SAMPLING_DATA_MAX = 100;
    public static int sampling_index = 0;
    public static List<String> sampling_data = new ArrayList<String>();
    public static String[] sampling_exception =
            {"ㅋ", "ㅎ", "이모티콘", "사진",
            CommandList.BOT_NAME[0], CommandList.BOT_NAME[1]};


    public String getRecentMessage() {
        String data = "";

        if (sampling_data.size() < SAMPLING_RECENT_THRESHOLD) {
            return data;
        }

        for (int i = sampling_data.size() - SAMPLING_RECENT_THRESHOLD;
                 i < sampling_data.size();
                 i++) {
            data += sampling_data.get(i) + "\n";
        }

        return data;
    }

    public String samplingGptMessage(String msg) {
        String result = null;
        String data = "";

        if (sampling_data.size() < 5) {
            result = "대화기록이 부족하다!";
            return result;
        }

        for (int i = 0; i < sampling_data.size(); i++) {
            data += sampling_data.get(i) + "\n";
        }

        result = "최근 대화 요약이다!\n\n" +
                new CommandGPT().gptConversationSummary(data);

        return result;
    }

    public void storeSamplingAllMessage(String msg) {
        String store_data;

        for (int i = 0; i < sampling_exception.length; i++) {
            if (msg.indexOf(sampling_exception[i]) == 0) {
                return;
            }
        }

        if (sampling_data.size() > SAMPLING_DATA_MAX) {
            sampling_data.remove(0);
        }

        store_data = msg;
        sampling_data.add(store_data);
    }

    public String samplingMessage(String msg) {
        String result = null;

        if (sampling_data.size() < 5) {
            result = "대화기록이 부족하다!";
            return result;
        }

        Random random = new Random();
        int rand = random.nextInt(CommandList.RAND_MAX);
        rand = rand % (sampling_data.size() - 3);

        result = "최근 대화 요약이다!\n\n" +
                sampling_data.get(rand) + "\n" +
                sampling_data.get(rand + 1) + "\n" +
                sampling_data.get(rand + 2);

        return result;
    }

    public void storeSamplingMessage(String msg) {
        String store_data;

        sampling_index++;

        if (sampling_index > SAMPLING_THRESHOLD) {
            for (int i=0; i < sampling_exception.length; i++) {
                if (msg.indexOf(sampling_exception[i]) == 0) {
                    return;
                }
            }

            if (sampling_data.size() > SAMPLING_DATA_MAX) {
                sampling_data.remove(0);
            }
            store_data = "??? : " + msg;
            sampling_data.add(store_data);
            sampling_index = 0;
        }
    }
}
