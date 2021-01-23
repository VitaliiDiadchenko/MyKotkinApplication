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
        parentColumns = arrayOf("movieId"),
        childColumns = arrayOf("movieId"),
        onDelete = CASCADE
    )]
)
data class ActorEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    val actorId: Int,
    val name: String,
    val picture: String?,
    val placeOfBirth: String?,
    val popularity: Float?,
    val birthday: String?,
    val biography: String?,
    val movieId: Int
)

