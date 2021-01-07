package com.vitaliidiadchenko.mykotkinapplication.networkService

import com.vitaliidiadchenko.mykotkinapplication.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MoviesPojo

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): GenresPojo

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Path("movie_id") movieId: Int
    ): ActorsPojo
}