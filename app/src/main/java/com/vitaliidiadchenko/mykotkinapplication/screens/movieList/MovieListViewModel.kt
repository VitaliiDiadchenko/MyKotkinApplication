package com.vitaliidiadchenko.mykotkinapplication.screens.movieList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliidiadchenko.mykotkinapplication.data.Movie
import com.vitaliidiadchenko.mykotkinapplication.data.db.Repository
import com.vitaliidiadchenko.mykotkinapplication.network_module.dto.moviesDtoMapping
import com.vitaliidiadchenko.mykotkinapplication.network_module.MovieApi
import com.vitaliidiadchenko.mykotkinapplication.screens.State
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieListViewModel(
    private val movieApi: MovieApi,
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableLiveData<State>(State.Init())
    val state: LiveData<State> = _state

    private val _moviesData = MutableLiveData<List<Movie>>()
    val moviesData: LiveData<List<Movie>> get() = _moviesData

    init {
        loadMoviesFromDb()
        loadMoviesFromNetwork()
    }

    private fun loadMoviesFromNetwork(){
        viewModelScope.launch {
            try {
                _state.postValue(State.Loading())
                val moviesDto = movieApi.getMovies()
                val genersDto = movieApi.getGenres()
                val movies = moviesDtoMapping(moviesDto.result, genersDto.genres)
                _moviesData.postValue(movies)
                _state.postValue(State.Success())
                updateMoviesIntoDb(movies)
            } catch (e: Exception) {
                _state.postValue(State.Error())
                Log.i("Error getting moviesData", e.message.toString())
            }
        }
    }

    private fun updateMoviesIntoDb(movies: List<Movie>){
        viewModelScope.launch {
            if(moviesData.value?.isNotEmpty() == true)
            repository.updateMoviesIntoDb(movies)
        }
    }

    private fun loadMoviesFromDb(){
        viewModelScope.launch {
           try {
               _state.postValue(State.Loading())
               val movies = repository.getAllMovies()
               if (movies.isNotEmpty()) {
                   _state.postValue(State.Success())
                   _moviesData.postValue(movies)
               } else {
                   _state.postValue(State.Error())
               }
           } catch (e: Exception) {
               _state.postValue(State.Error())
               Log.i("Error getting moviesData", e.message.toString())
           }
        }
    }


}