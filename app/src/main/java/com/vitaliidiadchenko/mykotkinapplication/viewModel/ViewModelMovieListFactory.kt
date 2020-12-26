package com.vitaliidiadchenko.mykotkinapplication.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


class ViewModelMovieListFactory(private val context: Context,
                                private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
        ViewModelMovieList:: class.java -> ViewModelMovieList(
            dispatcher = dispatcher,
            context = context,
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")

    } as T
}