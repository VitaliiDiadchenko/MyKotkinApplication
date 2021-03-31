package com.vitaliidiadchenko.mykotkinapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vitaliidiadchenko.mykotkinapplication.data.db.entity.ActorDetailEntity

@Dao
interface ActorDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActorDetail(actorDetail: ActorDetailEntity)

    @Query("DELETE FROM actors_detail WHERE actorId == :actorId")
    suspend fun deleteActorDetail(actorId: Int)

    @Query("SELECT * FROM actors_detail WHERE actorId == :actorId")
    suspend fun getActorDetailById(actorId: Int): ActorDetailEntity
}