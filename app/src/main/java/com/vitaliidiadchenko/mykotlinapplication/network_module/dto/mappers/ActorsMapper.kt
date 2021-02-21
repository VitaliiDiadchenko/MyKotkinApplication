package com.vitaliidiadchenko.mykotlinapplication.network_module.dto

import com.vitaliidiadchenko.mykotlinapplication.BuildConfig
import com.vitaliidiadchenko.mykotlinapplication.data.Actor
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