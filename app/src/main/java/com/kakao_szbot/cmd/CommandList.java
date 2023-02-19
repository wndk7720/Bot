package com.kakao_szbot.cmd;

public class CommandList {
    public final static int RAND_MAX = 10000;
    public final static String BOT_NAME = "쿄코";

    public static String[][] BASIC_CMD = {
            /* what cmd */
            {
                "뭐해", "뭐하", "뭐햐", "뭐행", "머해",
                "머하", "머행", "모해", "모하", "모행"
            },
            /* default cmd */
            {
                BOT_NAME
            },
    };
    public static String[][] BASIC_MSG = {
            /* what msg */
            {
                "범인은..",
                "오늘 의뢰를 해결하고 있어요",
                "이번 의뢰는 조금 어려운걸요",
                "조용히 좀 있어보세요!",
                "추리하고 있습니다",
                "추리중입니다",
                "추리중이에요",
                "추리중이니 방해하지 마세요"
            },
            /* default msg */
            {
                "부르셨나요?",
                "무슨 일이시죠?",
                "무슨 일 있나요?",
                "네?",
                "네!",
                "맡겨만 주세요!",
                "지금 바빠요!",
                "저요?",
                "왜요!"
            },
    };
}
