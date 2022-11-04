package com.karrar.movieapp.domain.usecases.home.getData.movie

import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.usecases.home.mappers.movie.MysteryMovieMapper
import com.thechance.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMysteryMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MysteryMovieMapper,
) {

    suspend operator fun invoke(): Flow<List<Media>> {
        return movieRepository.getMysteryMovies().map {
            it.map(movieMapper::map)
        }
    }
}