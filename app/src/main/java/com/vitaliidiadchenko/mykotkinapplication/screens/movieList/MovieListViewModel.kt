package com.vitaliidiadchenko.mykotkinapplication.screens.movieList

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliidiadchenko.mykotkinapplication.data.Movie
import com.vitaliidiadchenko.mykotkinapplication.data.loadMovies
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieListViewModel(private val context: Context) : ViewModel() {

    private val _state = MutableLiveData<State>(State.Init())
    val state: LiveData<State> = _state

    private val _moviesData = MutableLiveData<List<Movie>>()
    val moviesData: LiveData<List<Movie>> get() = _moviesData

    init {
        viewModelScope.launch {
            try {
                _state.postValue(State.Loading())
                val movies = loadMovies(context)
                _moviesData.postValue(movies)
                _state.postValue(State.Success())
            } catch (e: Exception) {
                _state.postValue(State.Error())
                Log.i("Error getting moviesData", e.message.toString())
            }
        }
    }
}