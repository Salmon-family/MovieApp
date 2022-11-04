package com.karrar.movieapp.domain.usecases.home.mappers

import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Actor
import com.thechance.local.database.entity.ActorEntity
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class ActorEntityMapper @Inject constructor() : Mapper<ActorEntity, Actor> {
    override fun map(input:ActorEntity): Actor {
        return Actor(
            actorID = input.id,
            actorName = input.name,
            actorImage = BuildConfig.IMAGE_BASE_PATH + input.imageUrl
        )
    }
}