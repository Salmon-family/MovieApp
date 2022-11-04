package com.devfalah.usecases.home.getData.movie

import com.devfalah.models.Media
import com.devfalah.usecases.home.mappers.movie.UpcomingMovieMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val movieRepository: com.thechance.repository.MovieRepository,
    private val movieMapper: UpcomingMovieMapper,
) {

    suspend  operator fun invoke(): Flow<List<Media>> {
        return movieRepository.getUpcomingMovies().map {
            it.map(movieMapper::map)
        }
    }
}