package com.e.testappmovies.ui

class Resource<out T>(val status: ViewState, val data: T?, val msg: String?) {

    companion object {
        fun <T> success(data: T) = Resource(status = ViewState.SUCCESS, data = data, msg = null)

        fun <T> loading(data: T?) = Resource(status = ViewState.LOADING, data = data, msg = null)

        fun <T> error(data: T?, msg: String) =
            Resource(status = ViewState.ERROR, data = data, msg = msg)
    }
}