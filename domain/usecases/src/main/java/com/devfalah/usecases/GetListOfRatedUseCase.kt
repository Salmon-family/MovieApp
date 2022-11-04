package com.devfalah.usecases

import com.devfalah.usecases.home.mappers.ListMapper
import com.devfalah.usecases.home.mappers.movie.RatedMoviesMapper
import com.devfalah.usecases.home.mappers.series.RatedTvShowMapper
import javax.inject.Inject

class GetListOfRatedUseCase @Inject constructor(
    private val movieRepository: com.thechance.repository.MovieRepository,
    private val tvShowRepository: com.thechance.repository.SeriesRepository,
    private val ratedMoviesMapper: RatedMoviesMapper,
    private val ratedTvShowMapper: RatedTvShowMapper
) {

    suspend operator fun invoke(): List<com.devfalah.models.Rated> {
        return getRatedMovie().plus(getRatedTvShow()).reversed()
    }

    private suspend fun getRatedMovie(): List<com.devfalah.models.Rated> {
        return ListMapper(ratedMoviesMapper).mapList(movieRepository.getRatedMovie())
    }

    private suspend fun getRatedTvShow(): List<com.devfalah.models.Rated> {
        return ListMapper(ratedTvShowMapper).mapList(tvShowRepository.getRatedTvShow())
    }
}