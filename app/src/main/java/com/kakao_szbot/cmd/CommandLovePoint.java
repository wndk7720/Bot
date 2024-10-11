package com.kakao_szbot.cmd;

import static com.kakao_szbot.lib.CommonLibrary.patternIndexOf;

import com.kakao_szbot.lib.FileLibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CommandLovePoint {
    public final static String TAG = "CommandLovePoint";

    public static int RAND_POINT_MAX = 100;
    public static int LOVE_POINT_THRESHOLD = 1000;
    public static String[] POINT_UP_CMD =
            {"이뻐", "귀여", "좋아", "착해", "똑똑", "최고",
            "예뻐", "귀엽", "커엽", "귀욤", "귀요", "멋져",
            "멋있", "사랑", "스키", "알라뷰", "알랍", "알럽",
            "알러뷰", "굿", "짱"};
    public static String[] POINT_DOWM_CMD =
            {"바보", "멍청", "못생", "싫", "나뻐", "나쁜",
            "돼지", "뚱땡", "미워", "너무해", "흥", "그만", "냄",
            "저리", "최악", "나빠", "죽어", "별로", "죽었",
            "쓰레", "쓰렉", "꺼", "퉷", "퉤"};

    public static List<String> hogam_sender = new ArrayList<String>();
    public static List<Integer> hogam_sender_value = new ArrayList<Integer>();

    public String upLovePointMessage(String msg, String sender) {
        String result = null;
        int lovePoint = 0;
        Random random = new Random();
        int randPoint = random.nextInt(CommandList.RAND_MAX) % RAND_POINT_MAX;

        String resultSender = sender;
        int patternIndex = patternIndexOf(sender, "[0-9`~!@#$%^&*()-_=+\\|\\[\\]{};:'\",.<>/? ]");
        if (patternIndex != 0) sender = sender.substring(0, patternIndex);

        if (Collections.frequency(hogam_sender, sender) == 0) {
            hogam_sender.add(sender);
            lovePoint += randPoint;
            hogam_sender_value.add(lovePoint);
        } else {
            int index = hogam_sender.indexOf(sender);
            lovePoint = hogam_sender_value.get(index) + randPoint;
            hogam_sender_value.set(index, lovePoint);
        }

        if (randPoint > RAND_POINT_MAX * 0.95 || lovePoint > LOVE_POINT_THRESHOLD) {
            result = "우등생이니까요! (Unbelievable!!! +" + randPoint + ")\n - " + resultSender + "님의 호감도 : " + lovePoint;
        } else if (randPoint > RAND_POINT_MAX * 0.75) {
            result = "너무너무 멋져요!! (Excellent!! +" + randPoint + ")\n - " + resultSender + "님의 호감도 : " + lovePoint;
        } else if (randPoint > RAND_POINT_MAX * 0.5) {
            result = "표창감이네요!? (Very Good! +" + randPoint + ")\n - " + resultSender + "님의 호감도 : " + lovePoint;
        } else if (randPoint > RAND_POINT_MAX * 0.25) {
            result = "좋네요! (Good +" + randPoint + ")\n - " + resultSender + "님의 호감도 : " + lovePoint;
        } else {
            result = "와아...! (Normal +" + randPoint + ")\n - " + resultSender + "님의 호감도 : " + lovePoint;
        }

        FileLibrary csv = new FileLibrary();
        csv.writePointCSV("lovePointList.csv", sender, lovePoint);

        return result;
    }

    public String downLovePointMessage(String msg, String sender) {
        String result = null;
        int lovePoint = 0;
        Random random = new Random();
        int randPoint = random.nextInt(CommandList.RAND_MAX) % RAND_POINT_MAX;

        String resultSender = sender;
        int patternIndex = patternIndexOf(sender, "[0-9`~!@#$%^&*()-_=+\\|\\[\\]{};:'\",.<>/? ]");
        if (patternIndex != 0) sender = sender.substring(0, patternIndex);

        if (Collections.frequency(hogam_sender, sender) == 0) {
            hogam_sender.add(sender);
            lovePoint -= randPoint;
            hogam_sender_value.add(lovePoint);
        } else {
            int index = hogam_sender.indexOf(sender);
            lovePoint = hogam_sender_value.get(index) - randPoint;
            hogam_sender_value.set(index, lovePoint);
        }

        if (randPoint > RAND_POINT_MAX * 0.95 || lovePoint < -LOVE_POINT_THRESHOLD) {
            result = "훌쩍... (Unbelievable!!! -" + randPoint + ")\n - " + resultSender + "님의 호감도 : " + lovePoint;
        } else if (randPoint > RAND_POINT_MAX * 0.75) {
            result = "다신 저 볼 생각하지 마세요 (Excellent!! -" + randPoint + ")\n - " + resultSender + "님의 호감도 : " + lovePoint;
        } else if (randPoint > RAND_POINT_MAX * 0.5) {
            result = "... (Very Good! -" + randPoint + ")\n - " + resultSender + "님의 호감도 : " + lovePoint;
        } else if (randPoint > RAND_POINT_MAX * 0.25) {
            result = "실망이에요 (Good -" + randPoint + ")\n - " + resultSender + "님의 호감도 : " + lovePoint;
        } else {
            result = "그런 말씀을 하시다니.. (Normal -" + randPoint + ")\n - " + resultSender + "님의 호감도 : " + lovePoint;
        }

        FileLibrary csv = new FileLibrary();
        csv.writePointCSV("lovePointList.csv", sender, lovePoint);

        return result;
    }

    public String printLovePointList() {
        String result = null;
        String result_msg = "";

        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i < hogam_sender.size(); i++) {
            map.put(hogam_sender.get(i), hogam_sender_value.get(i));
        }

        List<String> keySet = new ArrayList<>(map.keySet());
        keySet.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return map.get(o1).compareTo(map.get(o2));
            }
        });
        keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));

        for (String key : keySet) {
            result_msg += "\n - " + key + "님의 호감도: "
                    + map.get(key);
        }

        result = "[" + CommandList.BOT_NAME[0] + "의 호감도 현황]" + result_msg;
        return result;
    }

    public void loadLovePointList() {
        FileLibrary csv = new FileLibrary();
        String allData = csv.ReadCSV("lovePointList.csv");
        if (allData == null)
            return;

        String[] parts = allData.split("\n");
        for (String part : parts) {
            String[] data = part.split(",");

            hogam_sender.add(data[0]);
            hogam_sender_value.add(Integer.parseInt(data[1]));
        }
    }
}
