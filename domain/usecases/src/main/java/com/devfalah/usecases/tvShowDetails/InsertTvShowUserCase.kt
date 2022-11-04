package com.devfalah.usecases.tvShowDetails

import com.devfalah.models.TvShowDetails
import com.devfalah.usecases.home.mappers.series.WatchHistoryMapper
import com.thechance.repository.SeriesRepository
import javax.inject.Inject

class InsertTvShowUserCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val watchHistoryMapper: WatchHistoryMapper
) {

    suspend operator fun invoke(tvShow: TvShowDetails) {
        return seriesRepository.insertTvShow(
            watchHistoryMapper.map(tvShow)
        )
    }
}
