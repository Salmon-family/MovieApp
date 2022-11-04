package com.karrar.movieapp.ui.search.uiStatMapper

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Media
import com.karrar.movieapp.ui.search.mediaSearchUIState.MediaUIState
import javax.inject.Inject


class SearchMediaUIStateMapper @Inject constructor():
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.Media, MediaUIState> {
    override fun map(input: com.devfalah.models.Media): MediaUIState {
        return MediaUIState(
            input.mediaID,
            input.mediaName,
            input.mediaImage,
            input.mediaType,
            input.mediaRate,
            input.mediaDate
        )
    }
}