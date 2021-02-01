package com.vitaliidiadchenko.mykotkinapplication.data.db

object DbContract {

    const val DATABASE_NAME = "movies.db"

    object MoviesContract {
        const val TABLE_NAME = "movies"
    }

    object ActorsContract {
        const val TABLE_NAME = "actors"
    }

    object ActorsDetailContract {
        const val TABLE_NAME = "actors_detail"
    }
}