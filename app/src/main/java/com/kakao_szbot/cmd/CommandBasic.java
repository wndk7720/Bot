package com.kakao_szbot.cmd;


import static com.kakao_szbot.lib.CommonLibrary.findNum;
import static com.kakao_szbot.lib.CommonLibrary.patternIndexOf;

import android.util.Log;

import com.kakao_szbot.lib.FileLibrary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CommandBasic {
    public final static String TAG = "CommandEcho";
    public final static int percent = CommandList.RAND_MAX / 5;

    public final static String ANI_SLANG_POINT_FILE_NAME = "slangPointList.csv";
    public static int total_slang_point = 0;
    private static Map<String, Integer> slangMember = new HashMap<>();


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

    public String slangMessage(String msg, String sender, String[] list) {
        String responseMessage = null;

        for (int i = 0; i < list.length; i++) {
            if (msg.indexOf(list[i]) != -1) {
                int patternIndex = patternIndexOf(sender, "[0-9`~!@#$%^&*()-_=+\\|\\[\\]{};:'\",.<>/? ]");
                if (patternIndex != 0) sender = sender.substring(0, patternIndex);

                total_slang_point++;
                if (slangMember.containsKey(sender)) {
                    slangMember.put(sender, slangMember.get(sender) + 1);
                } else {
                    slangMember.put(sender, 1);
                }

                FileLibrary csv = new FileLibrary();
                csv.writePointCSV(ANI_SLANG_POINT_FILE_NAME, sender, slangMember.get(sender));
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
        responseMessage += " 추천!";

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

        String responseMessage = "확신! 이 번호다!\n[ ";
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

        result = "주사위 결과는 " + randNum + "!";

        return result;
    }

    public void loadSlangPointList() {
        FileLibrary csv = new FileLibrary();
        String allData = csv.ReadCSV(ANI_SLANG_POINT_FILE_NAME);
        if (allData == null)
            return;

        String[] parts = allData.split("\n");
        for (String part : parts) {
            String[] data = part.split(",");
            slangMember.put(data[0], Integer.parseInt(data[1]));
            total_slang_point += Integer.parseInt(data[1]);
        }

        Log.d(TAG, "슬랭 총 점수 : " + total_slang_point);
    }
}
