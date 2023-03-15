package com.kakao_szbot.cmd;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;

public class CommandGPT {
    public final static String TAG = "CommandGPT";
    private static final String API_KEY = "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";


    public String gptMessage(String msg, String sender) {
        return generateText(msg);
    }

    public static String generateText(String msg) {
        OpenAiService service = new OpenAiService(API_KEY);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Somebody once told me the world is gonna roll me")
                .model("ada")
                .echo(true)
                .build();
        //service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
        return service.createCompletion(completionRequest).getChoices().get(0).getText();
    }
}
