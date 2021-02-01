package com.vitaliidiadchenko.mykotkinapplication.data.db.mappers

import com.vitaliidiadchenko.mykotkinapplication.data.Movie
import com.vitaliidiadchenko.mykotkinapplication.data.db.entity.MovieEntity

object MovieEntityMapper {
    internal fun toMovie(movieEnt: MovieEntity) =
        Movie(
            id = movieEnt.movieId,
            title = movieEnt.title,
            overview = movieEnt.overview,
            poster = movieEnt.poster,
            backdrop = movieEnt.backdrop,
            ratings = movieEnt.ratings,
            adult = movieEnt.adult,
            runtime = movieEnt.runtime,
            genres = movieEnt.genres.split(",").map { it.trim() },
            like = movieEnt.like
        )

    internal fun toMovieEntity(movie: Movie) =
        MovieEntity(
            movieId = movie.id,
            title = movie.title,
            overview = movie.overview,
            poster = movie.poster,
            backdrop = movie.backdrop,
            ratings = movie.ratings,
            adult = movie.adult,
            runtime = movie.runtime,
            genres = movie.genres.joinToString(","),
            like = movie.like
        )
}