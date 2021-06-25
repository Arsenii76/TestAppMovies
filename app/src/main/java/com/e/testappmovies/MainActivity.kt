package com.e.testappmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.testappmovies.ui.movies.view.MoviesFragment

class MainActivity : AppCompatActivity() {

    private val moviesFragment = MoviesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState==null) supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, moviesFragment).commit()
        else supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, moviesFragment).commit()
        
    }
}