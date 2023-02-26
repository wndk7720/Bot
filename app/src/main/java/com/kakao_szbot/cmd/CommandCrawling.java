package com.kakao_szbot.cmd;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommandCrawling {
    public final static String TAG = "CommandCrawling";

    public String coinMessage(String msg, String sender) throws Exception {
        String coin_name = null, coin_symbol = null, result = null;

        for (int i = 0; i < CommandList.COIN_CMD.length; i++) {
            if (msg.contains(CommandList.COIN_CMD[i])) {
                coin_name = CommandList.COIN_CMD[i];
                coin_symbol = CommandList.COIN_MSG[i];
                break;
            }
        }

        if (coin_symbol == null) {
            return result;
        }

        // Specify the URL for retrieving XRP price from Upbit
        URL url = new URL("https://api.upbit.com/v1/ticker?markets=KRW-" + coin_symbol);

        // Create a connection object and set the request method to GET
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Read the response from the URL
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        // Parse the response JSON and extract XRP price
        JSONArray jsonArray = new JSONArray(content.toString());
        JSONObject response = jsonArray.getJSONObject(0);
        double price = response.getDouble("trade_price");

        result = "현재 " + coin_name + " 시세는 " + (int)price + "원인 것 같습니다!";
        return result;
    }
}
