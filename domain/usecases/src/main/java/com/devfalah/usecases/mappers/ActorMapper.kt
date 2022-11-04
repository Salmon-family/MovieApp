package com.devfalah.usecases.mappers

import com.thechance.local.database.entity.ActorEntity
import com.thechance.remote.response.actor.ActorDto
import javax.inject.Inject

class ActorMapper @Inject constructor() : Mapper<ActorDto, ActorEntity> {
    override fun map(input: ActorDto): ActorEntity {
        return ActorEntity(
            id = input.id ?: 0,
            name = input.name ?: "",
            imageUrl = input.profilePath ?: "",
        )
    }
}