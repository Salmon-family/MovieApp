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

    suspend operator fun invoke(mediaType: String, mediaID: Int): Trailer {
        val result = when (mediaType) {
            MediaType.MOVIE.name -> {
                movieRepository.getMovieTrailer(mediaID)
            }
            else -> {
                seriesRepository.getTvShowTrailer(mediaID)
            }
        }
        return result?.let { trailerMapper.map(it) } ?: throw Throwable("Error")
    }
}