package com.thechance.viewmodel.tvShowDetails.tvShowUIMapper

import com.thechance.viewmodel.tvShowDetails.episodes.TvShowEpisodesUIMapper
import javax.inject.Inject

class TvShowMapperContainer @Inject constructor(
    val tvShowDetailsUIMapper: TvShowDetailsResultUIMapper,
    val tvShowRatedUIMapper: TvShowRatedUIMapper,
    val tvShowCastUIMapper: SeriesCastUIMapper,
    val tvShowReviewUIMapper: TvShowReviewUIMapper,
    val tvShowSeasonUIMapper: TvShowSeasonUIMapper,
    val tvShowWatchHistoryMapper: TvShowWatchHistoryMapper,
    val tvShowEpisodesUIMapper: TvShowEpisodesUIMapper
)
