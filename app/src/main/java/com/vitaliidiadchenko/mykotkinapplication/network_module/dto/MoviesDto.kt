package com.vitaliidiadchenko.mykotkinapplication.network_module.dto

import kotlinx.serialization.Serializable

@Serializable
data class MoviesDto(
    val result: List<MovieDto>
)
