package com.vitaliidiadchenko.mykotkinapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vitaliidiadchenko.mykotkinapplication.data.Movie
import com.vitaliidiadchenko.mykotkinapplication.screens.FragmentListener
import com.vitaliidiadchenko.mykotkinapplication.screens.movieDetail.MovieDetailFragment
import com.vitaliidiadchenko.mykotkinapplication.screens.movieList.MovieListFragment

class MainActivity : AppCompatActivity(), FragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, MovieListFragment()).commit()
        }
    }

    override fun goToMoviesDetailsFragment(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.main_container, MovieDetailFragment.newInstance(movie))
            .commit()
    }

    override fun goToMoviesListFragment() {
        supportFragmentManager.popBackStack()
    }
}