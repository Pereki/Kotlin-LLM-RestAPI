package com.example.util

import com.anthropic.models.messages.Message

val extractMessage : (Message) -> String = { message ->
    message.content()[0].text().get().text()
}