package com.karrar.movieapp.ui.movieDetails.mapper

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Media
import com.karrar.movieapp.ui.models.MediaUiState
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