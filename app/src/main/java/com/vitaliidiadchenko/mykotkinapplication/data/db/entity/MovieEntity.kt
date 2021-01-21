package com.vitaliidiadchenko.mykotkinapplication.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vitaliidiadchenko.mykotkinapplication.data.db.DbContract

@Entity(tableName = DbContract.MoviesContract.TABLE_NAME)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val uniqueId: Int,
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val adult: Boolean,
    val runtime: Int,
    val genres: List<String>,
    val like: Boolean = false
)
