package com.vitaliidiadchenko.mykotlinapplication.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vitaliidiadchenko.mykotlinapplication.data.db.DbContract

@Entity(tableName = DbContract.MoviesContract.TABLE_NAME)
data class MovieEntity(
    @PrimaryKey
    val movieId: Int,
    val title: String,
    val overview: String?,
    val poster: String?,
    val backdrop: String?,
    val ratings: Float,
    val adult: Boolean,
    val runtime: Int?,
    val genres: String,
    val like: Boolean = false
)
