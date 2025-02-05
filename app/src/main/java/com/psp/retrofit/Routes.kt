package com.psp.retrofit

import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.configureRouting(){
    routing {
        post("/login") {
            val loginRequest = call.receive<LoginRequest>()
            if (loginRequest.username == "admin" && loginRequest.password == "password")
            {
                val token = createToken(loginRequest.username)
                call.respond(TokenResponse(token))
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Credenciales válidas")
            }
        }

        authenticate {
            get("/alumnos") {
                val principal = call.principal<JWTPrincipal>()
                val username = principal!!.payload.getClaim("username").asString()
                call.respond(listOf(
                    Alumno(
                        id = 1,
                        nombre = "Test",
                        fechaNacimiento = "2000-01-01",
                        curso = Curso.DAM2,
                        email = "test@email.com",
                        asignaturas = listOf(Asignatura.PSP)
                    )
                ))
            }
        }
    }
}