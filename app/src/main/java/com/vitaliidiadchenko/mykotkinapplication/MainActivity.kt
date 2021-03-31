package com.vitaliidiadchenko.mykotkinapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.vitaliidiadchenko.mykotkinapplication.screens.movieList.MovieListFragmentDirections

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels { MainActivityViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            goToFragmentMovieDetail()
            intent?.let(::handleIntent)
        }
        setContentView(R.layout.activity_main)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            handleIntent(intent)
        }

    }

    private fun goToFragmentMovieDetail(){
        viewModel.navigateToSelectedMovie.observe(this, { movie ->
            if (null != movie) {
                findNavController(R.id.main_container)
                    .navigate(MovieListFragmentDirections.actionToMovieDetailFragment(movie))
            }
        })
    }

    private fun handleIntent(intent: Intent) {
        when (intent.action) {
            Intent.ACTION_VIEW -> {
                val movieId = intent.data?.lastPathSegment?.toIntOrNull()
                if (movieId != null) {
                    viewModel.showMovieFromNotification(movieId)
                    viewModel.showMovieFromNotificationComplete()

                }
            }
            else -> viewModel.startBackgroundMovieCheck()
        }
    }

}