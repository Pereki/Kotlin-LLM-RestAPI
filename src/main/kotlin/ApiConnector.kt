package com.example

import com.anthropic.client.AnthropicClient
import com.anthropic.client.okhttp.AnthropicOkHttpClient


import com.anthropic.models.messages.Message
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.Model


val question: (String, String) -> String = { question: String, apiKey: String ->
    val client: AnthropicClient = AnthropicOkHttpClient.builder()
        .apiKey(apiKey)
        .build()

    val params: MessageCreateParams = MessageCreateParams.builder()
        .model(Model.CLAUDE_SONNET_4_6)
        .maxTokens(1024L)
        .addUserMessage(question)
        .build()

    val message: Message = client.messages().create(params)
    message.content()[0].text().get().text()
}
