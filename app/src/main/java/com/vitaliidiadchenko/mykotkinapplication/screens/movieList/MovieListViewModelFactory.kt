package com.vitaliidiadchenko.mykotkinapplication.screens.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitaliidiadchenko.mykotkinapplication.App


class MovieListViewModelFactory() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
        MovieListViewModel:: class.java -> MovieListViewModel(
        context = App.context())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")

    } as T
}