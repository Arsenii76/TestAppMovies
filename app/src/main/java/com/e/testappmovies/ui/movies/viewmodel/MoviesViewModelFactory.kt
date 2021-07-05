package com.e.testappmovies.ui.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.e.testappmovies.data.repository.MoviesRepository
import com.e.testappmovies.data.repository.helper.ApiHelperMovies

class MoviesViewModelFactory(private val apiHelperMovies: ApiHelperMovies): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = MoviesViewModel(MoviesRepository(apiHelperMovies)) as T
}