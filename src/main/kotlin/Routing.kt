package com.example

import com.example.config.Endpoint
import com.example.model.ImportFormat
import com.example.service.AntrophicApiConnector
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val apiConnector = AntrophicApiConnector(
        environment.config.property("antrophic.api.key").getString(),
        environment.config.property("antrophic.api.system.prompt").getString()
    );

    routing {
        post(Endpoint.PROMPT.url) {
            val message = call.receive<ImportFormat>()
            call.respond(apiConnector.prompt(message.message, message.context ?: arrayListOf()))
        }
    }
}
