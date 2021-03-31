package com.vitaliidiadchenko.mykotkinapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitaliidiadchenko.mykotkinapplication.data.db.repository.RepositoryHolder
import kotlinx.serialization.ExperimentalSerializationApi

class MainActivityViewModelFactory: ViewModelProvider.Factory {

    @ExperimentalSerializationApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MainActivityViewModel::class.java -> MainActivityViewModel(
            repository = RepositoryHolder.createRepository()
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}