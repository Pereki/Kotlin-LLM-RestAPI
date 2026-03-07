package com.example.util

import com.anthropic.models.messages.MessageParam

val messageToMessageParams : (ArrayList<String>) -> List<MessageParam> = { messages ->
    messages.mapIndexed { index, message ->
        MessageParam.builder()
            .role(if (index % 2 == 0) MessageParam.Role.ASSISTANT else MessageParam.Role.USER)
            .content(message)
            .build()
    }
}