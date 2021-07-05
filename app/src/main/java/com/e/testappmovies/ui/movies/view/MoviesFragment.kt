package com.e.testappmovies.ui.movies.view

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.testappmovies.R
import com.e.testappmovies.data.api.RetrofitBuilder
import com.e.testappmovies.data.model.ResponseMovies
import com.e.testappmovies.data.repository.helper.ApiHelperMovies
import com.e.testappmovies.ui.ViewState.Success
import com.e.testappmovies.ui.ViewState.Error
import com.e.testappmovies.ui.ViewState.Loading
import com.e.testappmovies.ui.movies.adapter.ItemMovies
import com.e.testappmovies.ui.movies.adapter.MoviesAdapter
import com.e.testappmovies.ui.movies.viewmodel.MoviesViewModel
import com.e.testappmovies.ui.movies.viewmodel.MoviesViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MoviesFragment : Fragment() {

    private lateinit var movesViewModel: MoviesViewModel
    private lateinit var adapterMovies: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_movies, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupViewModel()
        setupObserver()
    }


    private fun setupRecyclerView(){
        rcViewMovies.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(activity)
            adapterMovies = MoviesAdapter(arrayListOf())
            adapter = adapterMovies
        }
    }

    private fun setupViewModel(){
        movesViewModel = ViewModelProvider(
            requireActivity(),
            MoviesViewModelFactory(ApiHelperMovies(RetrofitBuilder.apiService))
        ).get(MoviesViewModel::class.java)
    }

    private fun setupObserver() {
        movesViewModel.moviesLiveData.observe(requireActivity(), {
            it.let { resource ->
                when (resource) {
                    is Success -> {
                        resource.data?.let { movies ->
                            progressBarMovies.visibility = View.GONE
                            adapterMovies.addMovies(movies.results)
                        } ?: Toast.makeText(requireContext(), "Данных нет!", Toast.LENGTH_SHORT).show()
                    }
                    is Loading -> {
                        progressBarMovies.visibility = View.VISIBLE
                    }
                    is Error -> {
                        progressBarMovies.visibility = View.GONE
                        Toast.makeText(requireContext(), resource.exception, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}