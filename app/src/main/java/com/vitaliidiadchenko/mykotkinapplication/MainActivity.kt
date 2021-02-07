package com.vitaliidiadchenko.mykotkinapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.data.Movie
import com.vitaliidiadchenko.mykotkinapplication.screens.FragmentListener
import com.vitaliidiadchenko.mykotkinapplication.screens.actorDetail.ActorDetailFragment
import com.vitaliidiadchenko.mykotkinapplication.screens.movieDetail.MovieDetailFragment
import com.vitaliidiadchenko.mykotkinapplication.screens.movieList.MovieListFragment
import com.vitaliidiadchenko.mykotkinapplication.work_manager.MovieWorkRepository
import com.vitaliidiadchenko.mykotkinapplication.work_manager.MovieWorker

class MainActivity : AppCompatActivity(), FragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WorkManager.getInstance(App.context())
            .enqueueUniquePeriodicWork(
                MovieWorkRepository.MOVIES_UPDATE,
                ExistingPeriodicWorkPolicy.REPLACE,
                MovieWorkRepository().periodicUploadWork
            )

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