package com.vitaliidiadchenko.mykotkinapplication.network_module

import com.vitaliidiadchenko.mykotkinapplication.network_module.dto.ActorDetailDto
import com.vitaliidiadchenko.mykotkinapplication.network_module.dto.ActorsDto
import com.vitaliidiadchenko.mykotkinapplication.network_module.dto.GenresDto
import com.vitaliidiadchenko.mykotkinapplication.network_module.dto.MoviesDto
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

    @GET("person/{person_id}")
    suspend fun getActor(
        @Path("person_id") personId: Int
    ): ActorDetailDto
}