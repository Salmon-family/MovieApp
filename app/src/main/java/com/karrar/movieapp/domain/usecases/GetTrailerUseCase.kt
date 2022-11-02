package com.karrar.movieapp.domain.usecases

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.mappers.TrailerMapper
import com.karrar.movieapp.domain.models.Trailer
import javax.inject.Inject

class GetTrailerUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
    private val trailerMapper: TrailerMapper
) {

    suspend operator fun invoke(mediaType: MediaType, mediaID: Int): Trailer {
        val result = when (mediaType) {
            MediaType.MOVIE -> {
                movieRepository.getMovieTrailer(mediaID)
            }
            MediaType.TV_SHOW -> {
                seriesRepository.getTvShowTrailer(mediaID)
            }
        }
        return result?.let { trailerMapper.map(it) } ?: throw Throwable("Error")
    }
}