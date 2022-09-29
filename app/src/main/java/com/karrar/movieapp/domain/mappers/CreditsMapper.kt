package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.movieDetailsDto.CreditsDto
import com.karrar.movieapp.domain.models.Credits

class CreditsMapper:Mapper<CreditsDto, Credits> {
    override fun map(input: CreditsDto): Credits {
        return Credits(
            cast = input.cast
        )
    }
}