package com.kakao_szbot.cmd;

import com.kakao_szbot.lib.FileLibrary;

import java.util.ArrayList;
import java.util.List;

public class CommandList {
    public final static String TAG = "CommandList";

    public final static String[] BOT_NAME = {
            "이사장", "아키카와야요이", "아키카와 야요이", "아키카와", "야요이"
    };

    public final static String BOT_VERSION = "25.11.09";

    public final static int RAND_MAX = 10000;
    public final static int RAND_ANI_MAX = 2500;

    public static List<String>[] BOT_BASIC_CMD;
    public static List<String>[] BOT_BASIC_MSG;

    public static String[][] BOT_BASIC_DEFAULT_CMD = {
            /* help cmd */
            {
                "도움말", "--help", "-h"
            },
            /* notification cmd */
            {
                "공지"
            },
    };
    public static String[][] BOT_BASIC_DEFAULT_MSG = {
            /* help msg */
            {
                "/*\n * Akikawa Yayoi Bot\n * Version " + BOT_VERSION + "\n */" +
                "\n\n 「" + BOT_NAME[0] + " + 명령어」 형태로 동작합니다.\n\n" +
                "명령어 목록은 아래와 같습니다.\n" +
                "   - 도움말, -h, --help\n" +
                "   - 애니추천\n   - 오늘의 애니\n   - 공부하기, 잊어버려, 공부목록\n" +
                "   - 대화요약\n   - 퀴즈, 퀴즈순위\n   - 명예의 전당\n   - 가챠\n   - 강화\n   - 투자게임\n" +
                "   - 주사위\n   - GPT\n" +
                "\n@github: https://github.com/wndk7720/Bot"
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
                "[모임 일정 관련 공지]\n" +
                " - 모임은 일정 등록을 통해 개설 및 확인\n" +
                " - 일정 이미 열린날 다른 모임을 열고 싶을 경우, 기존 일정 주최자에게 양해 구한 후 개설\n" +
                " - 모임 참석 눌렀다가 불참시 주최자에게 양해 구한 후 취소\n" +
                " - 주최한 일정 터뜨릴때도 참여자들에게 양해 구한 후 취소\n" +
                " - 일정 약속시간보다 늦을 경우 미리미리 덧글 및 채팅방에 알려주기"
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
                    "확신! 트레이너, 좋은 일 있었나 보군",
                    "열정! 그 웃음, 보기 좋군 트레이너!",
                    "하핫! 그 기세, 마음에 든다!",
                    "확신. 웃음은 성공의 예고지!",
                    "에헤헷, 트레이너도 꽤 즐길 줄 아는군?",
                    "흥미! 그 웃음, 분명 무언가 꾸미고 있군? 말해보게!",
                    "좋다, 그런 활력이야말로 트레센 학원의 자산이지!",
                    "감동! 트레이너가 웃는 걸 보니… 오늘도 학원은 평화롭군.",
                    "열정! 웃음은 최고의 투자다, 트레이너!",
                    "확신. 그 미소야말로 하루를 여는 원동력이지!"
            },
            /* go to work msg */
            {
                    "확신! 오늘도 전력으로 임할 시간이군!",
                    "열정! 학원이 우리를 기다리고 있다, 트레이너!",
                    "하핫! 드디어 전장의 아침이 밝았군!",
                    "흥미! 오늘은 어떤 아이디어가 폭발할지 기대되는걸",
                    "기상! 출근이다! 학원의 기둥이 움직이는 순간이지!",
                    "후후, 출근이라… 하야카와가 또 출석 체크를 하겠군.",
                    "좋다, 트레이너. 이사장실 문은 이미 열려 있다!",
                    "열광! 커피 한 잔이면 완벽한 출근 준비 완료다!",
                    "호오~ 오늘도 새로운 계획을 세울 절호의 기회군!",
                    "확신. 우마무스메들이 달린다면, 나도 달려야지! 출근이다!"
            },
            /* leave work msg */
            {
                    "확신! 오늘도 임무 완수다, 트레이너! 훌륭했군!",
                    "하핫! 퇴근이라... 자네도 드디어 자유를 얻었군!",
                    "열정! 학원을 떠나도 마음은 여전히 전력 질주다!",
                    "후후, 오늘도 수고 많았다. 이사장실 불은 조금 더 켜두지.",
                    "흥! 퇴근이 곧 끝이 아니다. 내일의 준비가 시작되는 순간이지!",
                    "좋다, 트레이너. 오늘의 성과, 이사장이 직접 칭찬하마!",
                    "호오~ 벌써 퇴근이라니... 하야카와보다 빠르군? 대담한데!",
                    "휴식도 전략의 일부다. 잘 쉬어라, 트레이너.",
                    "기백! 오늘의 노력, 반드시 결실로 돌아올 것이다!",
                    "감동! 하루를 전력으로 달린 자만이 퇴근의 감동을 맛보는 법이지!"
            },
            /* ohoh msg */
            {
                    "흥! 놀랐나 보군, 트레이너! 이사장의 기백에 감탄한 건가?",
                    "후후, 갑작스러운 변화에도 침착한 대응! 훌륭하다!",
                    "확신! 트레이너, 그 감탄은 발전의 시작이다!",
                    "호오~ 뭔가 마음에 드는 걸 본 모양이군!",
                    "오오! 그 눈빛, 새로운 아이디어가 떠오른 것 같군!",
                    "하핫! 반응이 좋군! 역시 내 선택은 틀리지 않았지!",
                    "흥미! 뭔가 느낀 게 있나, 트레이너? 말해보게!",
                    "오우~ 오늘의 에너지가 아주 좋다! 계속 그 기세로 가자!",
                    "호오...? 감탄이 폭발했군! 하핫, 무언가 인상 깊었나 보지?",
                    "후후, 트레이너... 놀라운 걸 본 모양이군? 이사장도 궁금하군!"
            },
            /* .... msg */
            {
                    "확신. 지금, 무언가 고민하고 있군? 말해보게, 이사장이 들어주지.",
                    "확신! 그 여운 속에서도 메시지가 느껴지네!",
                    "에헤헷, 나를 시험하는 건 아니지?",
                    "흥! 눈빛이 모든 걸 말하고 있군. 좋아.",
                    "……음, 잠시 생각 중인가? 그래, 신중함은 미덕이지.",
                    "오오~ 중요한 비밀이라도 있는가?",
                    "기상! 말 대신 행동으로 보여주겠다는 뜻인가? 멋지군!",
                    "흥미! 상상력이 폭발하는군!",
                    "호오~ 뭔가 숨겨진 이야기가 있는가?",
                    "후후후, 그 말투… 뭔가 계획이 있는 모양이군!"
            },
    };

    public static String[] SLANG_CMD = {
            "ㅅㅂ","시발","시빨","씨발","씨빠","씨빨","슈밤","싀발","슈빨","쓔발",
            "쓔빨","씌발","싀빨","씌발","ㅆㅃ","ㅅㅃ","ㅆㅃ","ㅅㅍ","시팔","씨팔",
            "ㅄ","ㅂㅅ","ㅂ1ㅅ","병신","븅신","또라이","미친놈","미친넘","미친년",
            "개새끼","뒤1져","좆","ㅅ1ㅂ","ㅅ@ㅂ","시이발","씨댕","개빡","시파",
            "싀파","싀팔","싀바","꺼져","꺼저","도라이","ㅈㄴ","존나","썅",
            "시벌", "시팔", "찌발", "싸발", "씨방", "쒸발", "씻팔", "시바루",
            "Tlqkf", "끼발", "뀨발", "야발", "ㅅ1ㅂ", "ㅈㄹ", "지랄", "tlqkf",
            "끼1발", "시1발", "씨1발", "ㅆ1ㅃ", "ㅅ1ㅃ", "ㅆ1ㅂ", "쉬발"
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
            "엔", "달러", "유로", "위안",
            "미국",
            "유럽연합",
            "일본",
            "중국",
            "홍콩",
            "대만",
            "영국",
            "오만",
            "캐나다",
            "스위스",
            "스웨덴",
            "호주",
            "뉴질랜드",
            "체코",
            "칠레",
            "튀르키예",
            "몽골",
            "이스라엘",
            "덴마크",
            "노르웨이",
            "사우디아라비아",
            "쿠웨이트",
            "바레인",
            "아랍에미리트",
            "요르단",
            "이집트",
            "태국",
            "싱가포르",
            "말레이시아",
            "인도네시아",
            "카타르",
            "카자흐스탄",
            "브루나이",
            "인도",
            "파키스탄",
            "방글라데시",
            "필리핀",
            "멕시코",
            "브라질",
            "베트남",
            "남아프리카",
            "러시아",
            "헝가리",
            "폴란드",
            "스리랑카",
            "알제리",
            "케냐",
            "콜롬비아",
            "탄자니아",
            "네팔",
            "루마니아",
            "리비아",
            "마카오",
            "미얀마",
            "에티오피아",
            "우즈베키스탄",
            "캄보디아",
            "피지"
    };
    public static String[] EXCHANGE_RATE_SPECIFIC_CMD = {
            "엔화", "달러", "유로", "위안"
    };
    public static String[] EXCHANGE_RATE_SYMBOL = {
            "JPY", "USD", "EUR", "CNY"
    };


    public static String[] ECONOMIC_CMD = {
            "금리", "출산", "고령",
            "인구", "실업", "부채", "외환",
            "유가", "예금", "대출", "신용", "GDP",
            "채권", "금값"
    };
    public static String[] ECONOMIC_SYMBOL = {
            "51/51", "63/63", "49/49",
            "62/62", "43/43", "33/33", "25/25",
            "23/23", "21/21", "20/20", "18/18", "13/13",
            "7/7", "24/24"
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

    public static String[] QUIZ3_POINT_CMD = {
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

    public static String[] TQ_GAME_CMD = {
            "스무고개"
    };

    public static String[] TQ_GAME_QUESTION_CMD = {
            "질문"
    };

    public static String[] TQ_GAME_ANSWER_CMD = {
            "정답"
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
