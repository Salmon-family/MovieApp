package com.karrar.movieapp.domain.usecase.tvShowDetails

import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.mappers.SeriesMapperContainer
import com.karrar.movieapp.domain.models.WatchHistory
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper.TvShowWatchHistoryMapper
import javax.inject.Inject

class GetInsertTvShowUserCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val seriesMapperContainer: SeriesMapperContainer,
) {

    suspend operator fun invoke(tvShow: WatchHistory) {
        return seriesRepository.insertTvShow(
            seriesMapperContainer.watchHistoryMapper.map(tvShow)
        )
    }
}