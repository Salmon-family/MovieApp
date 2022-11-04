package com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Season
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.SeasonUIState
import javax.inject.Inject

class TvShowSeasonUIMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.Season, SeasonUIState> {
    override fun map(input: com.devfalah.models.Season): SeasonUIState {
        return SeasonUIState(
            seasonName = input.seasonName,
            seasonNumber = input.seasonNumber,
            imageUrl = input.imageUrl,
            episodeCount = input.episodeCount,
            seasonYear = input.seasonYear,
            seasonDescription = input.seasonDescription,
        )
    }
}
