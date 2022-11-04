package com.devfalah.usecases.tvShowDetails


import com.devfalah.models.MediaDetailsReviews
import com.devfalah.models.Season
import com.devfalah.models.TvShowDetails
import com.devfalah.types.MediaType
import com.devfalah.usecases.Constant.MAX_NUM_REVIEWS
import com.devfalah.usecases.GetReviewsUseCase
import com.devfalah.usecases.home.mappers.ListMapper
import com.devfalah.usecases.home.mappers.SeriesMapperContainer

import javax.inject.Inject

class GetTvShowDetailsUseCase @Inject constructor(
    private val seriesRepository: com.thechance.repository.SeriesRepository,
    private val seriesMapperContainer: SeriesMapperContainer,
    private val getTVShowsReviews: GetReviewsUseCase
) {

    suspend fun getTvShowDetails(tvShowId: Int): TvShowDetails {
        return seriesRepository.getTvShowDetails(tvShowId)?.let {
            seriesMapperContainer.tvShowDetailsMapper.map(it)
        } ?: TvShowDetails()
    }

    suspend fun getSeriesCast(tvShowId: Int): List<com.devfalah.models.Actor> {
        return ListMapper(seriesMapperContainer.actorMapper)
            .mapList(seriesRepository.getTvShowCast(tvShowId)?.cast)
    }

    suspend fun getSeasons(tvShowId: Int): List<Season> {
        return ListMapper(seriesMapperContainer.seasonMapper)
            .mapList(seriesRepository.getTvShowDetails(tvShowId)?.season)
    }


    suspend fun getTvShowReviews(tvShowId: Int): MediaDetailsReviews {
        val reviews = getTVShowsReviews(MediaType.TV_SHOW, tvShowId)
        return MediaDetailsReviews(reviews.take(MAX_NUM_REVIEWS),
            reviews.size > MAX_NUM_REVIEWS)
    }


    suspend fun getTvShowRated(tvShowID: Int): Float {
        val result = seriesRepository.getRatedTvShow()
        return result?.let {
            it.find { it.id == tvShowID }?.rating ?: 0F
        } ?: throw Throwable("Error")
    }
}
