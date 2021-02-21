package com.vitaliidiadchenko.mykotlinapplication.network_module

import com.vitaliidiadchenko.mykotlinapplication.BuildConfig
import com.vitaliidiadchenko.mykotlinapplication.network_module.dto.ActorDetailDto
import com.vitaliidiadchenko.mykotlinapplication.network_module.dto.ActorsDto
import com.vitaliidiadchenko.mykotlinapplication.network_module.dto.GenresDto
import com.vitaliidiadchenko.mykotlinapplication.network_module.dto.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): MoviesDto

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): GenresDto

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): ActorsDto

    @GET("person/{person_id}")
    suspend fun getActorDetail(
        @Path("person_id") personId: Int,
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): ActorDetailDto
}