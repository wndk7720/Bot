package com.kakao_szbot.cmd;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommandGPT {
    public final static String TAG = "CommandGPT";
    private static final String API_KEY = "";
    private static final int MAX_TOKEN = 130;
    private static int SOMETIMES_RATIO = 0;
    private static final int SOMETIMES_THRESHOLD = 100;
    public static String[] sometimes_exception =
            {"ㅋ", "ㅎ", "이모티콘", "사진", CommandList.BOT_NAME};


    public String gptMessage(String msg, String sender) {
        int start_index = msg.indexOf("GPT");
        if (start_index < 0) start_index = msg.indexOf("gpt");

        String requestMsg = msg.substring(start_index + 4, msg.length());
        Log.d(TAG, "requestMsg: " + requestMsg);

        try {
            return generateText(requestMsg, MAX_TOKEN);
        } catch (Exception e) {
            return "아쉽게 ChatGPT가 고장났답니다. 데헷☆";
        }
    }

    public String gptDefaultMessage(String msg, String sender) {
        try {
            return generateText(msg, MAX_TOKEN);
        } catch (Exception e) {
            return "아쉽게 ChatGPT가 고장났답니다. 데헷☆";
        }
    }

    public String gptSometimesMessage(String msg, String sender) {
        for (int i=0; i < sometimes_exception.length; i++) {
            if (msg.indexOf(sometimes_exception[i]) == 0) {
                return null;
            }
        }

        SOMETIMES_RATIO++;
        if (SOMETIMES_RATIO > SOMETIMES_THRESHOLD) {
            Random random = new Random();
            int rand = random.nextInt(CommandList.RAND_MAX);
            if (rand < SOMETIMES_RATIO) {
                SOMETIMES_RATIO = 0;
                return gptDefaultMessage(msg, sender);
            }
        }

        return null;
    }

    public String gptBotMessage(String msg, String sender) {
        if (msg.contains(" ") == false) {
            return null;
        }

        String requestMsg = msg.replace("쿄코", "");
        String emptyCheckMsg = requestMsg.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "");
        if (emptyCheckMsg.length() == 0) {
            return null;
        }
        Log.d(TAG, "requestMsg: " + requestMsg);

        return gptDefaultMessage(requestMsg, sender);
    }

    private String generateText(String prompt, int maxTokens) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"prompt\":\"" + prompt + "\",\"max_tokens\":" + maxTokens + ",\"temperature\":0.7}");
        Request request = new Request.Builder()
                //.url("https://api.openai.com/v1/engines/davinci-codex/completions")
                .url("https://api.openai.com/v1/engines/text-davinci-003/completions")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();
        Response response = client.newCall(request).execute();
        String responseStr = response.body().string();
        Log.d(TAG, "responseStr: " + responseStr);

        JSONObject jsonObject = new JSONObject(responseStr);
        JSONArray jsonChoices = jsonObject.getJSONArray("choices");
        String result = jsonChoices.getJSONObject(0).getString("text");
        result = makePrettyText(result);

        return result;
    }

    private String makePrettyText(String result) {
        int start_index = result.indexOf("\n\n");
        if (start_index >= 0) {
            result = result.substring(start_index + 2, result.length());
        }

        for (int i = 0; i < result.length(); i++) {
            if (result.indexOf("\n") == 0) {
                result = result.substring(1, result.length());
            } else {
                break;
            }
        }

        int last_index = result.lastIndexOf(".");
        if (last_index < 0) last_index = result.lastIndexOf("!");
        if (last_index < 0) last_index = result.lastIndexOf("?");
        if (last_index < 0) last_index = result.lastIndexOf("~");

        if (result.length() != (last_index + 1)) {
            result = result + "...";
        }

        return result;
    }
}
