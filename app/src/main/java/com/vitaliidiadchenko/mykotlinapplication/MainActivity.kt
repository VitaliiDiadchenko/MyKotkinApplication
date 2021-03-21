package com.vitaliidiadchenko.mykotlinapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.vitaliidiadchenko.mykotlinapplication.data.Actor
import com.vitaliidiadchenko.mykotlinapplication.screens.FragmentListener
import com.vitaliidiadchenko.mykotlinapplication.screens.actorDetail.ActorDetailFragment
import com.vitaliidiadchenko.mykotlinapplication.screens.movieDetail.MovieDetailFragment
import com.vitaliidiadchenko.mykotlinapplication.screens.movieList.MovieListFragment
import com.vitaliidiadchenko.mykotlinapplication.work_manager.MovieWorkRepository

class MainActivity : AppCompatActivity(), FragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WorkManager.getInstance(App.context())
            .enqueueUniquePeriodicWork(
                MovieWorkRepository.MOVIES_UPDATE,
                ExistingPeriodicWorkPolicy.REPLACE,
                MovieWorkRepository().periodicUploadWork
            )

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, MovieListFragment()).commit()

            intent?.let(::handleIntent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            handleIntent(intent)
        }
    }

    private fun handleIntent(intent: Intent) {
        when (intent.action) {
            Intent.ACTION_VIEW -> {
                val movieId = intent.data?.lastPathSegment?.toIntOrNull()
                if (movieId != null) {
                    goToMoviesDetailsFragment(movieId)
                }
            }
        }
    }

    override fun goToMoviesDetailsFragment(movieId: Int) {
        val view = findViewById<CardView>(R.id.card_movie)
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.main_container, MovieDetailFragment.newInstance(movieId))
            .addSharedElement(view, App.context().getString(R.string.card_view_item_transition))
            .commit()
    }

    override fun goToActorDetailFragment(actor: Actor) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.main_container, ActorDetailFragment.newInstance(actor))
            .commit()
    }

    override fun goToMoviesListFragment() {
        supportFragmentManager.popBackStack()
    }
}