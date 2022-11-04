package com.devfalah.usecases.home.mappers.search

import com.devfalah.usecases.Constant
import com.thechance.remote.response.actor.ActorDto
import com.devfalah.usecases.mappers.Mapper
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class SearchActorMapper @Inject constructor() : Mapper<ActorDto, com.devfalah.models.Media> {
    override fun map(input: ActorDto): com.devfalah.models.Media {
        return com.devfalah.models.Media(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.profilePath,
            Constant.ACTOR,
            input.name ?: "",
            "",
            0F
        )
    }
}
