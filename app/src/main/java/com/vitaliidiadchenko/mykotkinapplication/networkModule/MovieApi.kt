package com.vitaliidiadchenko.mykotkinapplication.networkModule

import com.vitaliidiadchenko.mykotkinapplication.networkModule.Dto.ActorsDto
import com.vitaliidiadchenko.mykotkinapplication.networkModule.Dto.GenresDto
import com.vitaliidiadchenko.mykotkinapplication.networkModule.Dto.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("movie/popular")
    suspend fun getMovies(): MoviesDto

    @GET("genre/movie/list")
    suspend fun getGenres(): GenresDto

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Path("movie_id") movieId: Int
    ): ActorsDto
}