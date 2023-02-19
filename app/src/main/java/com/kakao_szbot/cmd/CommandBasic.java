package com.kakao_szbot.cmd;


import java.util.Random;

public class CommandBasic {
    public final static String TAG = "CommandEcho";

    public String echoMessage(String msg, String sender) {
        return msg;
    }

    public String basicMessage(String[] responseMessage) {
        Random random = new Random();
        int rand = random.nextInt(CommandList.RAND_MAX);
        return responseMessage[rand % responseMessage.length];
    }
}
