package com.karrar.movieapp.domain.home

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.movie.MysteryMovieMapper
import com.karrar.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMysteryMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MysteryMovieMapper,
) {

    operator fun invoke(): Flow<List<Media>> {
        return movieRepository.getMysteryMovies().map {
            it.map(movieMapper::map)
        }
    }
}