package com.karrar.movieapp.domain.usecases.home.getData.series

import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.mappers.series.AiringTodaySeriesMapper
import com.karrar.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAiringTodaySeriesUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val seriesMapper: AiringTodaySeriesMapper,
) {

    operator fun invoke(): Flow<List<Media>> {
        return seriesRepository.getAiringToday().map {
            it.map(seriesMapper::map)
        }
    }
}