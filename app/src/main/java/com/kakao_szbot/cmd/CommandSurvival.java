package com.kakao_szbot.cmd;

import static com.kakao_szbot.KakaoNotificationListener.KakaoSendReply;
import static com.kakao_szbot.KakaoNotificationListener.getSbn;
import static com.kakao_szbot.cmd.MainCommandChecker.checkCommnadList;
import static com.kakao_szbot.lib.CommonLibrary.patternIndexOf;
import static com.kakao_szbot.lib.CommonLibrary.patternLastIndexOf;

import android.util.Log;

import com.kakao_szbot.lib.FileLibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CommandSurvival {
    public final static String TAG = "CommandSurvival";

    public static String[] SURVIVAL_SUMMON_CMD = {
            "소환"
    };

    public static String[] SURVIVAL_SKILL_CMD = {
            "스킬"
    };

    private static String SURVIVAL_DATA_BASE = "survvialData.csv";
    public static int total_survant_num = 0;

    public static List<String> player = new ArrayList<String>();
    public static List<String> survant = new ArrayList<String>();
    public static List<Integer> survant_health = new ArrayList<Integer>();
    public static List<Integer> attack_rock_damage = new ArrayList<Integer>();
    public static List<Integer> attack_paper_damage = new ArrayList<Integer>();
    public static List<Integer> attack_scissors_damage = new ArrayList<Integer>();
    public static List<String> attack_rock_name = new ArrayList<String>();
    public static List<String> attack_paper_name = new ArrayList<String>();
    public static List<String> attack_scissors_name = new ArrayList<String>();


    public String defaultMessage() {
        String replyMessage = "[2023 추석 이벤트 - 성배전쟁]\n" +
                " - 본 이벤트는 성배전쟁(Fate 시리즈)을 컨셉으로 기획되었습니다.\n";
        return replyMessage;
    }

    public String summonSurvent(String sender) {
        int patternIndex = 0;
        String player = null, survant = null;

        patternIndex = patternIndexOf(sender, "[0-9`~!@#$%^&*()-_=+\\|\\[\\]{};:'\",.<>/? ]");
        Log.d(TAG, "patternIndexOf : " + patternIndex);
        if (patternIndex != 0) player = sender.substring(0, patternIndex);

        patternIndex = patternLastIndexOf(sender, "[0-9`~!@#$%^&*()-_=+\\|\\[\\]{};:'\",.<>/? ]");
        Log.d(TAG, "patternLastIndexOf : " + patternIndex);
        if (patternIndex != 0) survant = sender.substring(patternIndex, sender.length());
        else return "소환에 실패하였습니다. 개발자에게 문의 바랍니다.\n(프로필 형식을 안맞춰서 실패했을 가능성이 많습니다!)";

        FileLibrary csv = new FileLibrary();
        csv.WriteSurvivalCSV(SURVIVAL_DATA_BASE, player, survant, 5, 1, 1, 1);

        String ment = "\" 서번트 「" + survant + "」, 소환에 응해 찾아왔습니다. " + player + "님 당신이 나의 마스터입니까? \"";
        KakaoSendReply(ment, getSbn());
        String result = "[성배 전쟁 참전 신청 완료]\n" +
                " - 서번트 : " + survant + "\n" +
                " - 체력 : 5\n" +
                " - 스킬1 (이름미정/바위) : 1 공격력\n" +
                " - 스킬2 (이름미정/보) : 1 공격력\n" +
                " - 스킬3 (이름미정/가위) : 1 공격력\n" +
                " - 잔여 스텟 포인트 : 3\n\n" +
                "※ 스킬 이름을 정해주세요! 대괄호 꼭 써주셔야합니다!\n ex) 쿄코 스킬1 [원하는 스킬 이름]\n\n" +
                "※ 스텟 포인트도 분배해주세요!\n스킬 1~3 중에서 분배 가능합니다.\n ex) 쿄코 스킬1 업, 쿄코 스킬3 다운";

        return result;
    }

    public String mainSurvivalCommand(String msg, String sender) {
        if (checkCommnadList(msg, SURVIVAL_SUMMON_CMD) == 0) {
            return summonSurvent(sender);
        }

        return defaultMessage();
    }

    public void loadSurvivalData() {
        FileLibrary csv = new FileLibrary();
        String allData = csv.ReadCSV(SURVIVAL_DATA_BASE);
        if (allData == null)
            return;

        String[] parts = allData.split("\n");
        for (String part : parts) {
            String[] data = part.split(",");
            player.add(data[0]);
            survant.add(data[1]);
            survant_health.add(Integer.parseInt(data[2]));
            attack_rock_damage.add(Integer.parseInt(data[3]));
            attack_paper_damage.add(Integer.parseInt(data[4]));
            attack_scissors_damage.add(Integer.parseInt(data[5]));
            attack_rock_name.add(data[6]);
            attack_paper_name.add(data[7]);
            attack_scissors_name.add(data[8]);
            total_survant_num++;
        }

        Log.d(TAG, "전체 서버트 수 : " + total_survant_num);
    }
}
