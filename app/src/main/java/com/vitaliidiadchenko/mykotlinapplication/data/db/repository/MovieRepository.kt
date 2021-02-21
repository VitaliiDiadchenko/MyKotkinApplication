package com.vitaliidiadchenko.mykotlinapplication.data.db.repository

import com.vitaliidiadchenko.mykotlinapplication.data.Actor
import com.vitaliidiadchenko.mykotlinapplication.data.ActorDetail
import com.vitaliidiadchenko.mykotlinapplication.data.Movie

interface MovieRepository {

    suspend fun getAllMovies(): List<Movie>

    suspend fun updateMoviesIntoDb(movies: List<Movie>)

    suspend fun getAllActorsByMovieId(movieId: Int): List<Actor>

    suspend fun updateActorsIntoDb(movieId: Int, actors: List<Actor>)

    suspend fun getActorDetailById(actorId: Int): ActorDetail

    suspend fun updateActorDetailIntoDb(actorDetail: ActorDetail)

    suspend fun getMovieWithMaxRating(): Movie

    suspend fun getMovieById(movieId: Int): Movie

}