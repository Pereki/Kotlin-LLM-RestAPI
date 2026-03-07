package com.example.service

import com.anthropic.client.AnthropicClient
import com.anthropic.client.okhttp.AnthropicOkHttpClient


import com.anthropic.models.messages.Message
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.Model
import com.example.model.OutputFormat
import com.example.util.extractMessage
import com.example.util.messageToMessageParams

class AntrophicApiConnector(apiKey: String, val systemPrompt: String) {

    val client : AnthropicClient = AnthropicOkHttpClient.builder()
        .apiKey(apiKey)
        .build();

    fun prompt(message: String, messages: ArrayList<String>) : OutputFormat {
        val params: MessageCreateParams = MessageCreateParams.builder()
            .model(Model.CLAUDE_SONNET_4_6)
            .maxTokens(1024L)
            .system(systemPrompt)
            .messages(messageToMessageParams(messages))
            .addUserMessage(message)
            .build()

        val answer: String = extractMessage(client.messages().create(params));
        messages.add(message)
        return OutputFormat(answer, messages)
    }
}