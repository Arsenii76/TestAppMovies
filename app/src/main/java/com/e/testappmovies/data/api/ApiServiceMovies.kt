package com.e.testappmovies.data.api

import com.e.testappmovies.data.model.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceMovies {

    @GET("all.json")
    suspend fun getMovies(
        @Query("api-key") apiKey: String
    ): Response<ResponseMovies>
}