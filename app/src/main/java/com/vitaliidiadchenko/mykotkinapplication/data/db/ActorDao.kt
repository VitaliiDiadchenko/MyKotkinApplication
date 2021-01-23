package com.vitaliidiadchenko.mykotkinapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vitaliidiadchenko.mykotkinapplication.data.db.entity.ActorEntity

@Dao
interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(actors: List<ActorEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActor(actor: ActorEntity)

    @Query("DELETE FROM actors WHERE actorId == :actorId")
    suspend fun deleteActor(actorId: Int)

    @Query("DELETE FROM actors WHERE movieId == :movieId")
    suspend fun deleteByMovieId(movieId: Int)

    @Query("SELECT * FROM actors WHERE movieId == :movieId")
    suspend fun getAllByMovieId(movieId: Int): List<ActorEntity>

    @Query("SELECT * FROM actors WHERE actorId == :actorId")
    suspend fun getActorById(actorId: Int): ActorEntity
}