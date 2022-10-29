package com.karrar.movieapp.domain.usecase.tvShowDetails

import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.mappers.series.WatchHistoryMapper
import com.karrar.movieapp.domain.models.WatchHistory
import javax.inject.Inject

class InsertTvShowUserCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val watchHistoryMapper: WatchHistoryMapper
) {

    suspend operator fun invoke(tvShow: WatchHistory) {
        return seriesRepository.insertTvShow(
            watchHistoryMapper.map(tvShow)
        )
    }
}
