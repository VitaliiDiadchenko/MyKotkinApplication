package com.vitaliidiadchenko.mykotlinapplication.screens.movieList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliidiadchenko.mykotlinapplication.data.Movie
import com.vitaliidiadchenko.mykotlinapplication.data.db.repository.MovieRepositoryImpl
import com.vitaliidiadchenko.mykotlinapplication.network_module.dto.moviesDtoMapping
import com.vitaliidiadchenko.mykotlinapplication.network_module.MovieApi
import com.vitaliidiadchenko.mykotlinapplication.screens.State
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieListViewModel(
    private val movieApi: MovieApi,
    private val repository: MovieRepositoryImpl
) : ViewModel() {

    private val _state = MutableLiveData<State>(State.Init())
    val state: LiveData<State> = _state

    private val _moviesData = MutableLiveData<List<Movie>>()
    val moviesData: LiveData<List<Movie>> get() = _moviesData

    init {
        viewModelScope.launch {
            loadMoviesFromDb()
            loadMoviesFromNetwork()
        }
    }

    private suspend fun loadMoviesFromNetwork() {
        try {
            _state.postValue(State.Loading())
            val moviesDto = movieApi.getMovies()
            val genersDto = movieApi.getGenres()
            val movies = moviesDtoMapping(moviesDto.results, genersDto.genres)
            _moviesData.postValue(movies)
            _state.postValue(State.Success())
            updateMoviesIntoDb(movies)
        } catch (e: Exception) {
            _state.postValue(State.Error())
            Log.i("Error getting moviesData", e.message.toString())
        }
    }

    private suspend fun updateMoviesIntoDb(movies: List<Movie>) {
        if (moviesData.value?.isNotEmpty() == true)
            repository.updateMoviesIntoDb(movies)
    }

    private suspend fun loadMoviesFromDb() {
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