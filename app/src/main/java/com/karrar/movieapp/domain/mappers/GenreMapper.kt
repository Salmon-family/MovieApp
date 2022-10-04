package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.GenreDto
import com.karrar.movieapp.domain.models.Genre
import javax.inject.Inject

class GenreMapper  @Inject constructor() : Mapper<GenreDto, Genre> {
    override fun map(input: GenreDto): Genre {
        return Genre(input.id, input.name)
    }
}