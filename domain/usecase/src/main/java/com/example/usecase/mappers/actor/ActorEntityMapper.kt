package com.example.usecase.mappers.actor

import com.example.models.models.Actor
import com.example.usecase.mappers.Mapper
import com.thechance.repository.BuildConfig
import com.thechance.repository.local.database.entity.ActorEntity
import javax.inject.Inject

class ActorEntityMapper @Inject constructor() : Mapper<ActorEntity, Actor> {
    override fun map(input: ActorEntity): Actor {
        return Actor(
            actorID = input.id,
            actorName = input.name,
            actorImage = BuildConfig.IMAGE_BASE_PATH + input.imageUrl
        )
    }
}
