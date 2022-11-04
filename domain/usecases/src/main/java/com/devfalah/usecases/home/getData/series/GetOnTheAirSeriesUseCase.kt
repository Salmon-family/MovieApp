package com.devfalah.usecases.home.getData.series

import com.devfalah.models.Media
import com.devfalah.usecases.home.mappers.series.OnTheAirSeriesMapper
import com.thechance.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetOnTheAirSeriesUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val seriesMapper: OnTheAirSeriesMapper,
) {

    suspend operator fun invoke(): Flow<List<Media>> {
        return seriesRepository.getOnTheAir().map {
            it.map(seriesMapper::map)
        }
    }
}