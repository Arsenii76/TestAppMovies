package com.e.testappmovies.data.repository.helper

import com.e.testappmovies.data.api.ApiServiceMovies
import kotlinx.coroutines.*

class ApiHelperMovies(private val apiServiceMovies: ApiServiceMovies) {

    private val apiKey = "We0U0yAWdIDSEKBklHzBv2fZB81yPTEr"

    suspend fun getMovies() = withContext(Dispatchers.IO) {
        apiServiceMovies.getMovies(apiKey)
    }
}