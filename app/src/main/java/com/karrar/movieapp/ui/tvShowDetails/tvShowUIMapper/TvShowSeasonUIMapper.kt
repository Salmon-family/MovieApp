package com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Season
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.EpisodeUIState
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.SeasonUIState
import javax.inject.Inject

class TvShowSeasonUIMapper @Inject constructor() : Mapper<Season, SeasonUIState> {
    override fun map(input: Season): SeasonUIState {
        return SeasonUIState(
            seasonId = input.seasonId,
            seasonName = input.seasonName,
            seasonNumber = input.seasonNumber,
            imageUrl = input.imageUrl,
            episodeCount = input.episodeCount,
            seasonYear = input.seasonYear,
            seasonDescription = input.seasonDescription,
            episodes = input.episodes.map { episode ->
                EpisodeUIState(
                    episodeId = episode.episodeId,
                    episodeName = episode.episodeName,
                    episodeNumber = episode.episodeNumber,
                    imageUrl = episode.imageUrl,
                    episodeTotalReviews = episode.episodeTotalReviews,
                    episodeRate = episode.episodeRate,
                    episodeDuration = episode.episodeDuration,
                    episodeDate = episode.episodeDate,
                    episodeDescription = episode.episodeDescription
                )
            }
        )
    }
}
