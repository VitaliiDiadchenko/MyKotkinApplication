package com.vitaliidiadchenko.mykotlinapplication.data.db.mappers

import com.vitaliidiadchenko.mykotlinapplication.data.Actor
import com.vitaliidiadchenko.mykotlinapplication.data.db.entity.ActorEntity

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