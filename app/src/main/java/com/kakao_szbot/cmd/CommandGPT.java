package com.kakao_szbot.cmd;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

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
    private static final int SOMETIMES_THRESHOLD = 1000;
    public static String[] sometimes_exception =
            {"ㅋ", "ㅎ", "이모티콘", "사진",
            CommandList.BOT_NAME[0], CommandList.BOT_NAME[1]};


    public String gptMessage(String msg, String sender) {
        int start_index = msg.indexOf("GPT");
        if (start_index < 0) start_index = msg.indexOf("gpt");

        String requestMsg = msg.substring(start_index + 4, msg.length());
        Log.d(TAG, "requestMsg: " + requestMsg);

        try {
            return generateDefaultText(requestMsg);
        } catch (Exception e) {
            return "으, 으으윽... 움직이라고요 ChatGPT씨~!!";
        }
    }

    public String questionTQGPT(String msg) {
        try {
            return generateDefaultText(msg);
        } catch (Exception e) {
            return "망했다. ChatGPT가 고장나버렸다;;";
        }
    }

    public String gptDefaultMessage(String msg) {
        try {
            return generateDefaultText(msg);
        } catch (Exception e) {
            return "으, 으으윽... 움직이라고요 ChatGPT씨~!!";
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
                return gptDefaultMessage(msg);
            }
        }

        return null;
    }

    public String gptConversationSummary(String msg) {
        String requestMsg = msg;

        requestMsg += "\n\n위의 내용을 3줄 요약해줘.";
        Log.d(TAG, "requestMsg: " + requestMsg);

        return gptDefaultMessage(requestMsg);
    }

    public String gptBotMessage(String msg, String sender) {
        if (msg.contains(" ") == false) {
            return null;
        }

        String requestMsg = msg;
        for (int i = 0; i < CommandList.BOT_NAME.length; i++) {
            requestMsg = requestMsg.replace(CommandList.BOT_NAME[i], "");
        }

        String emptyCheckMsg = requestMsg.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "");
        if (emptyCheckMsg.length() == 0) {
            return null;
        }

        //requestMsg += "\n\nPlease write in Friendly and Optimistic. Korean language. Please answer as if your name is 바쿠신. Please answer within 5 sentences.";
        Log.d(TAG, "requestMsg: " + requestMsg);

        return gptDefaultMessage(requestMsg);
    }

    private String generateText(JSONArray messageList) throws JSONException, IOException {
        // Build input and API key params
        String result = null;
        JSONObject payload = new JSONObject();

        /*
        String data = null;
        JSONArray messageList = new JSONArray();
        JSONObject message;


        message = new JSONObject();
        message.put("role", "user");
        message.put("content", input);
        messageList.put(message);

        message = new JSONObject();
        message.put("role", "system");
        message.put("content",
                //"Please write in Friendly and Optimistic. Korean language. Please answer as if your name is 바쿠신. Please answer within 5 sentences.");
                "넌 우마무스메라는 애니의 사쿠라 바쿠신 오라는 캐릭터라는 설정이야. 매우 밝고 긍정적인 모범생에 반장역할이고 돌진! 이라는 표현을 습관적으로 쓰는 컨셉이야. 존댓말로만 5문장 이내로 답장해줘.");
        messageList.put(message);

        data = new CommandSampling().getRecentMessage();
        data += "\n위의 내용이 이전내용이야. 참고해서 답변해줘.";
        message = new JSONObject();
        message.put("role", "assistant");
        message.put("content", data);
        messageList.put(message);
        */

        //payload.put("model", "gpt-3.5-turbo"); // model is important
        payload.put("model", "gpt-4o"); // model is important
        payload.put("messages", messageList);
        //payload.put("temperature", 0.7);

        URL url = new URL("https://api.openai.com/v1/chat/completions");
        HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "Bearer " + API_KEY);
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        con.setDoInput(true);

        Log.d(TAG, "requestStr: " + payload.toString());
        try(OutputStream os = con.getOutputStream()) {
            byte[] inputByte = payload.toString().getBytes("utf-8");
            os.write(inputByte, 0, inputByte.length);
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            result = response.toString();
        } catch (Exception e){
            Log.d(TAG, "exception code: " + con.getResponseCode() + ", message: " + con.getResponseMessage());
            e.printStackTrace();
        }
        con.disconnect();

        JSONObject jsonResult = new JSONObject(result);
        JSONArray jsonChoices = jsonResult.getJSONArray("choices");
        JSONObject jsonMessage = jsonChoices.getJSONObject(0).getJSONObject("message");
        result = jsonMessage.getString("content");
        result = makePrettyText(result);
        Log.d(TAG, "responseStr: " + result);
        con.disconnect();

        return result;
    }

    private String generateDefaultText(String input) throws JSONException, IOException {
        JSONArray messageList = new JSONArray();
        JSONObject message;

        message = new JSONObject();
        message.put("role", "user");
        message.put("content", input);
        messageList.put(message);

        message = new JSONObject();
        message.put("role", "system");
        message.put("content",
                //"Please write in Friendly and Optimistic. Korean language. Please answer as if your name is 바쿠신. Please answer within 5 sentences.");
                "넌 우마무스메라는 애니의 사쿠라 바쿠신 오라는 캐릭터라는 설정이야. 매우 밝고 긍정적인 모범생에 반장역할이고 돌진! 이라는 표현을 습관적으로 쓰는 컨셉이야. 존댓말로만 5문장 이내로 답장해줘.");
        messageList.put(message);

        String data = new CommandSampling().getRecentMessage();
        data += "\n위의 내용이 이전내용이야. 참고해서 답변해줘.";
        message = new JSONObject();
        message.put("role", "assistant");
        message.put("content", data);
        messageList.put(message);

        return generateText(messageList);
    }

    public String replaceTQText(String reply, String answer, String theme) {
        Log.d(TAG, "replaceTQText reply before: " + reply);

        if (answer != null) {
            reply = reply.replaceAll(answer, "?");
            answer = answer.replaceAll(" ", "");
            reply = reply.replaceAll(answer, "?");
        }

        if (theme != null) {
            reply = reply.replaceAll(theme, "?");
            theme = theme.replaceAll(" ", "");
            reply = reply.replaceAll(theme, "?");
        }

        Log.d(TAG, "replaceTQText reply after: " + reply);

        return reply;
    }

    public String generateTQQuestionText(String question, String answer, String theme) throws JSONException, IOException {
        JSONArray messageList = new JSONArray();
        JSONObject message;
        String request = "지금은 "  + theme + "에 등장하는 캐릭터 \"" + answer +"\"가 정답인 스무고개 게임을 진행중이고 너가 출제자인 상황이야.\n" +
                "절대 정답을 말하면 안돼. \"예\" 또는 \"아니오\" 라고만 대답해줘. 아래 질문에 대해서 대답해줘.\n\n" +
                question;

        message = new JSONObject();
        message.put("role", "user");
        message.put("content", request);
        messageList.put(message);

        String reply = generateText(messageList);
        return replaceTQText(reply, answer, theme);
    }

    public String generateTQQuestionHint1Text(String answer, String theme) throws JSONException, IOException {
        JSONArray messageList = new JSONArray();
        JSONObject message;
        String request = theme + "에 대한 장르를 한 단어로 말해줘.";

        message = new JSONObject();
        message.put("role", "user");
        message.put("content", request);
        messageList.put(message);

        String reply = generateText(messageList);
        return replaceTQText(reply, answer, theme);
    }

    public String generateTQQuestionHint2Text(String answer, String theme) throws JSONException, IOException {
        JSONArray messageList = new JSONArray();
        JSONObject message;
        String request = theme + "에 대한 힌트를 \"" + theme +
                "\" 라는 단어를 포함하지 말고 2줄로 표현해줘.";

        message = new JSONObject();
        message.put("role", "user");
        message.put("content", request);
        messageList.put(message);

        String reply = generateText(messageList);
        return replaceTQText(reply, answer, theme);
    }

    public String generateTQQuestionHint3Text(String answer, String theme) throws JSONException, IOException {
        JSONArray messageList = new JSONArray();
        JSONObject message;
        String request = "지금은 " + theme + "에 등장하는 캐릭터 \"" + answer +"\"가 정답인 스무고개 게임을 진행중이고 너가 출제자인 상황이야.\n" +
                "절대 정답을 말하면 안돼. 이 정답에 대한 아주 어려운 힌트를 한 단어로 말해줘. 다시 한번 강요하지만 절대 정답을 힌트에 넣지마. 절대 정답을 말하면 안돼.";

        message = new JSONObject();
        message.put("role", "user");
        message.put("content", request);
        messageList.put(message);

        String reply = generateText(messageList);
        return replaceTQText(reply, answer, theme);
    }

    public String generateTQQuestionHint4Text(String answer, String theme) throws JSONException, IOException {
        JSONArray messageList = new JSONArray();
        JSONObject message;
        String request = "지금은 " + theme + "에 등장하는 캐릭터 \"" + answer +"\"가 정답인 스무고개 게임을 진행중이고 너가 출제자인 상황이야.\n" +
                "절대 정답을 말하면 안돼. 이 정답에 대한 힌트를 3줄로 말해줘. 절대 \"" + answer + "\" 단어를 힌트에 포함하면 안돼.";

        message = new JSONObject();
        message.put("role", "user");
        message.put("content", request);
        messageList.put(message);

        String reply = generateText(messageList);
        return replaceTQText(reply, answer, theme);
    }

    /*
    private String generateText(String prompt, int maxTokens) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"prompt\":\"" + prompt + "\",\"max_tokens\":" + maxTokens + ",\"temperature\":0.7}");
        //RequestBody body = RequestBody.create(mediaType, "{\"model\": \"gpt-3.5-turbo\",\"messages\": [{\"role\":\"user\", \"content\":\"" + prompt + "\"}]");
        Request request = new Request.Builder()
                //.url("https://api.openai.com/v1/engines/davinci-codex/completions")
                .url("https://api.openai.com/v1/engines/text-davinci-003/completions")
                //.url("https://api.openai.com/v1/chat/completions")
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
    */

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
