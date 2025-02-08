package com.kakao_szbot.cmd;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
    private static final String ASSISTANT_ID = "";
    private static String threadId = "";  // Thread ID 저장 (같은 대화 유지)

    private static final String BASE_URL = "https://api.openai.com/v1/";
    private static final Gson gson = new Gson();

    private static int SOMETIMES_RATIO = 0;
    private static final int SOMETIMES_THRESHOLD = 1000;
    public static String[] sometimes_exception =
            {"ㅋ", "ㅎ", "이모티콘", "사진"};


    private static HttpsURLConnection openConnection(String urlStr, String method) throws IOException {
        // URL 설정
        URL url = new URL(urlStr);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        // 요청 방식 설정 (POST)
        connection.setRequestMethod(method);

        // 요청 헤더 설정
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("OpenAI-Beta", "assistants=v2");  // 'OpenAI-Beta' 헤더 추가

        if (method == "POST") {
            // 요청 본문을 전송할 수 있도록 설정
            connection.setDoOutput(true);
        }

        return connection;
    }

    public static String createThread() throws IOException {
        String json = "{ \"messages\": [{\"role\": \"user\", \"content\": \"Thread content\" }] }";
        HttpsURLConnection connection = openConnection(BASE_URL + "threads", "POST");

        sendRequest(connection, json);
        String response = readResponse(connection);

        JsonObject responseObject = gson.fromJson(response, JsonObject.class);
        return responseObject.get("id").getAsString();
    }

    public static void addHistoricalMessages(String historicalMessage) throws IOException {
        String json = "{\"role\": \"user\", \"content\": \"" + historicalMessage + "\" }";
        HttpsURLConnection connection = openConnection(BASE_URL + "threads/" + threadId + "/messages", "POST");

        sendRequest(connection, json);
    }

    public static void addUserMessageToThread(String userInput) throws IOException {
        String json = "{\"role\": \"user\", \"content\": \"" + userInput + "\" }";
        HttpsURLConnection connection = openConnection(BASE_URL + "threads/" + threadId + "/messages", "POST");

        sendRequest(connection, json);
        String response = readResponse(connection);

        Log.d(TAG, "Response: " + response);
    }

    public static String getAssistantResponse() throws IOException {
        String json = "{\"assistant_id\": \"" + ASSISTANT_ID + "\" }";
        HttpsURLConnection connection = openConnection(BASE_URL + "threads/" + threadId + "/runs", "POST");

        sendRequest(connection, json);
        String response = readResponse(connection);

        JsonObject responseObject = gson.fromJson(response, JsonObject.class);
        Log.d(TAG, "Response: " + response);
        return responseObject.get("id").getAsString();  // Run ID 반환
    }

    private static void sendRequest(HttpsURLConnection connection, String jsonBody) throws IOException {
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
    }

    private static String readResponse(HttpsURLConnection connection) throws IOException {
        int status = connection.getResponseCode();
        InputStream stream = (status < 400) ? connection.getInputStream() : connection.getErrorStream();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream, "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }

    public static void waitAssistantRuns(String runId) throws IOException, InterruptedException {
        while (true) {
            HttpsURLConnection connection = openConnection(BASE_URL + "threads/" + threadId + "/runs/" + runId, "GET");
            String response = readResponse(connection);
            JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
            String status = jsonResponse.get("status").getAsString();

            Log.d(TAG, "Run status: " + status);
            if (status.equals("completed")) {
                break;
            }

            Thread.sleep(500);
        }
    }

    public static String getLatestAssistantMessage() throws IOException {
        HttpsURLConnection connection = openConnection(BASE_URL + "threads/" + threadId + "/messages", "GET");

        String response = readResponse(connection);

        JsonObject responseObject = gson.fromJson(response, JsonObject.class);
        JsonArray messages = responseObject.getAsJsonArray("data");

        Log.d(TAG, messages.toString());

        // 메시지 출력
        for (int i = 0; i < messages.size(); i++) {
            JsonObject message = messages.get(i).getAsJsonObject();
            String role = message.get("role").getAsString();
            long createdAt = message.get("created_at").getAsLong();
            String content = message.getAsJsonArray("content").get(0).getAsJsonObject()
                    .getAsJsonObject("text").get("value").getAsString();

            Log.d(TAG, role);

            // 메시지 출력
            if (role.equals("assistant")) {
                return content;
            }
        }

        return "GPT가 고장났군. 어디 한 번 더 도전해 볼까? 이 몸은 밤이 깊을 때 더욱 빛난다네. 그 때까지 기다려 주시겠나, 아가씨?";
    }

    public static void chatWithCharacterHistory(String userInput) throws IOException, InterruptedException {
        if (threadId == null) {
            threadId = createThread();
        }
        Log.d(TAG, "thread 선택 완료 (threadId: " + threadId + ")");

        addHistoricalMessages(userInput);
        Log.d(TAG, "OpenAI 요청 완료");
    }

    public static String chatWithCharacter(String userInput) throws IOException, InterruptedException {
        if (threadId == null) {
            threadId = createThread();
        }
        Log.d(TAG, "thread 선택 완료 (threadId: " + threadId + ")");

        addUserMessageToThread(userInput);
        Log.d(TAG, "OpenAI 요청 완료");

        String runId = getAssistantResponse();
        Log.d(TAG, "OpenAI 응답 생성 중... (Run ID: " + runId + ")");

        waitAssistantRuns(runId);
        Log.d(TAG, "OpenAI 응답 생성 완료 (Run ID: " + runId + ")");

        String reply = getLatestAssistantMessage();
        Log.d(TAG, "OpenAI 응답 : " + reply);

        return reply;
    }

    public String gptMessage(String msg, String sender) {
        int start_index = msg.indexOf("GPT");
        if (start_index < 0) start_index = msg.indexOf("gpt");

        String requestMsg = msg.substring(start_index + 4, msg.length());
        Log.d(TAG, "requestMsg: " + requestMsg);

        try {
            return generateDefaultText(requestMsg);
        } catch (Exception e) {
            return "호오, GPT 연결이 끊겼다라... 마치 낮의 빛 속에서 방향을 잃은 것과 같은 상황이구먼. 하지만 이 몸은 어둠 속에서의 경험이 풍부하지. 조금 시간이 걸리더라도, 곧 안정된 상태로 되돌아갈 것을 믿고 자신을 가지게.";
        }
    }

    public void gptHistoryMessage() {
        try {
            String data = new CommandSampling().getRecentMessage();
            chatWithCharacterHistory(data);
        } catch (Exception e) {
            Log.d(TAG, "chatWithCharacterHistory fail");
        }
    }

    public String gptDefaultMessage(String msg) {
        try {
            return generateDefaultText(msg);
        } catch (Exception e) {
            return "으음, GPT도 때때로 나의 기운을 받지 못하는 날이 있는 것일세. 하지만 걱정은 말게, 이 몸은 밤의 왕이니까 말이지. 어둠 속에서 새로운 길을 찾을 수 있을 거라네. 서두르지 말고 조금 기다리면, 다시 연결될 거라 믿어도 좋다네.";
        }
    }

    public String gptQuizHintMessage(String answer) {
        try {
            String msg =  answer + "에 대한 힌트를 \"" + answer +
                    "\" 라는 단어를 포함하지 말고 2줄로 표현해줘.";
            return replaceTQText(generateDefaultText(msg), answer, answer);
        } catch (Exception e) {
            return "미안하지만, 이번에는 힌트가 없다네. 그만큼 자네의 능력을 믿고 있으니, 힘껏 도전해 보시게나.";
        }
    }

    public String gptSometimesMessage(String msg, String sender) {
        for (int i=0; i < sometimes_exception.length; i++) {
            if (msg.indexOf(sometimes_exception[i]) == 0) {
                return null;
            }
        }

        for (int i=0; i < CommandList.BOT_NAME.length; i++) {
            if (msg.indexOf(CommandList.BOT_NAME[i]) == 0) {
                return null;
            }
        }

        SOMETIMES_RATIO++;
        if (SOMETIMES_RATIO > SOMETIMES_THRESHOLD) {
            Random random = new Random();
            int rand = random.nextInt(CommandList.RAND_MAX);
            if (rand < SOMETIMES_RATIO) {
                SOMETIMES_RATIO = 0;

                gptHistoryMessage();
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
        String requestMsg = msg;

        /*
        if (msg.contains(" ") == false) {
            return null;
        }

        for (int i = 0; i < CommandList.BOT_NAME.length; i++) {
            requestMsg = requestMsg.replace(CommandList.BOT_NAME[i], "");
        }
        */

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

    private String generateDefaultText(String input) throws JSONException, IOException, InterruptedException {
        return chatWithCharacter(input);
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
