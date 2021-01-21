package com.vitaliidiadchenko.mykotkinapplication.data.db

import android.provider.BaseColumns

object DbContract {

    const val DATABASE_NAME = "movies.db"

    object MoviesContract {
        const val TABLE_NAME = "movies"
    }

    object ActorsContract {
        const val TABLE_NAME = "actors"
    }
}