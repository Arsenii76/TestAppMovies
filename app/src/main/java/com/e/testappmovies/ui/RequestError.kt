package com.e.testappmovies.ui

import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException

object RequestError {
    fun checkException(e: Exception): String {
        return when(e){
            is UnknownHostException -> ErrorStatus.NO_CONNECTION
            is HttpException -> ErrorStatus.SERVER_ERROR
            is ConnectException -> ErrorStatus.TIMEOUT_EXPIRED
            else -> ErrorStatus.UNKNOWN_ERROR
        }
    }
}
