package com.vitaliidiadchenko.mykotkinapplication.networkService

import com.vitaliidiadchenko.mykotkinapplication.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesPojo(
    val result: List<MoviePojo>
)

@Serializable
data class MoviePojo(
    val id: Int,
    val title: String,
    @SerialName("poster_path")
    val poster: String,
    @SerialName("backdrop_path")
    val backdrop: String,
    val runtime: Int,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("vote_average")
    val ratings: Float,
    val overview: String,
    val adult: Boolean
)

@Serializable
data class ActorsPojo(
    val cast: List<ActorPojo>
)

@Serializable
data class ActorPojo(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val picture: String
)

@Serializable
data class GenresPojo(
    val genres: List<GenrePojo>
)

@Serializable
data class GenrePojo(
    val id: Int,
    val name: String
)
//method to deserialize ListMovies
suspend fun deserializeMovies(
    moviesPojo: List<MoviePojo>, genres: List<GenrePojo>
): List<Movie> = withContext(Dispatchers.IO) {

    val genresMap = genres.associateBy { it.id }

    moviesPojo.map { moviesPojo ->
        Movie(
            id = moviesPojo.id,
            title = moviesPojo.title,
            overview = moviesPojo.overview,
            poster = moviesPojo.poster,
            backdrop = moviesPojo.backdrop,
            ratings = moviesPojo.ratings,
            adult = moviesPojo.adult,
            runtime = moviesPojo.runtime,
            genres = moviesPojo.genreIds.map {
                genresMap[it]?.name.toString()
            }
        )
    }
}
//method to deserialize ListActors
suspend fun deserializeActors(
    actorsPojo: List<ActorPojo>
): List<Actor> = withContext(Dispatchers.IO) {
    actorsPojo.map { actorPojo ->
        Actor(
            id = actorPojo.id,
            name = actorPojo.name,
            picture = actorPojo.picture
        )
    }
}
