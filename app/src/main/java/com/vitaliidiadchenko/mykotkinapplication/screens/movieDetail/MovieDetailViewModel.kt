package com.vitaliidiadchenko.mykotkinapplication.screens.movieDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.network_module.dto.actorsDtoMapping
import com.vitaliidiadchenko.mykotkinapplication.network_module.MovieApi
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailViewModel(private val movieApi: MovieApi) : ViewModel(){

    private var _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> get() = _actors

    fun getActors(movieId: Int){
        viewModelScope.launch {
            try {
                val actorsDto = movieApi.getActors(movieId = movieId)
                val actors = actorsDtoMapping(actorsDto.cast)
                _actors.postValue(actors)
            } catch (e: Exception) {
                Log.i("Error getting actorsData", e.message.toString())
            }
        }
    }
}