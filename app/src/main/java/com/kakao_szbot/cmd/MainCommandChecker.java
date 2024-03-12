package com.kakao_szbot.cmd;

import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.util.List;


public class MainCommandChecker {
    public final static String TAG = "CommandChecker";

    public String checkKakaoMessage(String msg, String sender, String room, StatusBarNotification sbn) {
        Log.d(TAG, "checkKakaoMessage ~ " + sender + ": " + msg);
        String replyMessage = null;

        /*
        replyMessage = specialRoomMessage(msg, sender, room);
        if (replyMessage != null) {
            return replyMessage;
        }
        */

        replyMessage = highPriorityMessage(msg, sender);
        if (replyMessage != null) {
            return replyMessage;
        }

        if (msg.contains(CommandList.BOT_NAME)) {
            replyMessage = selectBotMessage(msg, sender, sbn);
        } else {
            if (room.indexOf("다덕임") != -1) {
                replyMessage = selectNormalMessage(msg, sender);
            }
        }

        return replyMessage;
    }

    public String checkPersonalKakaoMessage(String msg, String sender, StatusBarNotification sbn) {
        Log.d(TAG, "checkPersonalKakaoMessage ~ " + sender + ": " + msg);
        String replyMessage = null;

        //replyMessage = new CommandSurvival().mainSurvivalCommand(msg, sender, sbn);
        replyMessage = new CommandTower().mainTowerCommand(msg, sender, sbn);
        return replyMessage;
    }

    private String selectBotMessage(String msg, String sender, StatusBarNotification sbn) {
        String replyMessage = null;

        try {
            if (checkCommnadList(msg, CommandList.SURVIVAL_CMD) == 0) {
                if (sender.indexOf("방장") >= 0) {
                    replyMessage = new CommandSurvival().startSurvivalCommand(sbn);
                    return replyMessage;
                }
            }
            if (checkCommnadList(msg, CommandList.TOWER_CMD) == 0) {
                if (sender.indexOf("방장") >= 0) {
                    replyMessage = new CommandTower().startTowerCommand(sbn);
                    return replyMessage;
                }
            }
            if (checkCommnadList(msg, CommandList.SURVIVAL_BETTING_CMD) == 0) {
                replyMessage = new CommandSurvival().voteServant(msg, sender);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.GPT_CMD) == 0) {
                replyMessage = new CommandGPT().gptMessage(msg, sender);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.RAMEN_CMD) == 0) {
                replyMessage = new CommandBasic().ramenMessage(msg);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.LOTTO_CMD) == 0) {
                replyMessage = new CommandBasic().lottoMessage(msg);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.EXCHANGE_RATE_CMD) == 0) {
                replyMessage = new CommandCrawling().exchangeRateMessage(msg, sender);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.COIN_CMD) == 0) {
                replyMessage = new CommandCrawling().coinMessage(msg, sender);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.WEATHER_CMD) == 0) {
                replyMessage = new CommandCrawling().weatherMessage(msg, sender);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.RECOMMEND_ANI_CMD) == 0) {
                replyMessage = new CommandCrawling().recommendAniMessage(msg, sender);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.TODAY_ANI_CMD) == 0) {
                replyMessage = new CommandCrawling().todayAniMessage(msg, sender);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.STUDY_CMD) == 0) {
                replyMessage = new CommandStudy().studyMessage(msg);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.STUDY_FORGOT_CMD) == 0) {
                replyMessage = new CommandStudy().forgotStudyMessage(msg);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.STUDY_LIST_CMD) == 0) {
                replyMessage = new CommandStudy().getStudyMessage();
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.SAMPLING_CMD) == 0) {
                //replyMessage = new CommandSampling().samplingMessage(msg);
                replyMessage = new CommandSampling().samplingGptMessage(msg);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.QUIZ1_POINT_CMD) == 0) {
                replyMessage = new CommandQuiz().printQuiz1PointList(0);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.QUIZ2_POINT_CMD) == 0) {
                replyMessage = new CommandQuiz().printQuiz2PointList(0);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.QUIZ_CMD) == 0) {
                //replyMessage = new CommandQuiz().quizMessage(msg);
                replyMessage = new CommandQuiz().quiz2Message(msg);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.GACHA_CMD) == 0) {
                replyMessage = new CommandGacha().gachaMessage(msg);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.REINFORCE_CMD) == 0) {
                replyMessage = new CommandReinforce().reinforceMessage(msg);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.DICE_CMD) == 0) {
                replyMessage = new CommandBasic().diceMessage(msg);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandList.INVEST_CMD) == 0) {
                replyMessage = new CommandInvest().investMessage(msg);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandInvest.INVEST_PURCHASE_CMD) == 0) {
                replyMessage = new CommandInvest().investPurchaseMessage(msg, sender);
                return replyMessage;
            }
            /*
            if (checkCommnadList(msg, CommandList.LOVE_LIST_CMD) == 0) {
                replyMessage = new CommandLovePoint().printLovePointList();
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandLovePoint.POINT_DOWM_CMD) == 0) {
                replyMessage = new CommandLovePoint().downLovePointMessage(msg, sender);
                return replyMessage;
            }
            if (checkCommnadList(msg, CommandLovePoint.POINT_UP_CMD) == 0) {
                replyMessage = new CommandLovePoint().upLovePointMessage(msg, sender);
                return replyMessage;
            }
            */

            for (int i = 0; i < (CommandList.BOT_BASIC_CMD.length - 1); i++) {
                if (checkCommnadArrayList(msg, CommandList.BOT_BASIC_CMD[i]) == 0) {
                    replyMessage = new CommandBasic().basicMessage(CommandList.BOT_BASIC_MSG[i]);
                    return replyMessage;
                }
            }

            replyMessage = new CommandGPT().gptBotMessage(msg, sender);
            if (replyMessage != null) {
                return replyMessage;
            }

            for (int i = (CommandList.BOT_BASIC_CMD.length - 1); i < CommandList.BOT_BASIC_CMD.length; i++) {
                if (checkCommnadArrayList(msg, CommandList.BOT_BASIC_CMD[i]) == 0) {
                    replyMessage = new CommandBasic().basicMessage(CommandList.BOT_BASIC_MSG[i]);
                    return replyMessage;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return replyMessage;
    }

    private String selectNormalMessage(String msg, String sender) {
        String replyMessage = null;

        //new CommandSampling().storeSamplingMessage(msg);
        new CommandSampling().storeSamplingAllMessage(msg);

        replyMessage = new CommandQuiz().answerQuizMessage(msg, sender);
        if (replyMessage != null)
            return replyMessage;

        for (int i = 0; i < CommandList.COMMON_BASIC_CMD.length; i++) {
            if (checkCommnadList(msg, CommandList.COMMON_BASIC_CMD[i]) == 0) {
                replyMessage = new CommandBasic().sometimesMessage(CommandList.COMMON_BASIC_MSG[i]);
                break;
            }
        }
        if (replyMessage != null)
            return replyMessage;

        replyMessage = new CommandGPT().gptSometimesMessage(msg, sender);
        if (replyMessage != null)
            return replyMessage;

        replyMessage = new CommandStudy().checkStudyMessage(msg);

        return replyMessage;
    }

    private String highPriorityMessage(String msg, String sender) {
        String replyMessage = new CommandBasic().slangMessage(msg, CommandList.SLANG_CMD);
        return replyMessage;
    }

    private String specialRoomMessage(String msg, String sender, String room) {
        String replyMessage = null;

        if (room.indexOf("고독") != -1) {
            if (msg.indexOf("이모티콘") != -1 || msg.indexOf("사진") != -1) {
                return null;
            }

            replyMessage = "채팅 금지입니다.. 사진 또는 이모티콘만 올려주세요..";
        }

        return replyMessage;
    }

    static int checkCommnadList(String msg, String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (msg.indexOf(list[i]) != -1) {
                return 0;
            }
        }

        return -1;
    }

    static int checkCommnadArrayList(String msg, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (msg.indexOf(list.get(i)) != -1) {
                return 0;
            }
        }

        return -1;
    }
}
