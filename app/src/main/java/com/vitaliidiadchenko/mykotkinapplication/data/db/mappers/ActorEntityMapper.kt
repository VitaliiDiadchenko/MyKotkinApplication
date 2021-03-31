package com.vitaliidiadchenko.mykotkinapplication.data.db.mappers

import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.data.db.entity.ActorEntity

object ActorEntityMapper {

    internal fun toActor(actorEnt: ActorEntity) =
        Actor(
            id = actorEnt.actorId,
            name = actorEnt.name,
            picture = actorEnt.picture,
        )

    internal fun toActorEntity(actor: Actor, movieId: Int) =
        ActorEntity(
            actorId = actor.id,
            name = actor.name,
            picture = actor.picture,
            movieId = movieId
        )
}