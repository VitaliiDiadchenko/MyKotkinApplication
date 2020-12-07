package com.vitaliidiadchenko.mykotkinapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, FragmentMovieList()).commit()
        }
    }

    override fun goToFragmentMoviesDetails() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.main_container, FragmentMovieDetail())
            .commit()
    }

    override fun goToFragmentMoviesList() {
        supportFragmentManager.popBackStack()
    }
}