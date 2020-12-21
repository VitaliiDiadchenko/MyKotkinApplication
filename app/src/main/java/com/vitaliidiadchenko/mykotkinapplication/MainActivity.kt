package com.vitaliidiadchenko.mykotkinapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vitaliidiadchenko.mykotkinapplication.data.Movie

class MainActivity : AppCompatActivity(), FragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, FragmentMovieList()).commit()
        }
    }

    override fun goToFragmentMoviesDetails(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.main_container, FragmentMovieDetail.newInstance(movie))
            .commit()
    }

    override fun goToFragmentMoviesList() {
        supportFragmentManager.popBackStack()
    }
}