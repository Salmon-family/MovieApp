package com.karrar.movieapp.domain.home.refreshData.series

import com.karrar.movieapp.data.local.mappers.series.OnTheAirSeriesMapper
import com.karrar.movieapp.data.repository.SeriesRepository
import javax.inject.Inject

class RefreshOnTheAirSeriesUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val seriesMapper: OnTheAirSeriesMapper,
) {

    suspend operator fun invoke() {
        val items = seriesRepository.getOnTheAir(1).map(seriesMapper::map)
        seriesRepository.deleteOnTheAir()
        seriesRepository.insertOnTheAir(items)
    }

}