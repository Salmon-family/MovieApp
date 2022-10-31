package com.karrar.movieapp.domain

import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.movie.RatedMoviesMapper
import com.karrar.movieapp.domain.mappers.series.RatedTvShowMapper
import com.karrar.movieapp.domain.models.Rated
import com.karrar.movieapp.utilities.margeTowList
import com.thechance.repository.MovieRepository
import com.thechance.repository.SeriesRepository
import javax.inject.Inject

class GetListOfRatedUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvShowRepository: SeriesRepository,
    private val ratedMoviesMapper: RatedMoviesMapper,
    private val ratedTvShowMapper: RatedTvShowMapper
) {

    suspend operator fun invoke(accountId: Int, sessionId: String): List<Rated> {
        return getRatedMovie(accountId, sessionId).margeTowList(
            getRatedTvShow(
                accountId,
                sessionId
            )
        ).reversed()
    }

    private suspend fun getRatedMovie(accountId: Int, sessionId: String): List<Rated> {
        return ListMapper(ratedMoviesMapper).mapList(
            movieRepository.getRatedMovie(
                accountId,
                sessionId
            )
        )
    }

    private suspend fun getRatedTvShow(accountId: Int, sessionId: String): List<Rated> {
        return ListMapper(ratedTvShowMapper).mapList(
            tvShowRepository.getRatedTvShow(
                accountId,
                sessionId
            )
        )
    }
}