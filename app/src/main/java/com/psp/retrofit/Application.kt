package com.psp.retrofit

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
        embeddedServer(Netty, port = 8080, host = "192.168.1.13") {
            configureRouting()
            configureSecurity()
            configureSerialization()
        }.start(wait = true)
    }

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)
