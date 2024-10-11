package com.kakao_szbot.cmd;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
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

import java.io.IOException;


public class CommandCrawling {
    public final static String TAG = "CommandCrawling";
    public final static String BOT_END_OF_MSG = "보고는 이상입니다! 에헴!";
    private static final String API_KEY = "";

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
        String url = "https://finance.naver.com/marketindex/exchangeList.naver";
        String result = null, exchangeName = null;
        int startIndex = 0, endIndex;
        int i;

        for (i = 0; i < CommandList.EXCHANGE_RATE_CMD.length; i++) {
            if (msg.indexOf("환율") == -1) {
                continue;
            }

            startIndex = msg.indexOf(CommandList.EXCHANGE_RATE_CMD[i]);
            if (startIndex != -1) {
                endIndex = startIndex + CommandList.EXCHANGE_RATE_CMD[i].length();
                exchangeName = msg.substring(startIndex, endIndex);
                break;
            }
        }

        for (i = 0; i < CommandList.EXCHANGE_RATE_SPECIFIC_CMD.length; i++) {
            if (msg.contains(CommandList.EXCHANGE_RATE_SPECIFIC_CMD[i])) {
                exchangeName = CommandList.EXCHANGE_RATE_SYMBOL[i];
                break;
            }
        }

        if (exchangeName == null) {
            return result;
        }

        try {
            // URL로부터 HTML 문서를 가져옵니다.
            Document doc = Jsoup.connect(url).get();

            // 매매기준율을 포함한 테이블을 선택합니다.
            Elements rows = doc.select(".tbl_exchange tbody tr");

            for (Element row : rows) {
                // 통화명
                String currencyName = row.select("td.tit").text();
                // 매매기준율
                String exchangeRate = row.select("td.sale").text();

                if (currencyName.contains(exchangeName)) {
                    result = "현.재 " + currencyName + " " + exchangeRate + "원이에요! " + BOT_END_OF_MSG;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String economicMessage(String msg, String sender) throws Exception {
        String url = "https://ecos.bok.or.kr/api/KeyStatisticList/" + API_KEY + "/json/kr/";
        String exchange_symbol = null, result = null;

        for (int i = 0; i < CommandList.ECONOMIC_CMD.length; i++) {
            if (msg.contains(CommandList.ECONOMIC_CMD[i])) {
                exchange_symbol = CommandList.ECONOMIC_SYMBOL[i];
                break;
            }
        }

        if (exchange_symbol == null) {
            return result;
        }

        result = getContentURL(url + exchange_symbol);

        JSONObject jsonObject = new JSONObject(result);
        JSONObject KeyStatisticList = jsonObject.getJSONObject("KeyStatisticList");
        JSONArray row = KeyStatisticList.getJSONArray("row");
        JSONObject response = row.getJSONObject(0);

        String unit = response.getString("UNIT_NAME");
        String keystat  = response.getString("KEYSTAT_NAME");
        if (unit == "%") {
            double data = response.getDouble("DATA_VALUE");
            result = "현.재 " + keystat + " " + data + unit + "이에요! " + BOT_END_OF_MSG;
        } else {
            long data = response.getLong("DATA_VALUE");
            result = "현.재 " + keystat + " " + data + unit + "이에요! " + BOT_END_OF_MSG;
        }

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

        result = "현.재 " + coin_name + " 시세는 " + (int)price + "원이에요! " + BOT_END_OF_MSG;
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

        result = "이 애니로 " + CommandList.BOT_FAMOUS_MSG + "\n\n";
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
        result += BOT_END_OF_MSG;

        return result;
    }
}
