package com.vitaliidiadchenko.mykotlinapplication.network_module.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    @SerialName("poster_path")
    val poster: String,
    @SerialName("backdrop_path")
    val backdrop: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("vote_average")
    val ratings: Float,
    val overview: String,
    val adult: Boolean
)
