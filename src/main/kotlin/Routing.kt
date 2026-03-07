package com.example

import com.example.model.ImportFormat
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val apiKey = environment.config.property("antrophic.api.key").getString()
    val systemPrompt = environment.config.property("antrophic.api.system.prompt").getString()

    val apiConnector = AntrophicApiConnector(apiKey, systemPrompt);

    routing {
        post("call") {
            val message = call.receive<ImportFormat>()
            call.respond(apiConnector.question(message.message, message.context ?: arrayListOf()))
        }
    }
}
