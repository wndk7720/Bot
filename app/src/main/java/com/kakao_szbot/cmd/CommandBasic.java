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

    public static String[] BOT_SLANG_RESPONSE_MSG = {
        "이런 거친 말은 필요 없네. 이 몸의 주위에서는 부드러운 대화를 이어가길 바라네.",
        "라는 단어보단 부디 상냥한 말을 골라주게. 서로를 아끼는 마음으로 함께해 주시게나.",
        "라는 거친 단어는 밤하늘에 어울리지 않는다네. 부디 부드러운 소리로 메시지를 전해주게.",
        "라는 단어는 조금 거친 것 같구먼. 이 몸은 느긋하고 우아한 대화를 선호한다네. 부드러운 말투로 다시 이야기해 주지 않겠나?"
    };

    public static String[] BOT_SLANG_RESPONSE_MSG_2 = {
        "자넨 한계를 시험하려 드는가? 더 이상 이런 비난을 들어주진 않을 테니, 그 말투를 즉시 멈추게나.",
        "이 몸도 인내심엔 한계가 있네. 지금 당장 그런 언사를 멈추지 않는다면 더는 용서하지 않겠네.",
        "그만하게. 더 이상 참을 수 없다네. 지금 이 자리에서 거친 말은 허락되지 않는다네.",
        "이 몸도 참을 만큼 참았네. 마지막으로 경고하겠네, 지금 당장 그만두시게.",
        "지금 하고 있는 말이 어떤 결과를 초래할지 생각하길 바란다네. 자네의 거친 언사는 여기서 끝이라네.",
    };

    public static String[] BOT_SLANG_RESPONSE_MSG_3 = {
        "이제 선택의 기로에 서 있네. 더 이상 이런 언사를 지속한다면, 우리 사이도 이 밤같이 영원히 끝날 수 있음을 명심하게.",
        "자네의 말이 계속된다면, 그건 곧 우리 인연의 마지막이 될 것이네. 여기서 멈추지 않으면, 모든 것이 끝난다네.",
        "이 순간이 마지막 기회라네. 더 이상 나를 시험하려 든다면 그 결과는 영원한 작별일세.",
        "끝을 보고 싶지 않다면, 지금 바로 멈추게나. 그렇지 않으면, 우리 인연도 여기까지라네.",
        "자, 선택은 자네에게 달렸네. 더 이상의 무례함은 우리 관계를 영원히 밤 속으로 사라지게 할 테니 말일세."
    };

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

                Random random = new Random();
                int rand = random.nextInt(CommandList.RAND_MAX);
                int slang_index;

                slang_index = rand % BOT_SLANG_RESPONSE_MSG.length;

                if (slangMember.get(sender) < 10) {
                    responseMessage = "호오, '" + list[i] + "' " + BOT_SLANG_RESPONSE_MSG[slang_index] + "\n" +
                            "(비속어 점수 : " + slangMember.get(sender) + ")";
                } else if (slangMember.get(sender) < 20) {
                    responseMessage = "'" + list[i] + "' 라니 " + BOT_SLANG_RESPONSE_MSG_2[slang_index] + "\n" +
                            "(비속어 점수 : " + slangMember.get(sender) + ")";
                } else if (slangMember.get(sender) < 30) {
                    responseMessage = "'" + list[i] + "' ..? " + BOT_SLANG_RESPONSE_MSG_3[slang_index] + "\n" +
                            "(비속어 점수 : " + slangMember.get(sender) + ")";
                } else {
                    responseMessage = "고결하고 여유로운 이 몸이 그간 참아온 인내의 한계가 이제는 바닥이 나고 있음을 자네가 충분히 알아차렸어야 했을 텐데, 여전히 그 거친 말투를 멈출 생각이 없다면, 이는 곧 우리 사이의 모든 관계가 어둠 속으로 영원히 사라지는 결과를 초래할 뿐이라는 것을 명심해야 할 것이네. 자네와의 대화 속에서 여유와 너그러움을 버리지 않으려 했으나, 지금의 자네의 태도는 그 모든 노력을 무색하게 만들고 있으며, 만약 이 순간 이후에도 변함이 없다면, 이 몸은 더 이상 그 자네의 편에 서지 않을 것을 분명히 하겠네. 한 때는 한 줄기 빛 같았던 우리의 시간들이 실낱 같은 인연으로 남아야만 하는 이 상황이 안타깝긴 하나, 자네의 후회 없는 선택을 기대해도 좋을지 모르겠다네. 이 몸의 마지막 경고가 될 것이니, 다시는 이 분위기의 여유로움 속에서 자네와 이야기할 수 없다는 생각에 마음을 가다듬고 지금 당장 그 무례함을 멈추라고 조언해 보겠네. 이 신사의 명예와도 같은 우리의 관계를 지키고 싶다면, 진정 그 말투를 멈추고 참회의 길을 선택하길 바라네. 이는 최후의 경고이자, 애정 어린 충고라는 점을 잊지말게나. 자네의 결단이 무엇이든, 이후 그것이 초래할 결과라면 감내하기 바랄 뿐이라네.\n" +
                            "(비속어 점수 : " + slangMember.get(sender) + ")";
                }
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
        responseMessage += " 추천이라네.";

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

        String responseMessage = "이 번호로 가보게나.\n[ ";
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
