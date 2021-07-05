package com.e.testappmovies.ui.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.testappmovies.data.model.ResponseMovies
import com.e.testappmovies.data.repository.MoviesRepository
import com.e.testappmovies.ui.RequestError
import com.e.testappmovies.ui.ViewState
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class MoviesViewModel(private val moviesRepository: MoviesRepository): ViewModel() {

    val moviesLiveData = MutableLiveData<ViewState<ResponseMovies>>()

    init {
        viewModelScope.launch {
            moviesLiveData.value = ViewState.Loading()
            try {
                moviesLiveData.value = ViewState.Success(data = moviesRepository.getMovies().body())
            } catch (e: Exception) {
                moviesLiveData.value = ViewState.Error(exception = RequestError.checkException(e))
            }
        }
    }
}