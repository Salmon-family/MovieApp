package com.karrar.movieapp.domain.usecases

import com.karrar.movieapp.domain.models.Rated
import com.karrar.movieapp.domain.usecases.home.mappers.ListMapper
import com.karrar.movieapp.domain.usecases.home.mappers.movie.RatedMoviesMapper
import com.karrar.movieapp.domain.usecases.home.mappers.series.RatedTvShowMapper
import com.karrar.movieapp.utilities.margeTowList
import javax.inject.Inject

class GetListOfRatedUseCase @Inject constructor(
    private val movieRepository: com.thechance.repository.MovieRepository,
    private val tvShowRepository: com.thechance.repository.SeriesRepository,
    private val ratedMoviesMapper: RatedMoviesMapper,
    private val ratedTvShowMapper: RatedTvShowMapper
) {

    suspend operator fun invoke(): List<Rated> {
        return getRatedMovie().margeTowList(getRatedTvShow()).reversed()
    }

    private suspend fun getRatedMovie(): List<Rated> {
        return ListMapper(ratedMoviesMapper).mapList(movieRepository.getRatedMovie())
    }

    private suspend fun getRatedTvShow(): List<Rated> {
        return ListMapper(ratedTvShowMapper).mapList(tvShowRepository.getRatedTvShow())
    }
}