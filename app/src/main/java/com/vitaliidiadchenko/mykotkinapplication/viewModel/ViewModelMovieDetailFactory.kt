package com.vitaliidiadchenko.mykotkinapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitaliidiadchenko.mykotkinapplication.data.Movie
import java.lang.IllegalArgumentException

class ViewModelMovieDetailFactory (private val movie: Movie): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        ViewModelMovieDetail::class.java -> ViewModelMovieDetail(movie)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}