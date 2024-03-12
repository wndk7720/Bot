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

    public String exchangeRateMessage(String msg, String sender) throws Exception {
        String url = "https://quotation-api-cdn.dunamu.com/v1/forex/recent?codes=FRX.";
        String exchange_name = null, exchange_symbol = null, result = null;

        for (int i = 0; i < CommandList.EXCHANGE_RATE_CMD.length; i++) {
            if (msg.contains(CommandList.EXCHANGE_RATE_CMD[i])) {
                exchange_name = CommandList.EXCHANGE_RATE_CMD[i];
                exchange_symbol = CommandList.EXCHANGE_RATE_SYMBOL[i];
                break;
            }
        }

        if (exchange_symbol == null) {
            return result;
        }

        result = getContentURL(url + exchange_symbol);

        JSONArray jsonArray = new JSONArray(result);
        JSONObject response = jsonArray.getJSONObject(0);
        double price = response.getDouble("basePrice");

        result = "현.재 " + exchange_name + " 환율은 " + (int)price + "원이에요! " + CommandList.BOT_FAMOUS_MSG;
        return result;
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

        result = "현.재 " + coin_name + " 시세는 " + (int)price + "원이에요! " + CommandList.BOT_FAMOUS_MSG;
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

    public JSONObject getAniObject() throws Exception {
        String url = "https://api.anissia.net/anime/animeNo/";
        String result = null;
        Random random = new Random();

        int noRand = random.nextInt(CommandList.RAND_ANI_MAX);
        result = getContentURL(url + noRand);
        JSONObject content = new JSONObject(result);
        JSONObject object = content.getJSONObject("data");

        return object;
    }

    public String recommendAniMessage(String msg, String sender) throws Exception {
        String url = "https://api.anissia.net/anime/animeNo/";
        String result = null;
        Random random = new Random();

        int noRand = random.nextInt(CommandList.RAND_ANI_MAX);
        result = getContentURL(url + noRand);
        JSONObject content = new JSONObject(result);

        /*
        JSONArray array =  content.getJSONArray("content");
        int arrayLength = array.length();
        int aniRand = random.nextInt(arrayLength);
        */

        JSONObject object = content.getJSONObject("data");

        result = "이 애니 " + CommandList.BOT_FAMOUS_MSG + "\n\n";
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
        JSONObject jsonResult = new JSONObject(result);
        JSONArray jsonArray = jsonResult.getJSONArray("data");

        result = "오늘 방영하는 애니 목록이에요!\n";
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            result += "\n - " + jsonObject.getString("subject");
            result += " (" + jsonObject.getString("genres") + ")";
        }

        return result;
    }
}
