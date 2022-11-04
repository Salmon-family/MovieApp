package com.devfalah.usecases

import com.devfalah.types.MediaType
import com.devfalah.models.Trailer
import com.devfalah.usecases.home.mappers.TrailerMapper
import com.thechance.repository.MovieRepository
import com.thechance.repository.SeriesRepository
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