package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.genre.GenreDto
import com.karrar.movieapp.domain.models.Genre
import javax.inject.Inject

class GenreMapper @Inject constructor() : Mapper<GenreDto, Genre> {
    override fun map(input: GenreDto): Genre {
        return Genre(
            input.id ?: 0,
            input.name ?: "unknown"
        )
    }
}