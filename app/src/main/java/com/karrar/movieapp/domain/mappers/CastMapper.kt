package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.movieDetailsDto.CastDto
import com.karrar.movieapp.domain.models.Cast


class CastMapper:Mapper<CastDto, Cast> {
    override fun map(input: CastDto): Cast {
        return Cast(
            input.profilePath,
            input.name
        )
    }
}