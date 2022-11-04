package com.karrar.movieapp.domain.usecases.home.getData.series

import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.usecases.home.mappers.series.TopRatedSeriesMapper
import com.thechance.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTopRatedTvShowSeriesUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val seriesMapper: TopRatedSeriesMapper,
) {

    suspend operator fun invoke(): Flow<List<Media>> {
        return seriesRepository.getTopRatedTvShow().map {
            it.map(seriesMapper::map)
        }
    }
}