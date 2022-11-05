package com.thechance.viewmodel.tvShowDetails.episodes

import com.devfalah.models.Episode
import com.devfalah.usecases.mappers.Mapper
import javax.inject.Inject

class TvShowEpisodesUIMapper @Inject constructor() :
    Mapper<Episode, EpisodeUIState> {
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
