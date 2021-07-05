package com.e.testappmovies.data.networkerror

import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException

object RequestError {
    fun checkException(e: Exception): String {
        return when(e){
            is UnknownHostException -> StatusError.NO_CONNECTION
            is HttpException -> StatusError.SERVER_ERROR
            is ConnectException -> StatusError.TIMEOUT_ERROR
            else -> StatusError.UNKNOWN_ERROR
        }
    }
}
