package com.vitaliidiadchenko.mykotkinapplication.data.db.repository

import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.data.ActorDetail
import com.vitaliidiadchenko.mykotkinapplication.data.Movie

interface MovieRepository {

    suspend fun getAllMovies(): List<Movie>

    suspend fun updateMoviesIntoDb(movies: List<Movie>)

    suspend fun getAllActorsByMovieId(movieId: Int): List<Actor>

    suspend fun updateActorsIntoDb(movieId: Int, actors: List<Actor>)

    suspend fun getActorDetailById(actorId: Int): ActorDetail

    suspend fun updateActorDetailIntoDb(actorDetail: ActorDetail)

}