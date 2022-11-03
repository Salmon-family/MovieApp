package com.karrar.movieapp.domain.usecases

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.movie.RatedMoviesMapper
import com.karrar.movieapp.domain.mappers.series.RatedTvShowMapper
import com.karrar.movieapp.domain.models.Rated
import com.karrar.movieapp.utilities.margeTowList
import javax.inject.Inject

class GetListOfRatedUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvShowRepository: SeriesRepository,
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