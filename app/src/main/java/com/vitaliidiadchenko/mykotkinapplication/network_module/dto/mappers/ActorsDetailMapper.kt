package com.vitaliidiadchenko.mykotkinapplication.network_module.dto.mappers

import com.vitaliidiadchenko.mykotkinapplication.BuildConfig
import com.vitaliidiadchenko.mykotkinapplication.data.ActorDetail
import com.vitaliidiadchenko.mykotkinapplication.network_module.dto.ActorDetailDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun convertActorDetailDtoToActorDetail (
    actorDetailDto: ActorDetailDto
): ActorDetail = withContext(Dispatchers.IO) {
    ActorDetail(
        id = actorDetailDto.id,
        biography = actorDetailDto.biography,
        birthday = actorDetailDto.birthday,
        picture = BuildConfig.BASE_IMAGE_URL + BuildConfig.BIG_POSTER_SIZE + actorDetailDto.picture,
        name = actorDetailDto.name,
        placeOfBirth = actorDetailDto.placeOfBirth,
        popularity = actorDetailDto.popularity / 2
    )
}