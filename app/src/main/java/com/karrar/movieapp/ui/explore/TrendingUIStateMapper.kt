package com.karrar.movieapp.ui.explore

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.mappers.account.UserMapper
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.explore.exploreUIState.TrendyMediaUIState
import javax.inject.Inject


class TrendingUIStateMapper @Inject constructor():
    Mapper<Media, TrendyMediaUIState> {
    override fun map(input: Media): TrendyMediaUIState {
        return TrendyMediaUIState(
            input.mediaID,
            input.mediaType,
            input.mediaImage
        )
    }
}

