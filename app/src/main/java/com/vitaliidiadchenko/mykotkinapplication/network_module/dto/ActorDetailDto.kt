package com.vitaliidiadchenko.mykotkinapplication.network_module.dto

import kotlinx.serialization.SerialName

data class ActorDetailDto(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val picture: String,
    @SerialName("place_of_birth")
    val placeOfBirth: String,
    val popularity: Double,
    val birthday: String,
    val biography: String
)
