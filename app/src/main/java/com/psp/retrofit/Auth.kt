package com.psp.retrofit

import com.auth0.jwt.JWT
import com.auth0.jwt.RegisteredClaims.AUDIENCE
import com.auth0.jwt.RegisteredClaims.ISSUER
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date

data class LoginRequest(
    val username: String,
    val password: String
)

data class TokenResponse(
    val token: String
)

fun createToken(username: String): String = JWT.create()
    .withAudience(AUDIENCE)
    .withIssuer(ISSUER)
    .withClaim("username", username)
    .withExpiresAt(Date(System.currentTimeMillis() + 60000 * 60 * 24))
    .sign(Algorithm.HMAC256("clave_secreta_muy_larga_y_segura"))
