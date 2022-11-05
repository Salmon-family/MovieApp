package com.thechance.viewmodel.tvShowDetails.tvShowUIMapper

import com.devfalah.models.TvShowDetails
import com.devfalah.usecases.mappers.Mapper
import com.thechance.viewmodel.tvShowDetails.tvShowUIState.TvShowDetailsResultUIState
import javax.inject.Inject

class TvShowDetailsResultUIMapper @Inject constructor() :
    Mapper<TvShowDetails, TvShowDetailsResultUIState> {
    override fun map(input: TvShowDetails): TvShowDetailsResultUIState {
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
