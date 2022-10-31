package com.karrar.movieapp.domain.mappers.savedList

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.SaveListDetails
import com.thechance.repository.remote.response.SavedListDto
import javax.inject.Inject

class SaveListDetailsMapper @Inject constructor() : Mapper<SavedListDto, SaveListDetails> {
    override fun map(input: SavedListDto): SaveListDetails {
        return SaveListDetails(
            id = input.id ?: 0,
            mediaType = input.mediaType ?: "",
            title = listOf(input.originalTitle, input.originalName).filter { it != null }.first()
                .toString(),
            releaseDate = listOf(input.firstAirDate, input.releaseDate).filter { it != null }
                .first().toString(),
            voteAverage = input.voteAverage ?: 0.0,
            posterPath = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
        )
    }
}