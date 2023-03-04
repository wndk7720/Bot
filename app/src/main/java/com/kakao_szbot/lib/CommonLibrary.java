package com.kakao_szbot.lib;

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
}
