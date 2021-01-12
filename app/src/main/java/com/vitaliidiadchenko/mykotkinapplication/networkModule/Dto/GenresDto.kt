package com.vitaliidiadchenko.mykotkinapplication.networkModule.Dto

import kotlinx.serialization.Serializable

@Serializable
data class GenresDto(
    val genres: List<GenreDto>
)
