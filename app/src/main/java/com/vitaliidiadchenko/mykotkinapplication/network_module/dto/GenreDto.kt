package com.vitaliidiadchenko.mykotkinapplication.network_module.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)
