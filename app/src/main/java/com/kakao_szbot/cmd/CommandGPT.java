package com.kakao_szbot.cmd;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommandGPT {
    public final static String TAG = "CommandGPT";
    private static final String API_KEY = "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";
    private static final int MAX_TOKEN = 130;


    public String gptMessage(String msg, String sender) {
        int start_index = msg.indexOf("GPT") + 4;
        if (start_index < 0) start_index = msg.indexOf("gpt") + 4;

        String requestMsg = msg.substring(start_index, msg.length());
        Log.d(TAG, "requestMsg: " + requestMsg);

        try {
            return generateText(requestMsg, MAX_TOKEN);
        } catch (Exception e) {
            return "아쉽게 ChatGPT가 고장났답니다. 데헷☆";
        }
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
        result = result.replaceAll("\n", "");

        int last_index = result.lastIndexOf(".");
        if (last_index < 0) last_index = result.lastIndexOf("!");
        if (last_index < 0) last_index = result.lastIndexOf("?");
        if (last_index < 0) last_index = result.lastIndexOf("~");

        if (last_index < 0) {
            result = result + "...";
        } else {
            result = result.substring(0, last_index + 1);
        }

        return result;
    }
}
