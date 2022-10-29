package com.karrar.movieapp.domain.home.getData.series

import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.mappers.series.TopRatedSeriesMapper
import com.karrar.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTopRatedTvShowSeriesUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val seriesMapper: TopRatedSeriesMapper,
) {

    operator fun invoke(): Flow<List<Media>> {
        return seriesRepository.getTopRatedTvShow().map {
            it.map(seriesMapper::map)
        }
    }
}