package com.karrar.movieapp.domain.usecases.tvShowDetails

import com.karrar.movieapp.domain.models.Episode
import com.karrar.movieapp.domain.usecases.home.mappers.series.EpisodeMapper
import com.thechance.repository.SeriesRepository
import javax.inject.Inject

class GetSeasonsEpisodesUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val episodeMapper: EpisodeMapper,
) {
    suspend operator fun invoke(tvShowId: Int): List<Episode> {
        return seriesRepository.getSeasonDetails(tvShowId)?.map(episodeMapper::map) ?: emptyList()
    }
}