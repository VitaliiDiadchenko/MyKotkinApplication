package com.vitaliidiadchenko.mykotkinapplication.data.db.repository

object RepositoryHolder {

    private val repository by lazy { MovieRepositoryImpl() }
    fun createRepository(): MovieRepositoryImpl = repository

}