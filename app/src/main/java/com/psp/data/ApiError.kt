package com.psp.data

sealed class ApiError {

    data object NetworkError : ApiError()
    data class ServerError(val code: Int, val message: String) : ApiError()
    data class UnknownError(val message: String) : ApiError()
}