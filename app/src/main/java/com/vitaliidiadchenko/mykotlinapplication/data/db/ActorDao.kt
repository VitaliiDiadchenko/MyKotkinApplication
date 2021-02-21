package com.vitaliidiadchenko.mykotlinapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vitaliidiadchenko.mykotlinapplication.data.db.entity.ActorEntity

@Dao
interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(actors: List<ActorEntity>)

    @Query("DELETE FROM actors WHERE movieId == :movieId")
    suspend fun deleteByMovieId(movieId: Int)

    @Query("SELECT * FROM actors WHERE movieId == :movieId")
    suspend fun getAllByMovieId(movieId: Int): List<ActorEntity>


}