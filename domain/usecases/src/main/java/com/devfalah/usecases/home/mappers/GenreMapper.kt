package com.devfalah.usecases.home.mappers

import com.devfalah.usecases.mappers.Mapper
import com.thechance.remote.response.genre.GenreDto
import com.devfalah.models.Genre
import javax.inject.Inject

class GenreMapper @Inject constructor() : Mapper<GenreDto, Genre> {
    override fun map(input: GenreDto): Genre {
        return Genre(
            input.id ?: 0,
            input.name ?: "unknown"
        )
    }
}