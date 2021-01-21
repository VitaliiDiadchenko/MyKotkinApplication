package com.vitaliidiadchenko.mykotkinapplication.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vitaliidiadchenko.mykotkinapplication.App
import com.vitaliidiadchenko.mykotkinapplication.data.db.entity.ActorEntity
import com.vitaliidiadchenko.mykotkinapplication.data.db.entity.MovieEntity

@Database(entities = [MovieEntity::class, ActorEntity::class], version = 1, exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorDao

    companion object {
        val instance: MovieDataBase by lazy {
            Room.databaseBuilder(
                App.context(),
                MovieDataBase::class.java,
                DbContract.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}