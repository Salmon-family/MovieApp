package com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.TvShowDetails
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.TvShowDetailsResultUIState
import javax.inject.Inject

class TvShowDetailsResultUIMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.TvShowDetails, TvShowDetailsResultUIState> {
    override fun map(input: com.devfalah.models.TvShowDetails): TvShowDetailsResultUIState {
        return TvShowDetailsResultUIState(
            tvShowId = input.tvShowId,
            tvShowName = input.tvShowName,
            tvShowOverview = input.tvShowOverview,
            tvShowImage = input.tvShowImage,
            tvShowVoteAverage = input.tvShowVoteAverage,
            tvShowReview = input.tvShowReview,
            tvShowReleaseDate = input.tvShowReleaseDate,
            tvShowGenres = input.tvShowGenres,
            tvShowSeasonsNumber = input.tvShowSeasonsNumber
        )
    }
}
