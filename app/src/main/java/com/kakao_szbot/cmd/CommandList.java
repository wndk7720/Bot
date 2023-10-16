package com.kakao_szbot.cmd;

import android.util.Log;

import com.kakao_szbot.lib.FileLibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommandList {
    public final static String TAG = "CommandList";

    public final static String BOT_NAME = "를르슈";
    public final static String FAMOUS_MSG = "를르슈 비 브리타니아가 명한다.";
    public final static String BOT_VERSION = "23.10.16";

    public final static int RAND_MAX = 10000;
    public final static int RAND_ANI_MAX = 2500;

    public static List<String>[] BOT_BASIC_CMD;
    public static List<String>[] BOT_BASIC_MSG;

    public static String[][] BOT_BASIC_DEFAULT_CMD = {
            /* help cmd */
            {
                "도움말", "--help", "-h"
            },
            /* welcome cmd */
            {
                "환영"
            },
            /* introduce cmd */
            {
                "소개", "누구"
            },
            /* default cmd */
            {
                BOT_NAME
            },
    };
    public static String[][] BOT_BASIC_DEFAULT_MSG = {
            /* help msg */
            {
                "/*\n * Lelouch Bot\n * Version " + BOT_VERSION + "\n */" +
                "\n\n 「" + BOT_NAME + " + 명령어」 형태로 동작합니다.\n\n" +
                "명령어 목록은 아래와 같습니다.\n" +
                "   - 도움말, -h, --help\n" +
                "   - 환영하기\n   - 날씨\n   - 라면추천\n" +
                "   - 애니추천\n   - 오늘의 애니\n   - 공부하기, 잊어버려, 공부목록\n" +
                "   - 로또번호\n   - 대화요약\n   - 퀴즈, 퀴즈순위\n   - 가챠\n   - 강화\n   - 투자게임\n" +
                "   - 주사위\n   - GPT\n" +
                "\n@github: github.com/wndk7720/Bot.git"
                    // "   - 뭐해   - 아침, 점심, 저녁추천   - 치킨추천   - 비트코인   - 호감도
            },
            /* welcome msg */
            {
                FAMOUS_MSG + "\n\n" +
                "닉네임은 이름/출생연도/최애캐 설정 및 오픈채팅방내의 공지사항 확인해라.\n그리고 간단한 자기소개를 하거라.\n" +
                "(입문작/최애작/최애캐/가장 최근에 본 애니)"
             },
            /* introduce msg */
            {
                FAMOUS_MSG + " 나를 기억해라.",
                "난 제로, 세계를 부수고 세계를 창조할 사나이다.",
                "이름 말인가. 그럼 를르슈 람페르지로부터 따서 L.L.라는 건 어떠냐? ..안 되나?"
            },
            /* default msg */
            {
                "총을 쏴도 되는 건 총에 맞을 각오가 있는 자 뿐이다!",
                "나는 세계를 부수고, 세계를 창조한다.",
                "조건은 클리어되었다.",
                "전략이 전술에 깨져서야 될 것 같나!",
                "나라면 악이 되어 더 큰 악을 칠 것이다!",
                "모두 좋아하잖아? 민주주의는.",
                "자! 민주주의를 시작해볼까?",
                "막고 싶다면 막아봐라 내 절망에 견줄 수 있는 것이 있다면!",
                "세계는 변한다. 바꿔낼 것이다.",
                "써먹을 대로 써먹고 다 쓴 걸레처럼 버려주마!",
                "로로, 너와 나의 관계는 뭐였을까. 난 항상 나중에야 깨닫게 돼. 넌 를르슈 비 브리타니아가 아니라 를르슈 람페르지의 동생이었다는 것을.",
                "를르슈 비 브리타니아가 명한다. 세계여! 나에게 복종하라!",
                "왕부터 움직이지 않으면 부하들이 따라오지 않을 거 아냐.",
                "사람은 왜 거짓말을 하는가, 그것은 누군가와 다투기 위해서만이 아니다. 무언가를 추구하기 때문이다.",
                "디트하르트, 너에겐... 기아스를 걸 가치도 없다.",
                "아니, 좋아진다. 비록 아무리 시간이 걸리더라도... 인간은 끊임없이 행복을 추구하니까.",
                "힘있는 자들이여, 우리를 두려워하라! 힘없는 자들이여, 우리를 갈망하라!"
            },
    };

    public static String[][] COMMON_BASIC_CMD = {
            /* smile cmd */
            {
                    "ㅋㅋㅋㅋ"
            },
            /* go to work cmd */
            {
                    "출근"
            },
            /* leave work cmd */
            {
                    "퇴근"
            },
            /* ohoh cmd */
            {
                    "어우", "오오", "오우", "ㅗㅜㅑ"
            },
            /* .... cmd */
            {
                    "...."
            },
    };

    public static String[][] COMMON_BASIC_MSG = {
            /* smile msg */
            {
                    "ㅋㅋ",
                    "ㅎㅎ"
            },
            /* go to work msg */
            {
                    FAMOUS_MSG + " 돈을 벌어와라.",
                    "힘내라",
                    "화이팅이다"
            },
            /* leave work msg */
            {
                    "고생했다",
                    "수고했다",
                    FAMOUS_MSG + " 쉬어라."
            },
            /* ohoh msg */
            {
                    "호오?",
                    "호오~"
            },
            /* .... msg */
            {
                    FAMOUS_MSG + " 머리를 식혀라.",
                    "그럴수도 있다",
                    "힘내라"
            },
    };

    public static String[] SLANG_CMD = {
            "ㅅㅂ","시발","시빨","씨발","씨빠","씨빨","슈발","싀발","슈빨","쓔발",
            "쓔빨","씌발","싀빨","씌발","ㅆㅃ","ㅅㅃ","ㅆㅃ","ㅅㅍ","시팔","씨팔",
            "ㅄ","ㅂㅅ","ㅂ1ㅅ","병신","븅신","또라이","미친놈","미친넘","미친년",
            "개새끼","뒤1져","좆","ㅅ1ㅂ","ㅅ@ㅂ","시이발","씨댕","개빡","시파",
            "싀파","싀팔","싀바","꺼져","꺼저","도라이","ㅈㄴ","존나","썅"
    };

    public static String[] RAMEN_CMD = {
            "라면추천", "라면 추천"
    };
    public static String[][] RAMEN_CONTENTS_MSG = {
            /* topping msg */
            {
                "계란", "파송송", "치즈", "참치", "삼겹살", "푸아그라", "슈바인학센",
                "글라쉬나", "파전", "취두부", "홍어", "수르스트뢰밍", "멘보샤", "도마뱀",
                "꽃", "향수", "태풍", "고추기름", "물 한사바리", "제로콜라", "장아찌"
            },
            /* ramen msg */
            {
                "신라면", "진라면", "안성탕면", "삼양라면", "너구리", "무파마", "남자라면",
                "꼬꼬면", "진짬뽕", "맛짬뽕", "공화춘", "짜파게티", "짜왕", "팔도비빔면",
                "불닭볶음면", "틈새라면"
            },
    };

    public static String[] WEATHER_CMD = {
            "날씨"
    };

    public static String[] RECOMMEND_ANI_CMD = {
            "애니추천", "만화추천", "애니 추천", "만화 추천", "추천애니", "추천만화",
            "추천 애니", "추천 만화"
    };

    public static String[] TODAY_ANI_CMD = {
            "오늘의 애니", "오늘의애니", "오늘 애니", "오늘애니",
            "오늘의 만화", "오늘의만화", "오늘 만화", "오늘만화"
    };

    public static String[] COIN_CMD = {
            "비트코인", "이더리움", "리플"
    };
    public static String[] COIN_SYMBOL = {
            "BTC", "ETH", "XRP"
    };

    public static String[] LOTTO_CMD = {
            "로또", "lotto", "LOTTO"
    };

    public static String[] STUDY_CMD = {
            "공부하기", "학습하기", "배우기"
    };

    public static String[] STUDY_FORGOT_CMD = {
            "잊어버려"
    };

    public static String[] STUDY_LIST_CMD = {
            "공부목록"
    };

    public static String[] SAMPLING_CMD = {
            "요약"
    };

    public static String[] QUIZ_CMD = {
            "퀴즈"
    };
    public static String[] QUIZ_POINT_CMD = {
            "퀴즈순위", "퀴즈 순위"
    };

    public static String[] GACHA_CMD = {
            "가챠"
    };

    public static String[] REINFORCE_CMD = {
            "강화"
    };

    public static String[] INVEST_CMD = {
            "투자게임", "투자 게임"
    };

    public static String[] DICE_CMD = {
            "주사위", "다이스", "dice"
    };

    public static String[] LOVE_LIST_CMD = {
            "호감도"
    };

    public static String[] GPT_CMD = {
            "GPT", "gpt"
    };

    public static String[] SURVIVAL_CMD = {
            "전쟁"
    };

    public static String[] SURVIVAL_BETTING_CMD = {
            "최애"
    };

    public static String[] DEV_DEBUG_CSV_CMD = {
            "개발자_전용"
    };

    public int loadBotBasicCommand() {
        FileLibrary csv = new FileLibrary();
        String allData = null;
        try {
            allData = csv.ReadAssetsCSV("basic_command.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (allData == null)
            return -1;


        String[] parts = allData.split("\r\n");
        int parts_length = 0; // parts.length
        BOT_BASIC_CMD = new ArrayList[(parts_length / 2) + BOT_BASIC_DEFAULT_CMD.length];
        BOT_BASIC_MSG = new ArrayList[(parts_length / 2) + BOT_BASIC_DEFAULT_MSG.length];

        /* CSV Command Load */

        int cmd_index = 0, line_index = 0;
        /*
        for (String part : parts) {
            String[] data = part.split(",");

            if (line_index % 2 == 0) {
                BOT_BASIC_CMD[cmd_index] = new ArrayList<String>();
                for (int j = 0; j < data.length; j++) {
                    BOT_BASIC_CMD[cmd_index].add(data[j]);
                }
            } else {
                BOT_BASIC_MSG[cmd_index] = new ArrayList<String>();
                for (int j = 0; j < data.length; j++) {
                    BOT_BASIC_MSG[cmd_index].add(data[j]);
                }

                cmd_index++;
            }

            line_index++;
        }
        */

        /* Default Command Load */
        for (int i = 0; i < BOT_BASIC_DEFAULT_CMD.length; i++) {
            BOT_BASIC_CMD[cmd_index] = new ArrayList<String>();
            for (int j = 0; j < BOT_BASIC_DEFAULT_CMD[i].length; j++) {
                BOT_BASIC_CMD[cmd_index].add(BOT_BASIC_DEFAULT_CMD[i][j]);
            }

            BOT_BASIC_MSG[cmd_index] = new ArrayList<String>();
            for (int j = 0; j < BOT_BASIC_DEFAULT_MSG[i].length; j++) {
                BOT_BASIC_MSG[cmd_index].add(BOT_BASIC_DEFAULT_MSG[i][j]);
            }

            cmd_index++;
        }

        return 0;
    }
}
