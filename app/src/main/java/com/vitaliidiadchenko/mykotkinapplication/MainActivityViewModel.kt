package com.vitaliidiadchenko.mykotkinapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.vitaliidiadchenko.mykotkinapplication.data.Movie
import com.vitaliidiadchenko.mykotkinapplication.data.db.repository.MovieRepositoryImpl
import com.vitaliidiadchenko.mykotkinapplication.work_manager.MovieWorkRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val repository: MovieRepositoryImpl
) : ViewModel() {

    private val _navigateToSelectedMovie = MutableLiveData<Movie?>()
    val navigateToSelectedMovie: LiveData<Movie?> get() = _navigateToSelectedMovie

    fun startBackgroundMovieCheck(){
        WorkManager.getInstance(App.context())
            .enqueueUniquePeriodicWork(
                MovieWorkRepository.MOVIES_UPDATE,
                ExistingPeriodicWorkPolicy.REPLACE,
                MovieWorkRepository().periodicUploadWork
            )
    }

    fun showMovieFromNotification(movieId: Int) {
        viewModelScope.launch {
            val movie = repository.getMovieById(movieId)
            _navigateToSelectedMovie.postValue(movie)
        }
    }

    fun showMovieFromNotificationComplete() {
        _navigateToSelectedMovie.postValue(null)
    }

}