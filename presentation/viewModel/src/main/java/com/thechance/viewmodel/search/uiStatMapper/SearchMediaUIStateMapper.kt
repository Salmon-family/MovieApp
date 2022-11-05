package com.thechance.viewmodel.search.uiStatMapper

import com.devfalah.models.Media
import com.devfalah.usecases.mappers.Mapper
import com.thechance.viewmodel.search.mediaSearchUIState.MediaUIState
import javax.inject.Inject


class SearchMediaUIStateMapper @Inject constructor() :
    Mapper<Media, MediaUIState> {
    override fun map(input: Media): MediaUIState {
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