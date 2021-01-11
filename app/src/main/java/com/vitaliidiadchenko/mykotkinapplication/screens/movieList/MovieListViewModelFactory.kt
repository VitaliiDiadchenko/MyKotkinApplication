package com.vitaliidiadchenko.mykotkinapplication.screens.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitaliidiadchenko.mykotkinapplication.networkModule.MovieApiHolder
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.create


class MovieListViewModelFactory() : ViewModelProvider.Factory {

    @ExperimentalSerializationApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
        MovieListViewModel:: class.java -> MovieListViewModel(
        movieApiService = MovieApiHolder.retrofit.create())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}