package com.thechance.movie.ui.category

import com.thechance.movie.ui.category.uiState.MediaUIState
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

