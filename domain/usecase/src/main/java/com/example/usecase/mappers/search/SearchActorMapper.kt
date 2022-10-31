package com.example.usecase.mappers.search

import com.example.models.models.Media
import com.example.usecase.mappers.Mapper
import com.example.usecase.utilites.Constants
import com.thechance.repository.BuildConfig
import com.thechance.repository.remote.response.actor.ActorDto
import javax.inject.Inject

class SearchActorMapper @Inject constructor() : Mapper<ActorDto, Media> {
    override fun map(input: ActorDto): Media {
        return Media(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.profilePath,
            Constants.ACTOR,
            input.name ?: "",
            "",
            0F
        )
    }
}
