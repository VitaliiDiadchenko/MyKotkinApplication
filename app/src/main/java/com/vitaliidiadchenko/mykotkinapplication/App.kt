package com.vitaliidiadchenko.mykotkinapplication

import android.app.Application
import android.content.Context
import com.vitaliidiadchenko.mykotkinapplication.data.db.Repository

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        private var context: Context? = null
        fun context(): Context = context ?: throw IllegalStateException()

        private val repository by lazy { Repository() }
        fun repository(): Repository = repository
    }
}