package com.example

import com.anthropic.client.AnthropicClient
import com.anthropic.client.okhttp.AnthropicOkHttpClient


import com.anthropic.models.messages.Message
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.MessageParam
import com.anthropic.models.messages.Model
import com.example.model.OutputFormat

class AntrophicApiConnector(apiKey: String, val systemPrompt: String) {

    val client : AnthropicClient = AnthropicOkHttpClient.builder()
        .apiKey(apiKey)
        .build();

    fun question(message: String, messages: ArrayList<String>) : OutputFormat {
        val params: MessageCreateParams = MessageCreateParams.builder()
            .model(Model.CLAUDE_SONNET_4_6)
            .maxTokens(1024L)
            .system(systemPrompt)
            .messages(
                messages.mapIndexed { index, message ->
                    MessageParam.builder()
                        .role(if (index % 2 == 0) MessageParam.Role.ASSISTANT else MessageParam.Role.USER)
                        .content(message)
                        .build()
                }
            )
            .addUserMessage(message)
            .build()

        val message: Message = client.messages().create(params)
        return OutputFormat(message.content()[0].text().get().text(), messages)
    }
}