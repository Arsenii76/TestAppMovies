package com.e.testappmovies.ui.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.testappmovies.data.model.Response
import com.e.testappmovies.data.model.Results
import com.e.testappmovies.data.repository.MoviesRepository
import com.e.testappmovies.ui.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MoviesViewModel(private val moviesRepository: MoviesRepository): ViewModel() {

    val moviesLiveData = MutableLiveData<Resource<Response>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            moviesLiveData.postValue(Resource.loading(data = null))
            try {
                moviesLiveData.postValue(Resource.success(data = moviesRepository.getMovies()))
            } catch (e: Exception) {
                moviesLiveData.postValue(Resource.error(data = null, msg = e.message ?: "Error Occurred!"))
            }
        }
    }
}