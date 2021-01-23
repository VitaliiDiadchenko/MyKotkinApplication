package com.vitaliidiadchenko.mykotkinapplication.screens.movieDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.data.db.Repository
import com.vitaliidiadchenko.mykotkinapplication.network_module.dto.actorsDtoMapping
import com.vitaliidiadchenko.mykotkinapplication.network_module.MovieApi
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailViewModel(
    private val movieApi: MovieApi,
    private val repository: Repository
) : ViewModel() {

    private var _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> get() = _actors

    fun getActors(movieId: Int) {
        loadActorsFromDb(movieId)
        loadActorsFromNetwork(movieId)
    }

    private fun loadActorsFromNetwork(movieId: Int) {
        viewModelScope.launch {
            try {
                val actorsDto = movieApi.getActors(movieId = movieId)
                val actorsList = actorsDtoMapping(actorsDto.cast)
                _actors.postValue(actorsList)
                updateActorsIntoDb(movieId)
            } catch (e: Exception) {
                Log.i("Error getting actorsData", e.message.toString())
            }
        }
    }

    private fun updateActorsIntoDb(movieId: Int) {
        if(actors.value?.isNotEmpty() == true) {
            viewModelScope.launch {
                repository.updateActorsIntoDb(movieId, actors.value!!)
            }
        }
    }

    private fun loadActorsFromDb(movieId: Int) {
        viewModelScope.launch {
            try{
                val actors = repository.getAllActorsByMovieId(movieId)
                if(actors.isNotEmpty()) _actors.postValue(actors)
            } catch (e: Exception) {
                Log.i("Error getting actorsData", e.message.toString())
            }
        }
    }
}