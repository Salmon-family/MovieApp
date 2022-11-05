package com.thechance.viewmodel.explore

import com.devfalah.models.Media
import com.devfalah.usecases.mappers.Mapper
import com.thechance.viewmodel.explore.exploreUIState.TrendyMediaUIState
import javax.inject.Inject


class TrendingUIStateMapper @Inject constructor() :
    Mapper<Media, TrendyMediaUIState> {
    override fun map(input: Media): TrendyMediaUIState {
        return TrendyMediaUIState(
            input.mediaID,
            input.mediaType,
            input.mediaImage
        )
    }
}

