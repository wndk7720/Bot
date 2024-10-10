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
                responseMessage = "쵸왓!? '" + list[i] + "' 라는 단어 맙소사!?";
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
        responseMessage += " " + CommandList.BOT_FAMOUS_MSG;

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

        String responseMessage = "이 번호로 " + CommandList.BOT_FAMOUS_MSG + "\n[ ";
        for (int i = 0; i < LOTTO_NUM_MAX; i++) {
            responseMessage += num[i] + " ";
        }
        responseMessage += "]";

        return responseMessage;
    }

    /* diceMessage
     * 기능 추가 like DnD 주사위 by ygh 2024-01-25
     * 사용 예시 : "봇이름 주사위" 2d20+3 -> 20눈 주사위 2번 굴려 + 3
     */ 
    public String diceMessage(String msg) {
        String result = null;
        String diceStr = msg.toLowerCase().replaceAll("[^0-9d\\+]", "");
        int dice = 1; // 주사위 갯수
        int number = 6; // 주사위 눈
        int add = 0; // 보너스

        String tmp = "";
	    int dice_index = diceStr.indexOf("d");
	    int add_index = diceStr.indexOf("+");

	    try {
            if (dice_index != -1) {
                tmp = diceStr.substring(0, dice_index);
                if (tmp.equals("")) tmp = "1";
                dice = Integer.parseInt(tmp);
            }
            
            tmp = diceStr.substring((dice_index != -1) ? (dice_index + 1) : 0, (add_index != -1) ? add_index : diceStr.length());
            number = Integer.parseInt(tmp);
            
            if (add_index != -1) {
                tmp = diceStr.substring(add_index);
                add = Integer.parseInt(tmp);
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            // do nothing
        }

        Random random = new Random();
        int randNum = add;
        for (int i=0; i < dice; i++) {
            randNum += random.nextInt(number) + 1;
        }

        result = "주사위 결과는 " + randNum + " 입니다!";

        return result;
    }
}
