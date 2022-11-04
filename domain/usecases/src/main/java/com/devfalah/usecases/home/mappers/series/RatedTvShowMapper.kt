package com.devfalah.usecases.home.mappers.series

import com.thechance.remote.response.RatedTvShowDto
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Rated
import com.devfalah.usecases.Constant
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class RatedTvShowMapper @Inject constructor() : Mapper<RatedTvShowDto, Rated> {
    override fun map(input: RatedTvShowDto): Rated {
        return Rated(
            id = input.id ?: 0,
            title = input.title ?: "",
            posterPath = BuildConfig.IMAGE_BASE_PATH + input.backdropPath,
            rating = input.rating ?: 0F,
            releaseDate = "",
            mediaType = Constant.TV_SHOWS
        )
    }
}