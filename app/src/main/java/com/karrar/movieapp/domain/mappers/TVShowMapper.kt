package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.TVShowsDTO
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class TVShowMapper @Inject constructor() : Mapper<TVShowsDTO, Media> {
    override fun map(input: TVShowsDTO): Media {
        return Media(
            input.id ?: 0,
            Constants.IMAGE_BASE_PATH + input.posterPath,
            Constants.MOVIE,
            input.originalName ?: "",
            input.firstAirDate?.substringBefore('-') ?: "",
            input.voteAverage?.toFloat() ?: 0F,
        )
    }
}