package com.psp.retrofit

import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class ServerTest {

    /*
    @Test
    fun testLogin() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val response = client.post("/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest("admin", "password"))
        }

        assertEquals(HttpStatusCode.OK, response.status)
        assertNotNull(response.body<TokenResponse>().token)
    }
     */
}