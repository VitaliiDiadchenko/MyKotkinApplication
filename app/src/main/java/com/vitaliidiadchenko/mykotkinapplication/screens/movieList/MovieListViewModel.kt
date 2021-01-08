package com.vitaliidiadchenko.mykotkinapplication.screens.movieList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliidiadchenko.mykotkinapplication.data.Movie
import com.vitaliidiadchenko.mykotkinapplication.networkService.MovieApiService
import com.vitaliidiadchenko.mykotkinapplication.networkService.deserializeMovies
import com.vitaliidiadchenko.mykotkinapplication.screens.State
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieListViewModel(private val movieApiService: MovieApiService) : ViewModel() {

    private val _state = MutableLiveData<State>(State.Init())
    val state: LiveData<State> = _state

    private val _moviesData = MutableLiveData<List<Movie>>()
    val moviesData: LiveData<List<Movie>> get() = _moviesData

    init {
        viewModelScope.launch {
            try {
                _state.postValue(State.Loading())
                val moviesPojo = movieApiService.getMovies()
                val genersPojo = movieApiService.getGenres()
                val movies = deserializeMovies(moviesPojo.result, genersPojo.genres)
                _moviesData.postValue(movies)
                _state.postValue(State.Success())
            } catch (e: Exception) {
                _state.postValue(State.Error())
                Log.i("Error getting moviesData", e.message.toString())
            }
        }
    }
}