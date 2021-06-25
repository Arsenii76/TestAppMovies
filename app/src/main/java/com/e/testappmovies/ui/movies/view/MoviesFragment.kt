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
import com.e.testappmovies.data.repository.helper.ApiHelperMovies
import com.e.testappmovies.ui.ViewState.SUCCESS
import com.e.testappmovies.ui.ViewState.ERROR
import com.e.testappmovies.ui.ViewState.LOADING
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
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupViewModel()
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) { setupObserver() }

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

    private fun setupObserver(){
        movesViewModel.moviesLiveData.observe(requireActivity(), {
            it.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        resource.data?.let { movies ->
                            progressBarMovies.visibility = View.GONE
                            val arrayList = ArrayList<ItemMovies>()
                            Log.d("results", movies.results.size.toString())
                            movies.results.forEachIndexed { index, _ ->
                                arrayList.add(
                                    ItemMovies(
                                        title = movies.results[index].title,
                                        description = movies.results[index].description,
                                        image = movies.results[index].multimedia.image
                                    )
                                )
                            }
                            adapterMovies.addMovies(arrayList)
                        }
                    }
                    LOADING -> {
                        progressBarMovies.visibility = View.VISIBLE
                    }
                    ERROR -> {
                        progressBarMovies.visibility = View.GONE
                        Toast.makeText(requireContext(), "Ошибка! ${resource.msg}", Toast.LENGTH_SHORT).show()
                        Log.d("fdsfsd","${resource.msg}")
                    }
                }
            }
        })
    }
}