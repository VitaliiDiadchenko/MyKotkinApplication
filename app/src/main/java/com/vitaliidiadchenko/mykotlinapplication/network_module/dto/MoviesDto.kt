package com.vitaliidiadchenko.mykotlinapplication.network_module.dto

import kotlinx.serialization.Serializable

@Serializable
data class MoviesDto(
    val results: List<MovieDto>
)

