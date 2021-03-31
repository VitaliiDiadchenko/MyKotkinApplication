package com.vitaliidiadchenko.mykotkinapplication.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.vitaliidiadchenko.mykotkinapplication.data.db.DbContract

@Entity(
    tableName = DbContract.ActorsDetailContract.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = ActorEntity::class,
        parentColumns = arrayOf("actorId"),
        childColumns = arrayOf("actorId"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["actorId"])]
)
data class ActorDetailEntity(
    @PrimaryKey
    val actorId: Int,
    val name: String,
    val picture: String?,
    val placeOfBirth: String?,
    val popularity: Float,
    val birthday: String?,
    val biography: String
)