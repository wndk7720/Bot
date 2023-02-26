package com.kakao_szbot.cmd;

import android.util.Log;


public class MainCommandChecker {
    public final static String TAG = "CommandChecker";


    public String checkKakaoMessage(String msg, String sender) {
        Log.d(TAG, "checkKakaoMessage ~ " + sender + ": " + msg);
        String replyMessage = null;


        replyMessage = highPriorityMessage(msg, sender);
        if (replyMessage != null) {
            return replyMessage;
        }

        if (msg.contains(CommandList.BOT_NAME)) {
            replyMessage = selectBotMessage(msg, sender);
        } else {
            replyMessage = selectNormalMessage(msg, sender);
        }

        return replyMessage;
    }

    private String selectBotMessage(String msg, String sender) {
        String replyMessage = null;

        if (checkCommnadList(msg, CommandList.COIN_CMD) == 0) {
            try {
                replyMessage = new CommandCrawling().coinMessage(msg, sender);
                return replyMessage;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < CommandList.BOT_BASIC_CMD.length; i++) {
            if (checkCommnadList(msg, CommandList.BOT_BASIC_CMD[i]) == 0) {
                replyMessage = new CommandBasic().basicMessage(CommandList.BOT_BASIC_MSG[i]);
                return replyMessage;
            }
        }

        return replyMessage;
    }

    private String selectNormalMessage(String msg, String sender) {
        String replyMessage = null;

        for (int i = 0; i < CommandList.COMMON_BASIC_CMD.length; i++) {
            if (checkCommnadList(msg, CommandList.COMMON_BASIC_CMD[i]) == 0) {
                replyMessage = new CommandBasic().sometimesMessage(CommandList.COMMON_BASIC_MSG[i]);
                break;
            }
        }

        return replyMessage;
    }

    private String highPriorityMessage(String msg, String sender) {
        String replyMessage = new CommandBasic().slangMessage(msg, CommandList.SLANG_CMD);
        return replyMessage;
    }

    private int checkCommnadList(String msg, String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (msg.indexOf(list[i]) != -1) {
                return 0;
            }
        }

        return -1;
    }
}
