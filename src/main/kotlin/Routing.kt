package com.example

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val apiKey = environment.config.property("antrophic.api.key").getString()

    routing {
        get("call") {
            val message = call.parameters["message"]
            call.respondText(question(message ?: "What is the meaning of life?", apiKey))
        }
    }
}
