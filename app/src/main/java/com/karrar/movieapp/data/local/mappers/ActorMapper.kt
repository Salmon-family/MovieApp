package com.karrar.movieapp.data.local.mappers

import com.karrar.movieapp.data.local.database.entity.ActorEntity
import com.karrar.movieapp.data.remote.response.actor.ActorDto
import com.karrar.movieapp.domain.mappers.Mapper
import javax.inject.Inject

class ActorMapper @Inject constructor(): Mapper<ActorDto, ActorEntity> {
    override fun map(input: ActorDto): ActorEntity {
        return ActorEntity(
            id = input.id?:0,
            name = input.name?:"",
            imageUrl = input.profilePath?:""
        )
    }
}