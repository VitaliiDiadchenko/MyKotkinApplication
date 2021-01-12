package com.vitaliidiadchenko.mykotkinapplication.networkModule.Dto

import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)
