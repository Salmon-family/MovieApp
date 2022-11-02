package com.karrar.movieapp.domain.usecase.home.getData.movie

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.movie.TrendingMovieMapper
import com.karrar.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTrendingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: TrendingMovieMapper,
) {

    operator fun invoke(): Flow<List<Media>> {
        return movieRepository.getTrendingMovies().map {
            it.map(movieMapper::map)
        }
    }
}