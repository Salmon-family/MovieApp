package com.karrar.movieapp.ui.mappers


import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Media
import com.karrar.movieapp.ui.models.MediaUiState
import javax.inject.Inject

class MediaUiMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.Media, MediaUiState> {
    override fun map(input: com.devfalah.models.Media): MediaUiState {
        return MediaUiState(
            input.mediaID,
            input.mediaImage,
        )
    }
}