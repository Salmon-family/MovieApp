package com.karrar.movieapp.ui.tvShowDetails.episodes

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Episode
import com.karrar.movieapp.ui.tvShowDetails.episodes.EpisodeUIState
import javax.inject.Inject

class TvShowEpisodesUIMapper @Inject constructor() : Mapper<Episode, EpisodeUIState> {
    override fun map(input: Episode): EpisodeUIState {
        return EpisodeUIState(
            episodeId = input.episodeId,
            imageUrl = input.imageUrl,
            episodeName = input.episodeName,
            episodeDuration = input.episodeDuration,
            episodeDate = input.episodeDate,
            episodeRate = input.episodeRate,
            episodeTotalReviews = input.episodeTotalReviews,
            episodeDescription = input.episodeDescription,
            episodeNumber = input.episodeNumber
        )
    }
}
