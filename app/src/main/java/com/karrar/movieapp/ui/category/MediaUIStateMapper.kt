package com.karrar.movieapp.ui.category

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Media
import com.karrar.movieapp.ui.category.uiState.MediaUIState
import javax.inject.Inject

class MediaUIStateMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.Media, MediaUIState> {

    override fun map(input: com.devfalah.models.Media): MediaUIState {
        return MediaUIState(
            mediaID = input.mediaID,
            mediaImage = input.mediaImage,
            mediaType = input.mediaType
        )
    }
}

