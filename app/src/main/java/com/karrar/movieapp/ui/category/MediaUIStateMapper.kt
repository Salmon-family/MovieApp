package com.karrar.movieapp.ui.category

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Media
import javax.inject.Inject

class MediaUIStateMapper @Inject constructor() : Mapper<Media, MediaUIState> {

    override fun map(input: Media): MediaUIState {
        return MediaUIState(
            mediaID = input.mediaID,
            mediaImage = input.mediaImage,
            mediaType = input.mediaType
        )
    }
}

