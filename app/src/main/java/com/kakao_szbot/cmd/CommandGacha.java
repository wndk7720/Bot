package com.kakao_szbot.cmd;

import java.util.Random;

public class CommandGacha {
    public final static String TAG = "CommandGacha";
    String[] gacha_reply_1 = {"탐사수", "티슈", "쿠키", "바나나", "사발면", "야키토리"};
    String[] gacha_reply_2 = {"프로틴 스파클링", "제로콜라", "당근", "문 브레이커",
            "함정카드", "마스크팩", "안약", "은발로리", "도토리"};
    String[] gacha_reply_3 = {"나루토", "사쿠라", "사스케", "이타치", "히나타",
            "루피", "조로", "나미", "상디", "우솝"};
    String[] gacha_reply_4 = {"카마도 탄지로", "카마도 네즈코", "아가츠마 젠이츠",
            "하시비라 이노스케", "렌고쿠 쿄쥬로", "우즈이 텐겐", "코쵸우 시노부",
            "칸로지 미츠리", "이구로 오바나이", "히메지마 교메이", "토키토 무이치로",
            "토미오카 기유", "시나즈가와 사네미", "키부츠지 무잔"};
    String[] gacha_reply_5 = {"당근 무더기", "단비", "토르"};
    String[] gacha_reply_6 = {"바이올렛 에버가든"};
    String[] gacha_reply_7 = {"엑스칼리버"};
    String[] gacha_reply_8 = {"온천 티켓"};
    String[] gacha_reply_9 = {"메이드"};
    String[] gacha_reply_10 = {"메지로 맥퀸"};
    String[] gacha_reply_11 = {"집사"};
    String[] gacha_reply_12 = {"아냐 포저"};

    public String gachaMessage(String msg) {
        String result = null;
        Random random = new Random();

        int gacha_rand = random.nextInt(CommandList.RAND_MAX);
        int rand = random.nextInt(CommandList.RAND_MAX);


        if (gacha_rand > (CommandList.RAND_MAX * (0.5))) {
            result = gacha_reply_1[rand % gacha_reply_1.length] + " 뽑았다!\n(일반: 50%)";
        }
        else if (gacha_rand > (CommandList.RAND_MAX * (0.2))) {
            result = gacha_reply_2[rand % gacha_reply_2.length] + " 뽑았다!\n(고급: 30%)";
        }
        else if (gacha_rand > (CommandList.RAND_MAX * (0.1))) {
            result = gacha_reply_3[rand % gacha_reply_3.length] + " 뽑았다!\n(희귀: 10%)";
        }
        else if (gacha_rand > (CommandList.RAND_MAX * (0.05))) {
            result = gacha_reply_4[rand % gacha_reply_4.length] + " 뽑았다!\n(고대: 5%)";
        }
        else if (gacha_rand > (CommandList.RAND_MAX * (0.02))) {
            result = gacha_reply_5[rand % gacha_reply_5.length] + " 뽑았다!\n(영웅: 3%)";
        }
        else if (gacha_rand > (CommandList.RAND_MAX * (0.01))) {
            result = gacha_reply_6[rand % gacha_reply_6.length] + " 뽑았다!\n(유일: 1%)";
        }
        else if (gacha_rand > (CommandList.RAND_MAX * (0.005))) {
            result = gacha_reply_7[rand % gacha_reply_7.length] + " 뽑았다!\n(유물: 0.5%)";
        }
        else if (gacha_rand > (CommandList.RAND_MAX * (0.002))) {
            result = gacha_reply_8[rand % gacha_reply_8.length] + " 뽑았다!\n(경이: 0.3%)";
        }
        else if (gacha_rand > (CommandList.RAND_MAX * (0.001))) {
            result = gacha_reply_9[rand % gacha_reply_9.length] + " 뽑았다!\n(서사: 0.1%)";
        }
        else if (gacha_rand > (CommandList.RAND_MAX * (0.0003))) {
            result = gacha_reply_10[rand % gacha_reply_10.length] + " 뽑았다!\n(전설: 0.07%)";
        }
        else if (gacha_rand > (CommandList.RAND_MAX * (0.0001))) {
            result = gacha_reply_11[rand % gacha_reply_11.length] + " 뽑았다!\n(신화: 0.02%)";
        }
        else {
            result = gacha_reply_12[rand % gacha_reply_12.length] + " 뽑았다!\n(태초: 0.01%)";
        }

        return result;
    }
}
