package com.vitaliidiadchenko.mykotkinapplication.data.db

import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.data.Movie

interface MovieRepository {

    suspend fun getAllMovies(): List<Movie>

    suspend fun updateMoviesIntoDb(movies: List<Movie>)

    suspend fun getAllActorsByMovieId(movieId: Int): List<Actor>

    suspend fun updateActorsIntoDb(movieId: Int, actors: List<Actor>)

    suspend fun getActorById(actorId: Int): Actor

    suspend fun updateActor(actor: Actor, movieId: Int)

}