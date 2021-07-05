package com.e.testappmovies.data.repository

import com.e.testappmovies.data.repository.helper.ApiHelperMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(private val apiHelperMovies: ApiHelperMovies) {

    suspend fun getMovies() = withContext(Dispatchers.IO){
        apiHelperMovies.getMovies()
    }
}