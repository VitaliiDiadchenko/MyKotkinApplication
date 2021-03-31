package com.vitaliidiadchenko.mykotkinapplication.network_module.dto

import com.vitaliidiadchenko.mykotkinapplication.BuildConfig
import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun actorsDtoMapping(
    actorsDto: List<ActorDto>
): List<Actor> = withContext(Dispatchers.IO) {
    actorsDto.map { actorsDto ->
        Actor(
            id = actorsDto.id,
            name = actorsDto.name,
            picture = BuildConfig.BASE_IMAGE_URL + BuildConfig.ACTOR_AVATAR_SIZE + actorsDto.picture
        )
    }
}