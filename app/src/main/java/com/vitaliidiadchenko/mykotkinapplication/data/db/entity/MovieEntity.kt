package com.vitaliidiadchenko.mykotkinapplication.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.vitaliidiadchenko.mykotkinapplication.data.db.Converter
import com.vitaliidiadchenko.mykotkinapplication.data.db.DbContract

@Entity(tableName = DbContract.MoviesContract.TABLE_NAME)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    val movieId: Int,
    val title: String,
    val overview: String?,
    val poster: String?,
    val backdrop: String?,
    val ratings: Float,
    val adult: Boolean,
    val runtime: Int,
    @TypeConverters(Converter::class)
    val genres: List<String>,
    val like: Boolean = false
)
