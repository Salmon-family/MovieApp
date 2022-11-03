package com.karrar.movieapp.domain.usecase.tvShowDetails

import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.mappers.series.EpisodeMapper
import com.karrar.movieapp.domain.models.Episode
import javax.inject.Inject

class GetSeasonsEpisodesUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val episodeMapper: EpisodeMapper,
) {
    suspend operator fun invoke(tvShowId: Int, seasonId: Int): List<Episode> {
        return seriesRepository.getSeasonDetails(tvShowId, seasonId)?.map(episodeMapper::map) ?: emptyList()
    }
}