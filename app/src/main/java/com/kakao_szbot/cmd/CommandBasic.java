package com.kakao_szbot.cmd;


import android.util.Log;

import java.util.Random;

public class CommandBasic {
    public final static String TAG = "CommandEcho";
    public final static int percent = CommandList.RAND_MAX / 5;

    public String echoMessage(String msg, String sender) {
        return msg;
    }

    public String basicMessage(String[] responseMessage) {
        Random random = new Random();
        int rand = random.nextInt(CommandList.RAND_MAX);
        return responseMessage[rand % responseMessage.length];
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
                responseMessage = "'" + list[i] + "' 이런 말 쓰면 범인입니다";
            }
        }

        return responseMessage;
    }
}
