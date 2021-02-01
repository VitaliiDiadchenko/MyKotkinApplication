package com.vitaliidiadchenko.mykotkinapplication.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
/*import androidx.room.TypeConverters
import com.vitaliidiadchenko.mykotkinapplication.data.db.Converter*/
import com.vitaliidiadchenko.mykotkinapplication.data.db.DbContract

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
    val runtime: Int,
    /*@TypeConverters(Converter::class)*/
    val genres: String,
    val like: Boolean = false
)
