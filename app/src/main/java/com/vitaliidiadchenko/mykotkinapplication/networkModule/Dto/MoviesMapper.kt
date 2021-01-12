package com.vitaliidiadchenko.mykotkinapplication.networkModule.Dto

import com.vitaliidiadchenko.mykotkinapplication.BuildConfig
import com.vitaliidiadchenko.mykotkinapplication.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun moviesDtoMapping(
    moviesDto: List<MovieDto>, genres: List<GenreDto>
): List<Movie> = withContext(Dispatchers.IO) {

    val genresMap = genres.associateBy { it.id }

    moviesDto.map { moviesDto ->
        Movie(
            id = moviesDto.id,
            title = moviesDto.title,
            overview = moviesDto.overview,
            poster = BuildConfig.BASE_IMAGE_URL + BuildConfig.BIG_POSTER_SIZE + moviesDto.poster,
            backdrop = BuildConfig.BASE_IMAGE_URL + BuildConfig.BIG_POSTER_SIZE + moviesDto.backdrop,
            ratings = moviesDto.ratings,
            adult = moviesDto.adult,
            runtime = moviesDto.runtime,
            genres = moviesDto.genreIds.map {
                genresMap[it]?.name.toString()
            }
        )
    }
}