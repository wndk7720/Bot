package com.kakao_szbot.lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonLibrary {
    public static int findNum(String msg) {
        String result = msg.replaceAll("[^0-9]", "");
        int number = 0;

        try {
            number = Integer.parseInt(result);
        } catch (NumberFormatException e) {
            number = 0;
        }

        return number;
    }


    public static int patternIndexOf(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
            return matcher.start();
        return 0;
    }
}
