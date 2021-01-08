package com.vitaliidiadchenko.mykotkinapplication.screens.movieDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.networkService.MovieApiService
import com.vitaliidiadchenko.mykotkinapplication.networkService.deserializeActors
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailViewModel(private val movieApiService: MovieApiService) : ViewModel(){

    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> get() = _actors

    fun getActors(movieId: Int){
        viewModelScope.launch {
            try {
                val actorsPojo = movieApiService.getActors(movieId = movieId)
                val actors = deserializeActors(actorsPojo.cast)
            } catch (e: Exception) {
                Log.i("Error getting actorsData", e.message.toString())
            }
        }
    }
}