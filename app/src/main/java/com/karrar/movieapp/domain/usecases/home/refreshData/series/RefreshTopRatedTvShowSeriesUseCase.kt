package com.karrar.movieapp.domain.usecases.home.refreshData.series

import com.karrar.movieapp.data.local.database.entity.series.TopRatedSeriesEntity
import com.karrar.movieapp.data.local.mappers.series.TopRatedSeriesMapper
import com.karrar.movieapp.data.repository.SeriesRepository
import javax.inject.Inject

class RefreshTopRatedTvShowSeriesUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val seriesMapper: TopRatedSeriesMapper,
) {

    suspend operator fun invoke() {
        val items = mutableListOf<TopRatedSeriesEntity>()
        items.add(seriesMapper.map(seriesRepository.getTopRatedTvShow(1).first()))
        items.add(seriesMapper.map(seriesRepository.getPopularTvShow(1).first()))
        items.add(seriesMapper.map(seriesRepository.getAiringToday(1).first()))
        seriesRepository.deleteTopRatedTvShow()
        seriesRepository.insertTopRatedTvShow(items)
    }

}