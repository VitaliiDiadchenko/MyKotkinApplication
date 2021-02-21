package com.vitaliidiadchenko.mykotlinapplication.network_module.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorDto(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val picture: String?
)
