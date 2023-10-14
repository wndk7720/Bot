package com.kakao_szbot.cmd;


import static com.kakao_szbot.lib.CommonLibrary.findNum;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CommandBasic {
    public final static String TAG = "CommandEcho";
    public final static int percent = CommandList.RAND_MAX / 5;

    public String echoMessage(String msg, String sender) {
        return msg;
    }

    public String basicMessage(List<String> responseMessage) {
        Random random = new Random();
        int rand = random.nextInt(CommandList.RAND_MAX);
        return responseMessage.get(rand % responseMessage.size());
    }

    public String sometimesMessage(String[] responseMessage) {
        Random random = new Random();
        int rand = random.nextInt(CommandList.RAND_MAX);
        if (rand > percent) {
            return null;
        }
        return responseMessage[rand % responseMessage.length];
    }

    public String slangMessage(String msg, String[] list) {
        String responseMessage = null;

        for (int i = 0; i < list.length; i++) {
            if (msg.indexOf(list[i]) != -1) {
                responseMessage = CommandList.FAMOUS_MSG + " '" + list[i] + "' 단어를 쓰지말라.";
            }
        }

        return responseMessage;
    }

    public String ramenMessage(String msg) {
        Random random = new Random();
        int toppingRand = random.nextInt(CommandList.RAMEN_CONTENTS_MSG[0].length);
        int ramenRand = random.nextInt(CommandList.RAMEN_CONTENTS_MSG[1].length);
        String responseMessage = null;

        responseMessage = CommandList.RAMEN_CONTENTS_MSG[0][toppingRand];
        responseMessage += " 넣은 ";
        responseMessage += CommandList.RAMEN_CONTENTS_MSG[1][ramenRand];
        responseMessage += " 추천한다.";

        return responseMessage;
    }

    public String lottoMessage(String msg) {
        int LOTTO_NUM_MAX = 6;
        int LOTTO_RAND_MAX = 45;

        Random random = new Random();
        int rand = 0;
        int[] num = {0,0,0,0,0,0};

        int lotto_index = 0;
        int flag = 0;

        while (lotto_index < LOTTO_NUM_MAX) {
            rand = random.nextInt(LOTTO_RAND_MAX) + 1;
            flag = 0;

            for (int i = 0; i < LOTTO_NUM_MAX; i++) {
                if (num[i] == rand) {
                    flag = 1;
                }
            }

            if (flag == 0) {
                num[lotto_index++] = rand;
            }
        }

        Arrays.sort(num);

        String responseMessage = CommandList.FAMOUS_MSG + " 이 번호로 사라.\n[ ";
        for (int i = 0; i < LOTTO_NUM_MAX; i++) {
            responseMessage += num[i] + " ";
        }
        responseMessage += "]";

        return responseMessage;
    }

    public String diceMessage(String msg) {
        String result = null;
        int maxNum = findNum(msg);
        if (maxNum == 0) maxNum = 6;

        Random random = new Random();
        int randNum = random.nextInt(maxNum) + 1;
        result = "주사위 결과는 " + randNum + " 이다.";

        return result;
    }
}
