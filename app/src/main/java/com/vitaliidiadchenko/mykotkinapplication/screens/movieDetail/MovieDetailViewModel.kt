package com.vitaliidiadchenko.mykotkinapplication.screens.movieDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.networkModule.Dto.deserializeActors
import com.vitaliidiadchenko.mykotkinapplication.networkModule.MovieApiService
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailViewModel(private val movieApiService: MovieApiService) : ViewModel(){

    private var _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> get() = _actors

    fun getActors(movieId: Int){
        viewModelScope.launch {
            try {
                val actorsDto = movieApiService.getActors(movieId = movieId)
                val actors = deserializeActors(actorsDto.cast)
                _actors.postValue(actors)
            } catch (e: Exception) {
                Log.i("Error getting actorsData", e.message.toString())
            }
        }
    }
}