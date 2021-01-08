package com.vitaliidiadchenko.mykotkinapplication.networkService

import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getMovies(): MoviesPojo

    @GET("genre/movie/list")
    suspend fun getGenres(): GenresPojo

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Path("movie_id") movieId: Int
    ): ActorsPojo
}