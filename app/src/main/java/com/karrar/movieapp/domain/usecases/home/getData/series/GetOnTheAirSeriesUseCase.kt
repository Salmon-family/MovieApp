package com.karrar.movieapp.domain.usecases.home.getData.series

import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.usecases.home.mappers.series.OnTheAirSeriesMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetOnTheAirSeriesUseCase @Inject constructor(
    private val seriesRepository: com.thechance.repository.SeriesRepository,
    private val seriesMapper: OnTheAirSeriesMapper,
) {

    suspend operator fun invoke(): Flow<List<Media>> {
        return seriesRepository.getOnTheAir().map {
            it.map(seriesMapper::map)
        }
    }
}