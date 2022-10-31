package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.domain.models.Genre
import com.thechance.repository.remote.response.genre.GenreDto
import javax.inject.Inject

class GenreMapper @Inject constructor() : Mapper<GenreDto, Genre> {
    override fun map(input: GenreDto): Genre {
        return Genre(
            input.id ?: 0,
            input.name ?: "unknown"
        )
    }
}