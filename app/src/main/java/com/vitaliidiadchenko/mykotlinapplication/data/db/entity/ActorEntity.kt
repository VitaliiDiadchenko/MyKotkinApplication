package com.vitaliidiadchenko.mykotlinapplication.data.db.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.vitaliidiadchenko.mykotlinapplication.data.db.DbContract

@Entity(
    tableName = DbContract.ActorsContract.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = arrayOf("movieId"),
        childColumns = arrayOf("movieId"),
        onDelete = CASCADE
    )],
    indices = [Index(value = ["movieId"])]
)
data class ActorEntity(
    @PrimaryKey
    val actorId: Int,
    val name: String,
    val picture: String?,
    val movieId: Int
)

