package com.thechance.movie.ui.movieDetails.mapper

import com.thechance.movie.ui.models.MediaUiState
import javax.inject.Inject

class MediaUIStateMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.Media, MediaUiState> {
    override fun map(input: com.devfalah.models.Media): MediaUiState {
        return MediaUiState(
            id = input.mediaID,
            imageUrl = input.mediaImage,
        )
    }
}