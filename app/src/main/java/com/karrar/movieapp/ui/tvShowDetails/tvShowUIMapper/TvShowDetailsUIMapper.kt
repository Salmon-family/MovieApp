package com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.TvShowDetails
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.TvShowDetailsResultUIState
import javax.inject.Inject

class TvShowDetailsResultUIMapper @Inject constructor() : Mapper<TvShowDetails, TvShowDetailsResultUIState> {
    override fun map(input: TvShowDetails): TvShowDetailsResultUIState {
        return TvShowDetailsResultUIState(
            tvShowId = input.tvShowId,
            tvShowName = input.tvShowName,
            tvShowOverview = input.tvShowOverview,
            tvShowImage = input.tvShowImage,
            tvShowVoteAverage = input.tvShowVoteAverage,
            tvShowReview = input.tvShowReview,
            tvShowReleaseDate = input.tvShowReleaseDate,
            tvShowGenres = input.tvShowGenres
        )
    }
}
