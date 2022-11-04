package com.devfalah.usecases.home.mappers

import com.devfalah.usecases.mappers.Mapper
import com.thechance.local.database.entity.ActorEntity
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class ActorEntityMapper @Inject constructor() : Mapper<ActorEntity, com.devfalah.models.Actor> {
    override fun map(input:ActorEntity): com.devfalah.models.Actor {
        return com.devfalah.models.Actor(
            actorID = input.id,
            actorName = input.name,
            actorImage = BuildConfig.IMAGE_BASE_PATH + input.imageUrl
        )
    }
}