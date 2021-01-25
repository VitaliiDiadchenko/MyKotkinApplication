package com.vitaliidiadchenko.mykotkinapplication.data.db

import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.data.Movie
import com.vitaliidiadchenko.mykotkinapplication.data.db.entity.ActorEntity
import com.vitaliidiadchenko.mykotkinapplication.data.db.entity.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository : MovieRepository {

    private val moviesDb = MovieDataBase.instance

    override suspend fun getAllMovies(): List<Movie> = withContext(Dispatchers.IO) {
        moviesDb.movieDao().getAll().map { toMovie(it) }
    }

    override suspend fun updateMoviesIntoDb(movies: List<Movie>) = withContext(Dispatchers.IO) {
        moviesDb.movieDao().deleteAll()
        moviesDb.movieDao().insertAll(movies.map { toMovieEntity(it) })
    }

    override suspend fun getAllActorsByMovieId(movieId: Int): List<Actor> =
        withContext(Dispatchers.IO) {
            moviesDb.actorDao().getAllByMovieId(movieId).map { toActor(it) }
        }

    override suspend fun updateActorsIntoDb(movieId: Int, actors: List<Actor>) =
        withContext(Dispatchers.IO) {
            moviesDb.actorDao().deleteByMovieId(movieId)
            moviesDb.actorDao().insertAll(actors.map { toActorEntity(it, movieId) })
        }

    override suspend fun getActorById(actorId: Int): Actor =
        withContext(Dispatchers.IO) {
            toActor(moviesDb.actorDao().getActorById(actorId))
        }

    override suspend fun updateActor(actor: Actor, movieId: Int) {
        moviesDb.actorDao().deleteActor(actor.id)
        moviesDb.actorDao().insertActor(toActorEntity(actor, movieId))
    }

    private fun toMovie(movieEnt: MovieEntity) =
        Movie(
            id = movieEnt.movieId,
            title = movieEnt.title,
            overview = movieEnt.overview,
            poster = movieEnt.poster,
            backdrop = movieEnt.backdrop,
            ratings = movieEnt.ratings,
            adult = movieEnt.adult,
            runtime = movieEnt.runtime,
            genres = movieEnt.genres,
            like = movieEnt.like
        )

    private fun toMovieEntity(movie: Movie) =
        MovieEntity(
            id = null,
            movieId = movie.id,
            title = movie.title,
            overview = movie.overview,
            poster = movie.poster,
            backdrop = movie.backdrop,
            ratings = movie.ratings,
            adult = movie.adult,
            runtime = movie.runtime,
            genres = movie.genres,
            like = movie.like
        )

    private fun toActor(actorEnt: ActorEntity) =
        Actor(
            id = actorEnt.actorId,
            name = actorEnt.name,
            picture = actorEnt.picture,
            placeOfBirth = actorEnt.placeOfBirth,
            popularity = actorEnt.popularity,
            birthday = actorEnt.birthday,
            biography = actorEnt.biography
        )

    private fun toActorEntity(actor: Actor, movieId: Int) =
        ActorEntity(
            id = null,
            actorId = actor.id,
            name = actor.name,
            picture = actor.picture,
            placeOfBirth = actor.placeOfBirth,
            popularity = actor.popularity,
            birthday = actor.birthday,
            biography = actor.biography,
            movieId = movieId
        )
    companion object {

        private val repository by lazy { Repository() }
        fun createRepository(): Repository = repository

    }

}