package com.e.testappmovies.data.repository.helper

import com.e.testappmovies.data.api.ApiServiceMovies

class ApiHelperMovies(private val apiServiceMovies: ApiServiceMovies) {

    private val apiKey = "We0U0yAWdIDSEKBklHzBv2fZB81yPTEr"

    suspend fun getMovies() = apiServiceMovies.getMovies(apiKey)
}