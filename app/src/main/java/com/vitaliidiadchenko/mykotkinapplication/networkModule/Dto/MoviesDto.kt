package com.vitaliidiadchenko.mykotkinapplication.networkModule.Dto

import kotlinx.serialization.Serializable

@Serializable
data class MoviesDto(
    val result: List<MovieDto>
)
