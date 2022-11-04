package com.thechance.repository.mapper.movie

import com.thechance.local.database.entity.movie.MysteryMovieEntity
import com.thechance.remote.response.MovieDto
import com.thechance.repository.mapper.Mapper
import javax.inject.Inject

class MysteryMovieMapper @Inject constructor() : Mapper<MovieDto, MysteryMovieEntity> {
    override fun map(input: MovieDto): MysteryMovieEntity {
        return MysteryMovieEntity(
            id = input.id ?: 0,
            name = input.originalTitle ?: "",
            imageUrl = input.posterPath ?: ""
        )
    }
}