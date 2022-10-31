package com.example.usecase.mappers.movie

import com.example.models.models.Rated
import com.example.usecase.mappers.Mapper
import com.example.usecase.utilites.Constants
import com.thechance.repository.BuildConfig
import com.thechance.repository.remote.response.RatedMoviesDto
import javax.inject.Inject

class RatedMoviesMapper @Inject constructor() : Mapper<RatedMoviesDto, Rated> {
    override fun map(input: RatedMoviesDto): Rated {
        return Rated(
            id = input.id ?: 0,
            title = input.title ?: "",
            posterPath = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            rating = input.rating ?: 0F,
            releaseDate = input.releaseDate ?: "",
            mediaType = Constants.MOVIE
        )
    }
}
