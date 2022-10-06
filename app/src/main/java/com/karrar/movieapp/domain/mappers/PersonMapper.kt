package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.ActorDto
import com.karrar.movieapp.domain.models.MediaInfo
import javax.inject.Inject

class PersonMapper @Inject constructor() : Mapper<ActorDto, MediaInfo> {
    override fun map(input: ActorDto): MediaInfo {
        return MediaInfo(
            id = input.id,
            name = input.name,
            profileImage = BuildConfig.IMAGE_BASE_PATH + input.profilePath,
            type = null,
            releaseDate = null,
            rate = null,
            imagePath = null
        )
    }
}
