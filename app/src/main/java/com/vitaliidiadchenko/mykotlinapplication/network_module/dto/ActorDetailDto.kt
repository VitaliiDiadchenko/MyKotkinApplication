package com.vitaliidiadchenko.mykotlinapplication.network_module.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorDetailDto(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val picture: String?,
    @SerialName("place_of_birth")
    val placeOfBirth: String?,
    val popularity: Float,
    val birthday: String?,
    val biography: String
)
