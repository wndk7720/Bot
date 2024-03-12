package com.kakao_szbot.cmd;

import static com.kakao_szbot.KakaoNotificationListener.KakaoSendReply;
import static com.kakao_szbot.KakaoNotificationListener.getSbn;

import java.util.Random;

public class CommandReinforce {
    public final static String TAG = "CommandReinforce";
    public static String[] reinforce_weapon = {
            "화장포", "노가다 목장갑", "체리 마법봉", "일루시데이터", "도미네이터",
            "묠니르", "간장, 막야", "레이징 하트", "비래골", "소드 커틀라스", "커피잔",
            "자칼", "초은하 다이그렌", "고잉 메리 호", "인피니티 건틀릿", "롱기누스의 창"};
    public static int reinforce_weapon_index = 0;
    public static int reinforce_weapon_upgrade = 0;
    public static float reinforce_chance = 1;


    public String reinforceMessage(String msg) throws InterruptedException {
        String result = null;
        Random random = new Random();

        int rand = random.nextInt(CommandList.RAND_MAX);
        int rand_2 = random.nextInt(CommandList.RAND_MAX);

        if (reinforce_weapon_upgrade > 9) {
            KakaoSendReply("와.. " + CommandList.BOT_FAMOUS_MSG, getSbn());
            Thread.sleep(2000);
            KakaoSendReply("3", getSbn());
            Thread.sleep(1000);
            KakaoSendReply("2", getSbn());
            Thread.sleep(1000);
            KakaoSendReply("1", getSbn());
            Thread.sleep(1000);
        }
        else if (reinforce_weapon_upgrade > 4) {
            KakaoSendReply("호오.. " + CommandList.BOT_FAMOUS_MSG, getSbn());
            Thread.sleep(2000);
        }

        if (rand < (CommandList.RAND_MAX * reinforce_chance)) {
            reinforce_chance *= 0.9;
            reinforce_weapon_upgrade++;
            result = reinforce_weapon[reinforce_weapon_index] +
                    " (+" + reinforce_weapon_upgrade +
                    ") 강화에 성공했어요!\n( 다음 성공 확률 : " +
                    Math.floor(reinforce_chance * 100) + "% )";
        }
        else if (rand_2 < (CommandList.RAND_MAX * reinforce_chance)) {
            reinforce_weapon_upgrade--;
            if (reinforce_weapon_upgrade == 0) {
                reinforce_chance = 1;
            }

            result = reinforce_weapon[reinforce_weapon_index] +
                    " (+" + reinforce_weapon_upgrade +
                    ") 강화에 실패했어요..\n( 다음 성공 확률 : " +
                    Math.floor(reinforce_chance * 100) + "% )";
        }
        else {
            result = reinforce_weapon[reinforce_weapon_index] + " 깨져버렸어요!";

            reinforce_chance = 1;
            reinforce_weapon_upgrade = 0;
            reinforce_weapon_index = rand % reinforce_weapon.length;
        }

        return result;
    }
}
