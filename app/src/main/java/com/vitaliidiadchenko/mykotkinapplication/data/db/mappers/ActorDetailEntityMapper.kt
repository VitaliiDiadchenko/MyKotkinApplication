package com.vitaliidiadchenko.mykotkinapplication.data.db.mappers

import com.vitaliidiadchenko.mykotkinapplication.data.ActorDetail
import com.vitaliidiadchenko.mykotkinapplication.data.db.entity.ActorDetailEntity

object ActorDetailEntityMapper {

    internal fun toActorDetail(actorDetailEntity: ActorDetailEntity) =
        ActorDetail(
            id = actorDetailEntity.actorId,
            name = actorDetailEntity.name,
            picture = actorDetailEntity.picture,
            placeOfBirth = actorDetailEntity.placeOfBirth,
            popularity = actorDetailEntity.popularity,
            birthday = actorDetailEntity.birthday,
            biography = actorDetailEntity.biography
        )

    internal fun toActorDetailEntity(actorDetail: ActorDetail) =
        ActorDetailEntity(
            actorId = actorDetail.id,
            name = actorDetail.name,
            picture = actorDetail.picture,
            placeOfBirth = actorDetail.placeOfBirth,
            popularity = actorDetail.popularity,
            birthday = actorDetail.birthday,
            biography = actorDetail.biography
        )
}