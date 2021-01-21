package com.vitaliidiadchenko.mykotkinapplication.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.vitaliidiadchenko.mykotkinapplication.data.db.DbContract

@Entity(
    tableName = DbContract.ActorsContract.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("movieId"),
        onDelete = CASCADE
    )]
)
data class ActorEntity(
    @PrimaryKey(autoGenerate = true)
    val uniqueId: Int,
    val id: Int,
    val name: String,
    val picture: String,
    var placeOfBirth: String? = null,
    var popularity: Float = 0.0f,
    var birthday: String? = null,
    var biography: String? = null,
    val movieId: Int
)

