package com.thechance.repository.mapper.movie

import com.thechance.local.database.entity.movie.AdventureMovieEntity
import com.thechance.remote.response.MovieDto
import com.thechance.repository.mapper.Mapper
import javax.inject.Inject

class AdventureMovieMapper @Inject constructor() : Mapper<MovieDto, AdventureMovieEntity> {
    override fun map(input: MovieDto): AdventureMovieEntity {
        return AdventureMovieEntity(
            id = input.id ?: 0,
            name = input.originalTitle ?: "",
            imageUrl = input.posterPath ?: ""
        )
    }
}