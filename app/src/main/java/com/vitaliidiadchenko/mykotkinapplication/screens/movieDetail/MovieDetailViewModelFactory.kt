package com.vitaliidiadchenko.mykotkinapplication.screens.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitaliidiadchenko.mykotkinapplication.networkModule.MovieApiFactory
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.create

class MovieDetailViewModelFactory : ViewModelProvider.Factory {

    @ExperimentalSerializationApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieDetailViewModel::javaClass -> MovieDetailViewModel(
            movieApiService = MovieApiFactory.retrofit.create())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    }as T
}