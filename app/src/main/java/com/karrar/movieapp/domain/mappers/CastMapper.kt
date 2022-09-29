package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.movieDetailsDto.CastDto
import com.karrar.movieapp.domain.models.Cast
import com.karrar.movieapp.utilities.Constants.URL_EXTENSION

class CastMapper:Mapper<CastDto, Cast> {
    override fun map(input: CastDto): Cast {
        return Cast(
            actor_image = "${URL_EXTENSION}.${input.profilePath}",
            actor_name = input.name
        )
    }
}