package com.kakao_szbot.cmd;

import android.util.Log;


public class MainCommandChecker {
    public final static String TAG = "CommandChecker";


    public String checkKakaoMessage(String msg, String sender) {
        Log.d(TAG, "checkKakaoMessage ~ " + sender + ": " + msg);
        String replyMessage = null;

        if (msg.contains(CommandList.BOT_NAME)) {
            replyMessage = selectBotMessage(msg, sender);
        } else {
            //replyMessage = selectNormalMessage(msg, sender);
        }

        return replyMessage;
    }

    private String selectBotMessage(String msg, String sender) {
        String replyMessage = null;

        for (int i = 0; i < CommandList.BASIC_CMD.length; i++) {
            if (checkCommnadList(msg, CommandList.BASIC_CMD[i]) == 0) {
                replyMessage = new CommandBasic().basicMessage(CommandList.BASIC_MSG[i]);
                break;
            }
        }

        return replyMessage;
    }

    private String selectNormalMessage(String msg, String sender) {
        String replyMessage = new CommandBasic().echoMessage(msg, sender);
        return replyMessage;
    }

    private int checkCommnadList(String msg, String[] list) {
        for (int i=0; i < list.length; i++) {
            if (msg.indexOf(list[i]) != -1) {
                return 0;
            }
        }

        return -1;
    }
}