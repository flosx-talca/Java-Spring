package com.aluracursos.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
    public static String obtenerTraduccion(String texto) {
        OpenAiService service = new OpenAiService("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");

        CompletionRequest requisicion = CompletionRequest.builder()
                .model("gpt-4o-mini")
                .prompt("traduce a español el siguiente texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var respuesta = service.createCompletion(requisicion);
        return respuesta.getChoices().get(0).getText();
    }
}