package com.kakao_szbot.cmd;

import android.util.Log;

import com.kakao_szbot.lib.FileLibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommandList {
    public final static String TAG = "CommandList";

    public final static String BOT_NAME = "쿄코";
    public final static String BOT_VERSION = "23.04.07";

    public final static int RAND_MAX = 10000;
    public final static int RAND_ANI_PAGE_MAX = 89;

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
                "/*\n * Kyouko Bot\n * Version " + BOT_VERSION + "\n */" +
                "\n\n 「쿄코 + 명령어」 형태로 동작합니다.\n\n" +
                "명령어 목록은 아래와 같습니다.\n" +
                "   - 도움말, -h, --help\n" +
                "   - 환영하기\n   - 뭐해\n   - 날씨\n   - 라면추천\n" +
                "   - 애니추천\n   - 오늘의 애니\n   - 공부하기, 잊어버려\n" +
                "   - 로또번호\n   - 대화요약\n   - 퀴즈, 퀴즈순위\n   - 가챠\n   - 강화\n   - 투자게임\n" +
                "   - 주사위\n   - 호감도\n   - GPT\n" +
                "\n@github: github.com/wndk7720/Bot.git"
                    // "   - 아침, 점심, 저녁추천   - 치킨추천   - 비트코인
            },
            /* welcome msg */
            {
                "반가워요~ 저는 망각탐정 쿄코라고 합니다!\n\n" +
                "닉네임은 이름/출생연도/최애캐 설정해주시고,\n" +
                "간단한 자기소개 한번만 더 부탁드립니다~\n" +
                "(입문작/최애작/최애캐/가장 최근에 본 애니)\n\n" +
                "[오픈채팅방 공지사항]\n" +
                " - 톡방에선 어떤 이야기를 해도 좋지만, 예의를 갖추고 적당선에서 부탁드려요!\n" +
                " - 서로간 합의되지 않은 반말은 금지입니다!\n" +
                " - 번개는 누구나 열 수 있습니다! (일정 등록 및 확인)\n" +
                " - 큰 규모의 번개(ex 신년회, 송년회, 엠티, 해외여행 등)는 운영진과 먼저 협의 부탁드립니다!\n" +
                " - 초상권이 있는 모임 사진은 외부 SNS(인스타, 카카오 프로필 등)에 업로드 절대 금지입니다."
             },
            /* introduce msg */
            {
                "처음 뵙겠습니다. 오키테가미 탐정 사무소의 오키테가미 쿄코라고 합니다.",
                "처음 뵙겠습니다. 저는 탐정인 오키테가미 쿄코라고 합니다.",
                "안녕하세요. 저의 이름은 오키테카미 쿄코. 탐정이죠."
            },
            /* default msg */
            {
                "부르셨나요?",
                "무슨 일이시죠?",
                "네, 외람되지만",
                "네?",
                "네!",
                "지금 바빠요!",
                "저요?",
                "왜요!"
            },
    };

    public static String[][] COMMON_BASIC_CMD = {
            /* sleep cmd */
            {
                    "졸려", "졸리"
            },
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
            /* sleep msg */
            {
                    "저도 졸음이 오지만 참아야해요",
                    "자버리면 안돼..",
                    "참아요!",
                    "찬물을 마셔요!",
                    "볼 꼬집어요.. 강하게",
                    "이 졸음을 이겨내지 못하면.. 지금까지 한 일이 헛되고 말아요.."
            },
            /* smile msg */
            {
                    "ㅋㅋ",
                    "ㅎㅎ"
            },
            /* go to work msg */
            {
                    "다녀오세요~",
                    "힘내세요!",
                    "아자아자!",
                    "듣기만해도 힘드네요.."
            },
            /* leave work msg */
            {
                    "고생했어요!",
                    "수고하셨습니다~",
                    "쉬러갑시다!"
            },
            /* ohoh msg */
            {
                    "우와",
                    "오오",
                    "오우"
            },
            /* .... msg */
            {
                    "제 생각엔 조금 머리를 식히는게 좋을 것 같아요",
                    "그럴수도 있죠",
                    "힘내세요!"
            },
    };

    public static String[] SLANG_CMD = {
            "ㅅㅂ","시발","시빨","씨발","씨빠","씨빨","슈발","싀발","슈빨","쓔발",
            "쓔빨","씌발","싀빨","씌발","ㅆㅃ","ㅅㅃ","ㅆㅃ","ㅅㅍ","시팔","씨팔",
            "ㅄ","ㅂㅅ","ㅂ1ㅅ","병신","븅신","또라이","미친놈","미친넘","미친년",
            "개새끼","뒤져","좆","ㅅ1ㅂ","ㅅ@ㅂ","시이발","씨댕","개빡","시파",
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
        BOT_BASIC_CMD = new ArrayList[(parts.length / 2) + BOT_BASIC_DEFAULT_CMD.length];
        BOT_BASIC_MSG = new ArrayList[(parts.length / 2) + BOT_BASIC_DEFAULT_MSG.length];

        /* CSV Command Load */
        int cmd_index = 0, line_index = 0;;
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
