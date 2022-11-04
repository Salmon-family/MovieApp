package com.devfalah.usecases.home.getData.movie

import com.devfalah.models.Media
import com.devfalah.usecases.home.mappers.movie.AdventureMovieMapper
import com.thechance.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAdventureMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: AdventureMovieMapper,
) {

    suspend operator fun invoke(): Flow<List<Media>> {
        return movieRepository.getAdventureMovies().map {
            it.map(movieMapper::map)
        }
    }
}