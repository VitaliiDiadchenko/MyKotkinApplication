package com.vitaliidiadchenko.mykotkinapplication.networkModule.Dto

import androidx.lifecycle.MutableLiveData
import com.vitaliidiadchenko.mykotkinapplication.BuildConfig
import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//method to deserialize ListActors
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