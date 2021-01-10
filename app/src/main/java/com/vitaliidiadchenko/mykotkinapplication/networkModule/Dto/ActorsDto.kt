package com.vitaliidiadchenko.mykotkinapplication.networkModule.Dto

import kotlinx.serialization.Serializable

@Serializable
data class ActorsDto(
    val cast: List<ActorDto>
)
