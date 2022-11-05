package com.thechance.viewmodel.mappers


import com.devfalah.models.Media
import com.devfalah.usecases.mappers.Mapper
import com.thechance.viewmodel.models.MediaUiState
import javax.inject.Inject

class MediaUiMapper @Inject constructor() :
    Mapper<Media, MediaUiState> {
    override fun map(input: Media): MediaUiState {
        return MediaUiState(
            input.mediaID,
            input.mediaImage,
        )
    }
}