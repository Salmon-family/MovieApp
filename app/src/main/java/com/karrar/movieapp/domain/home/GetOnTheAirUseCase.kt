package com.karrar.movieapp.domain.home

import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.mappers.series.OnTheAirSeriesMapper
import com.karrar.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetOnTheAirUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val seriesMapper: OnTheAirSeriesMapper,
) {

    operator fun invoke(): Flow<List<Media>> {
        return seriesRepository.getOnTheAir().map {
            it.map(seriesMapper::map)
        }
    }
}