package com.karrar.movieapp.domain.usecases.home.mappers

import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.thechance.remote.response.genre.GenreDto
import com.karrar.movieapp.domain.models.Genre
import javax.inject.Inject

class GenreMapper @Inject constructor() : Mapper<GenreDto, Genre> {
    override fun map(input: com.thechance.remote.response.genre.GenreDto): Genre {
        return Genre(
            input.id ?: 0,
            input.name ?: "unknown"
        )
    }
}