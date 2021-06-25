package com.e.testappmovies.data.repository

import com.e.testappmovies.data.repository.helper.ApiHelperMovies

class MoviesRepository(private val apiHelperMovies: ApiHelperMovies) {
    suspend fun getMovies() = apiHelperMovies.getMovies()
}