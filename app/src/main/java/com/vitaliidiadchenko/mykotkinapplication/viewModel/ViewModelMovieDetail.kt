package com.vitaliidiadchenko.mykotkinapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vitaliidiadchenko.mykotkinapplication.data.Movie

class ViewModelMovieDetail(movie: Movie) : ViewModel() {

    private val _setMovie = MutableLiveData<Movie>()
    val setMovie: LiveData<Movie> get() = _setMovie

    init {
        _setMovie.postValue(movie)
    }
}