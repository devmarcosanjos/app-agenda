package com.marcosanjos.app_agenda.service

import retrofit2.HttpException
import java.io.IOException

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val code: Int, val message: String) : Result<Nothing>()
}

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        // Tenta executar a chamada e retorna Success se funcionar
        Result.Success(apiCall())
    } catch (e: Exception) {
        // Se der erro, cai aqui e verificamos o tipo da exceção
        when (e) {
            is HttpException -> {
                Result.Error(e.code(), e.message())
            }
            is IOException -> {
                Result.Error(0, "Network error (Check connection)")
            }
            else -> {
                Result.Error(e.hashCode(), e.message ?: "Unknown error")
            }
        }
    }
}
