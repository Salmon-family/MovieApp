package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.movieDetailsDto.cast.CastDto
import com.karrar.movieapp.domain.models.Cast
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject


class CastMapper @Inject constructor() :Mapper<CastDto, Cast> {
    override fun map(input: CastDto): Cast {
        return Cast(
            Constants.IMAGE_BASE_PATH + input.profilePath,
            input.name
        )
    }
}