package com.e.testappmovies.data.api

import com.e.testappmovies.data.model.Response
import com.e.testappmovies.data.model.Results
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceMovies {

    @GET("all.json")
    suspend fun getMovies(
        @Query("api-key") apiKey: String
    ): Response
}