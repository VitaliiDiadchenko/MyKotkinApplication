package com.vitaliidiadchenko.mykotlinapplication.network_module.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenresDto(
    val genres: List<GenreDto>
)
