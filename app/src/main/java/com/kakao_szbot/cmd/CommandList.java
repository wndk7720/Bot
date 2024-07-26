package com.kakao_szbot.cmd;

import com.kakao_szbot.lib.FileLibrary;

import java.util.ArrayList;
import java.util.List;

public class CommandList {
    public final static String TAG = "CommandList";

    public final static String BOT_NAME = "페코린느";
    public final static String BOT_FAMOUS_MSG = "장난 아니네요☆";
    public final static String BOT_VERSION = "24.07.27";

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
            /* notification cmd */
            {
                "공지"
            },
            /* introduce cmd */
            {
                "자기소개", "자기 소개"
            },
            /* special cmd */
            {
                "캬루"
            },
            {
                "콧코로"
            },
            /* default cmd */
            {
                    BOT_NAME
            },
    };
    public static String[][] BOT_BASIC_DEFAULT_MSG = {
            /* help msg */
            {
                "/*\n * Pecorine Bot\n * Version " + BOT_VERSION + "\n */" +
                "\n\n 「" + BOT_NAME + " + 명령어」 형태로 동작합니다.\n\n" +
                "명령어 목록은 아래와 같습니다.\n" +
                "   - 도움말, -h, --help\n" +
                "   - 환영하기\n   - 날씨\n   - 라면추천\n" +
                "   - 애니추천\n   - 오늘의 애니\n   - 공부하기, 잊어버려, 공부목록\n" +
                "   - 로또번호\n   - 대화요약\n   - 퀴즈, 퀴즈순위\n   - 명예의 전당\n   - 가챠\n   - 강화\n   - 투자게임\n" +
                "   - 주사위\n   - GPT\n" +
                "\n@github: github.com/wndk7720/Bot.git"
                    // "   - 뭐해   - 아침, 점심, 저녁추천   - 치킨추천   - 비트코인   - 호감도
            },
            /* welcome msg */
            {
                "안녕하세요~ 저는 다덕임의 먹보 담당 페코린느라고 합니다☆\n\n" +
                "닉네임은 이름/출생연도/최애캐로 설정해주시고, 간단한 자기소개 부탁드립니다!\n" +
                "(입문작/최애작/최애캐/가장 최근에 본 애니)"
            },
            /* notification msg */
            {
                "[기본 공지]\n" +
                " - 일본 만화 및 애니를 사랑하는 마음을 가진 덕후들의 모임입니다!\n" +
                "   * 다양한 장르에 대해 서로 이야기하며 지내요~\n" +
                " - 카카오톡 오픈채팅방에서 활동 (실명제)\n" +
                " - 오프라인 활동 위주\n" +
                " - 잠수, 개인중심, 파벌 등 기본적인 예의를 포함한 모임 방향과 맞지 않는 분 강퇴\n" +
                "   * 모임은 여러사람이 있는 단체임을 인지하고 서로 배려하고 오래 교류하실분만\n" +
                "\n" +
                "[오픈채팅방 공지]\n" +
                " - 어떤 이야기든 과하지 않게 적당선에서 부탁드립니다\n" +
                " - 취향 존중은 기본, 비난하는 내용은 되도록 하지말아요\n" +
                " - 본인 이야기 및 취향 적당히, 늘 한쪽으로 치우친 대화는 피해주세요\n" +
                " - 가치관이 전부 다르니 본인이 좋아한다더라도 남은 싫을 수 있습니다!\n" +
                " - 스포는 신작은 금지, 오래된 애니도 심각한 반전은 조심스럽게!\n" +
                " - 다른 사람 눈치보게 만드는 분위기 말투, 뉘앙스 금지\n" +
                " - 정치, 종교 등과 같은 예민한 소재 금지\n" +
                " - 초상권 포함 사진은 외부 공유 금지, 발각시 강퇴\n" +
                "   * 당사자 허락 없이 SNS(인스타, 트위터, 카톡 프로필 등) 업로드 금지\n" +
                "\n" +
                "[번개 관련 공지]\n" +
                " - 번개는 일정 등록을 통해 개설 및 확인\n" +
                " - 번개 이미 열린날 다른 번개 열고 싶을 경우, 기존 번개 장에게 양해 구한 후 개설\n" +
                " - 일정 참석 눌렀다가 불참시 번개 장에게 양해 구한 후 취소\n" +
                " - 번개 장이 일정 터뜨릴때도 참여자들에게 양해 구한 후 취소\n" +
                " - 일정 약속시간보다 늦을 경우 미리미리 덧글 및 채팅방에 알려주기"
            },
            /* introduce msg */
            {
                "페코린느? 그건 저를 말씀하시는 건가요? 정말 귀여운 별명이 붙어버렸네요~ " + BOT_FAMOUS_MSG,
                "안뇽~☆ 제 이름은...... 배가 꼬륵꼬륵 페코린느입니다!",

            },
            /* special msg */
            {
                "저는 캬루의 말대로 응석받이에 떼쟁이에요.",
                "캬루 쨩하고 함께☆",
                "캬루쨩... 저 좀 위로해주세요....",
                "에헤헤 저는 캬루와 함께라면 어디까지라도 달려갈 수 있을것 같아요"
            },
            {
                "콧코로쨩... 저 밥 좀...",
                "GO! GO! 콧코로쨩~",
                "쁘띠코를 먹어버려서 미안해요..."
            },
            /* default msg */
            {
                "맛있~어! 밥은 삶의 에너지-☆",
                "꼬르르륵~ 배에서 소리가 나버렸어요. 우, 웃으면 안 돼요!",
                "먹을 걸 주신다고요? 와아, 반해버려도 될까요?",
                "먹을 것을 함부로하는 나쁜 아이에는....벌을 줍니다!",
                "저, 역시 배가 꼬륵꼬륵 소리가 나요......",
                "이 다덕임은....제가 지킵니다!!",
                "뗵!",
                "밥~! 밥~",
                "그 누구라도 평등하게 맛있는 밥을 먹을 수 있는곳.. 그곳이 다덕임 이니까요..",
                "우리들의 목적은 이 세계의 온갖 만화나 애니를 추구, 탐구하면서 모두 함께 즐겁게 식사하는 길드!",
                "배 안 고프세요? 괜찮다면 저랑 같이 밥 먹으러 가실래요?",
                "안뇽~☆ 밥의 힘으로 용기가 100배 솟아났어요!",
                "음식을 소중히 합시다! 뭐, 제가 있으면 음식 남기는 일은 없겠지만요...."
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
                    "밥을 먹기 위해 일해야죠!",
                    "힘내세요!",
                    BOT_FAMOUS_MSG
            },
            /* leave work msg */
            {
                    "고생했어요!",
                    "수고했어요!",
                    BOT_FAMOUS_MSG
            },
            /* ohoh msg */
            {
                    BOT_FAMOUS_MSG,
                    "오오!"
            },
            /* .... msg */
            {
                    "장난 아니네요....☆"
            },
    };

    public static String[] SLANG_CMD = {
            "ㅅㅂ","시발","시빨","씨발","씨빠","씨빨","슈밤","싀발","슈빨","쓔발",
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

    public static String[] EXCHANGE_RATE_CMD = {
            "달러", "엔", "금리", "출산", "고령",
            "인구", "실업", "부채", "외환",
            "유가", "예금", "대출", "신용", "GDP",
            "채권", "주식", "유로", "금"
    };
    public static String[] EXCHANGE_RATE_SYMBOL = {
            "2/2", "3/3", "51/51", "63/63", "49/49",
            "62/62", "43/43", "33/33", "25/25",
            "23/23", "21/21", "20/20", "18/18", "13/13",
            "7/7", "6/6", "4/4", "24/24"
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

    public static String[] QUIZ1_POINT_CMD = {
            "명예의전당", "명예의 전당"
    };

    public static String[] QUIZ2_POINT_CMD = {
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

    public static String[] TOWER_CMD = {
            "다덕의탑"
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
