package com.karrar.movieapp.domain.usecases.tvShowDetails

import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.SeriesMapperContainer
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.domain.models.MediaDetailsReviews
import com.karrar.movieapp.domain.models.Season
import com.karrar.movieapp.domain.models.TvShowDetails
import com.karrar.movieapp.domain.usecases.GetReviewsUseCase
import com.karrar.movieapp.utilities.Constants.MAX_NUM_REVIEWS
import javax.inject.Inject

class GetTvShowDetailsUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val seriesMapperContainer: SeriesMapperContainer,
    private val getTVShowsReviews: GetReviewsUseCase
) {

    suspend fun getTvShowDetails(tvShowId: Int): TvShowDetails {
        return seriesRepository.getTvShowDetails(tvShowId)?.let {
            seriesMapperContainer.tvShowDetailsMapper.map(it)
        } ?: TvShowDetails()
    }

    suspend fun getSeriesCast(tvShowId: Int): List<Actor> {
        return ListMapper(seriesMapperContainer.actorMapper)
            .mapList(seriesRepository.getTvShowCast(tvShowId)?.cast)
    }

    suspend fun getSeasons(tvShowId: Int): List<Season> {
        return ListMapper(seriesMapperContainer.seasonMapper)
            .mapList(seriesRepository.getTvShowDetails(tvShowId)?.season)
    }


    suspend fun getTvShowReviews(tvShowId: Int): MediaDetailsReviews {
        val reviews = getTVShowsReviews(MediaType.TV_SHOW, tvShowId)
        return MediaDetailsReviews(reviews.take(MAX_NUM_REVIEWS), reviews.size > MAX_NUM_REVIEWS)
    }


    suspend fun getTvShowRated(tvShowID: Int): Float {
        val result = seriesRepository.getRatedTvShow()
        return result?.let {
            it.find { it.id == tvShowID }?.rating ?: 0F
        } ?: throw Throwable("Error")
    }
}
