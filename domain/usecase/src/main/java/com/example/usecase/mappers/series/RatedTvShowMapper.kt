package com.example.usecase.mappers.series

import com.example.models.models.Rated
import com.example.usecase.utilites.Constants
import com.example.usecase.mappers.Mapper
import com.thechance.repository.BuildConfig
import com.thechance.repository.remote.response.RatedTvShowDto
import javax.inject.Inject

class RatedTvShowMapper @Inject constructor() : Mapper<RatedTvShowDto, Rated> {
    override fun map(input: RatedTvShowDto): Rated {
        return Rated(
            id = input.id ?: 0,
            title = input.title ?: "",
            posterPath = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            rating = input.rating ?: 0F,
            releaseDate = "",
            mediaType = Constants.TV_SHOWS
        )
    }
}
