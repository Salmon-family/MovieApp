package com.thechance.movie.ui.explore

import com.thechance.movie.ui.explore.exploreUIState.TrendyMediaUIState
import javax.inject.Inject


class TrendingUIStateMapper @Inject constructor():
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.Media, TrendyMediaUIState> {
    override fun map(input: com.devfalah.models.Media): TrendyMediaUIState {
        return TrendyMediaUIState(
            input.mediaID,
            input.mediaType,
            input.mediaImage
        )
    }
}

