package com.vitaliidiadchenko.mykotkinapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), FragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        R.layout.activity_main
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