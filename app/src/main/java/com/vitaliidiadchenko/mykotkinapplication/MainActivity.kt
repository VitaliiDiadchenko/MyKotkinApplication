package com.vitaliidiadchenko.mykotkinapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun moveToMovieDetail() {
        val intent = Intent(this, MovieDetailActivity::class.java)
        startActivity(intent)
    }
}