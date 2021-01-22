package com.vitaliidiadchenko.mykotkinapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vitaliidiadchenko.mykotkinapplication.data.db.entity.ActorEntity

@Dao
interface ActorDao {

    @Query("SELECT * FROM actors")
    suspend fun getAll(): List<ActorEntity>

    @Query("DELETE FROM actors")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(actors: List<ActorEntity>)

}