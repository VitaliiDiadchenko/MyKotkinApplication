package com.vitaliidiadchenko.mykotkinapplication.data.db.repository

import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.data.ActorDetail
import com.vitaliidiadchenko.mykotkinapplication.data.Movie
import com.vitaliidiadchenko.mykotkinapplication.data.db.MovieDataBase
import com.vitaliidiadchenko.mykotkinapplication.data.db.entity.ActorDetailEntity
import com.vitaliidiadchenko.mykotkinapplication.data.db.entity.ActorEntity
import com.vitaliidiadchenko.mykotkinapplication.data.db.entity.MovieEntity
import com.vitaliidiadchenko.mykotkinapplication.data.db.mappers.ActorDetailEntityMapper
import com.vitaliidiadchenko.mykotkinapplication.data.db.mappers.ActorEntityMapper
import com.vitaliidiadchenko.mykotkinapplication.data.db.mappers.MovieEntityMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl : MovieRepository {

    private val moviesDb = MovieDataBase.instance

    override suspend fun getAllMovies(): List<Movie> = withContext(Dispatchers.IO) {
        moviesDb.movieDao().getAll().map { MovieEntityMapper.toMovie(it) }
    }

    override suspend fun updateMoviesIntoDb(movies: List<Movie>) = withContext(Dispatchers.IO) {
        moviesDb.movieDao().deleteAll()
        moviesDb.movieDao().insertAll(movies.map { MovieEntityMapper.toMovieEntity(it) })
    }

    override suspend fun getAllActorsByMovieId(movieId: Int): List<Actor> =
        withContext(Dispatchers.IO) {
            moviesDb.actorDao().getAllByMovieId(movieId).map { ActorEntityMapper.toActor(it) }
        }

    override suspend fun updateActorsIntoDb(movieId: Int, actors: List<Actor>) =
        withContext(Dispatchers.IO) {
            moviesDb.actorDao().deleteByMovieId(movieId)
            moviesDb.actorDao()
                .insertAll(actors.map { ActorEntityMapper.toActorEntity(it, movieId) })
        }

    override suspend fun getActorDetailById(actorId: Int): ActorDetail =
        withContext(Dispatchers.IO) {
            ActorDetailEntityMapper.toActorDetail(
                moviesDb.actorDetailDao().getActorDetailById(actorId)
            )
        }

    override suspend fun updateActorDetailIntoDb(actorDetail: ActorDetail) =
        withContext(Dispatchers.IO) {
            moviesDb.actorDetailDao().deleteActorDetail(actorDetail.id)
            moviesDb.actorDetailDao()
                .insertActorDetail(ActorDetailEntityMapper.toActorDetailEntity(actorDetail))
        }

    override suspend fun getMovieWithMaxRating(): Movie = withContext(Dispatchers.IO) {
        MovieEntityMapper.toMovie(moviesDb.movieDao().getMovieWithMaxRating())
    }

    override suspend fun getMovieById(movieId: Int): Movie = withContext(Dispatchers.IO) {
        MovieEntityMapper.toMovie(moviesDb.movieDao().getMovieById(movieId))
    }
}