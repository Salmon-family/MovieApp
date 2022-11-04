package com.devfalah.usecases.tvShowDetails

import com.devfalah.usecases.home.mappers.series.EpisodeMapper
import com.thechance.repository.SeriesRepository
import javax.inject.Inject

class GetSeasonsEpisodesUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val episodeMapper: EpisodeMapper,
) {
    suspend operator fun invoke(tvShowId: Int): List<com.devfalah.models.Episode> {
        return seriesRepository.getSeasonDetails(tvShowId)?.map(episodeMapper::map) ?: emptyList()
    }
}