package com.thechance.viewmodel.category

import com.devfalah.models.Media
import com.devfalah.usecases.mappers.Mapper
import com.thechance.viewmodel.category.uiState.MediaUIState
import javax.inject.Inject

class MediaUIStateMapper @Inject constructor() :
    Mapper<Media, MediaUIState> {

    override fun map(input: Media): MediaUIState {
        return MediaUIState(
            mediaID = input.mediaID,
            mediaImage = input.mediaImage,
            mediaType = input.mediaType
        )
    }
}

