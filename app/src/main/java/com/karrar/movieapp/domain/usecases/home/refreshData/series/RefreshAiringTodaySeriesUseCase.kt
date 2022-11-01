package com.karrar.movieapp.domain.usecases.home.refreshData.series

import com.karrar.movieapp.data.local.mappers.series.AiringTodaySeriesMapper
import com.karrar.movieapp.data.repository.SeriesRepository
import javax.inject.Inject

class RefreshAiringTodaySeriesUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val seriesMapper: AiringTodaySeriesMapper,
) {

    suspend operator fun invoke() {
        val items = seriesRepository.getAiringToday(1).map(seriesMapper::map)
        seriesRepository.deleteAiringToday()
        seriesRepository.insertAiringToday(items)
    }

}