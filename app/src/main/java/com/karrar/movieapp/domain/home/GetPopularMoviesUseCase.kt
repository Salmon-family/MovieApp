package com.karrar.movieapp.domain.home

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.movie.PopularMovieMapper
import com.karrar.movieapp.domain.models.PopularMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: PopularMovieMapper,
) {
    operator fun invoke(): Flow<List<PopularMovie>> {
        return movieRepository.getPopularMovies().map { items ->
            items.map(movieMapper::map)
        }
    }
}