package com.karrar.movieapp.domain.mappers.actor

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.local.database.entity.ActorEntity
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Actor
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