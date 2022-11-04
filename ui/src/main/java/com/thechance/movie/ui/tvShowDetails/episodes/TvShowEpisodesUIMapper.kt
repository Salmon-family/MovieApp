package com.thechance.movie.ui.tvShowDetails.episodes

import javax.inject.Inject

class TvShowEpisodesUIMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.Episode, EpisodeUIState> {
    override fun map(input: com.devfalah.models.Episode): EpisodeUIState {
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
