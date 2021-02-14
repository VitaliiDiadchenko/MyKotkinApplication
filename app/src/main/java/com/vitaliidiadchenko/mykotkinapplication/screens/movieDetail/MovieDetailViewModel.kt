package com.vitaliidiadchenko.mykotkinapplication.screens.movieDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.data.db.repository.MovieRepositoryImpl
import com.vitaliidiadchenko.mykotkinapplication.network_module.dto.actorsDtoMapping
import com.vitaliidiadchenko.mykotkinapplication.network_module.MovieApi
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailViewModel(
    var movieApi: MovieApi,
    private val repository: MovieRepositoryImpl
) : ViewModel() {

    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> get() = _actors

    fun getActors(movieId: Int) {
        viewModelScope.launch {
            loadActorsFromDb(movieId)
            loadActorsFromNetwork(movieId)
        }
    }

    private suspend fun loadActorsFromNetwork(movieId: Int) {
        try {
            val actorsDto = movieApi.getActors(movieId = movieId)
            val actorsList = actorsDtoMapping(actorsDto.cast)
            _actors.postValue(actorsList)
            updateActorsIntoDb(movieId)
        } catch (e: Exception) {
            Log.i("Error getting actorsData", e.message.toString())
        }
    }

    private suspend fun updateActorsIntoDb(movieId: Int) {
        if (actors.value?.isNotEmpty() == true) {
            repository.updateActorsIntoDb(movieId, actors.value!!)
        }
    }

    private suspend fun loadActorsFromDb(movieId: Int) {
        try {
            val actors = repository.getAllActorsByMovieId(movieId)
            if (actors.isNotEmpty()) _actors.postValue(actors)
        } catch (e: Exception) {
            Log.i("Error getting actorsData", e.message.toString())
        }
    }
}