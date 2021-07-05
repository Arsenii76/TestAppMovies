package com.e.testappmovies.ui

sealed class ViewState<out T>{
    data class Success<T>(val data: T?): ViewState<T>()
    data class Error<T>(val exception: String?): ViewState<T>()
    class Loading<T> : ViewState<T>()
}

