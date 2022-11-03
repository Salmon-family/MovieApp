package com.karrar.movieapp.domain.usecase.tvShowDetails

import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.SeriesMapperContainer
import com.karrar.movieapp.domain.models.*
import javax.inject.Inject

class GetTvShowDetailsUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val seriesMapperContainer: SeriesMapperContainer,
    private val getSessionIdUseCase: GetSessionIdUseCase
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


    suspend fun getTvShowReviews(tvShowId: Int): List<Review> {
        return ListMapper(seriesMapperContainer.reviewMapper)
            .mapList(seriesRepository.getTvShowReviews(tvShowId))
    }

    suspend fun getTvShowRated(accountId: Int): List<Rated> {
        return ListMapper(seriesMapperContainer.ratedTvShowMapper).mapList(
            seriesRepository.getRatedTvShow(
                accountId,
                getSessionIdUseCase() ?: ""
            )
        )
    }
}
