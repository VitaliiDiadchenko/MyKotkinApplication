package com.vitaliidiadchenko.mykotkinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), FragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, FragmentMoviesList()).commit()
        }
    }

    override fun goToFragmentMoviesDetails() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.main_container, FragmentMoviesDetails())
            .commit()
    }

    override fun goToFragmentMoviesList() {
        supportFragmentManager.popBackStack()
    }
}