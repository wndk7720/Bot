package com.kakao_szbot.cmd;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class CommandCrawling {
    public final static String TAG = "CommandCrawling";

    private String getContentURL(String address) throws Exception {
        // Specify the URL for retrieving XRP price from Upbit
        URL url = new URL(address);

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

        return content.toString();
    }

    public String coinMessage(String msg, String sender) throws Exception {
        String url = "https://api.upbit.com/v1/ticker?markets=KRW-";
        String coin_name = null, coin_symbol = null, result = null;

        for (int i = 0; i < CommandList.COIN_CMD.length; i++) {
            if (msg.contains(CommandList.COIN_CMD[i])) {
                coin_name = CommandList.COIN_CMD[i];
                coin_symbol = CommandList.COIN_SYMBOL[i];
                break;
            }
        }

        if (coin_symbol == null) {
            return result;
        }

        result = getContentURL(url + coin_symbol);

        JSONArray jsonArray = new JSONArray(result);
        JSONObject response = jsonArray.getJSONObject(0);
        double price = response.getDouble("trade_price");

        result = "현재 " + coin_name + " 시세는 " + (int)price + "원 입니다";
        return result;
    }

    public String weatherMessage(String msg, String sender) throws Exception {
        String url = "https://www.weather.go.kr/weather/forecast/mid-term-rss3.jsp";
        String result = null;

        // Connect to the website and get the XML document
        Document document = Jsoup.connect(url).get();

        Element pubDateElement = document.selectFirst("pubDate");
        String pubDate =  pubDateElement.text();

        // Find the element with the tag "wf"
        Element wfElement = document.selectFirst("wf");
        String wf =  wfElement.text();

        result = "[" + pubDate + "]\n\n";
        result += wf.replaceAll("<br />", "\n");

        return result;
    }

    public String recommendAniMessage(String msg, String sender) throws Exception {
        String url = "https://api.anissia.net/anime/list/";
        String result = null;
        Random random = new Random();

        int pageRand = random.nextInt(CommandList.RAND_ANI_PAGE_MAX);
        result = getContentURL(url + pageRand);
        JSONObject content = new JSONObject(result);

        JSONArray array =  content.getJSONArray("content");
        int arrayLength = array.length();
        int aniRand = random.nextInt(arrayLength);

        JSONObject object = array.getJSONObject(aniRand);

        result = "이런 애니 어떠신가요?\n\n";
        result += " - " + object.getString("subject");
        result += " (" + object.getString("genres") + ")\n";
        result += "  > 방영일 : " + object.getString("startDate") + "\n";
        result += "  > " + object.getString("website");

        return result;
    }

    public String todayAniMessage(String msg, String sender) throws Exception {
        String url = "https://api.anissia.net/anime/schedule/";
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        String result = null;

        result = getContentURL(url + dayOfWeek);
        JSONArray jsonArray = new JSONArray(result);

        result = "오늘 방영하는 애니 목록입니다\n";
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            result += "\n - " + jsonObject.getString("subject");
            result += " (" + jsonObject.getString("genres") + ")";
        }

        return result;
    }
}
