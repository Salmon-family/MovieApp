package com.devfalah.usecases.home.mappers.savedList

import com.thechance.remote.response.SavedListDto
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.SaveListDetails
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class SaveListDetailsMapper @Inject constructor() :
    Mapper<SavedListDto, SaveListDetails> {
    override fun map(input: SavedListDto): SaveListDetails {
        return SaveListDetails(
            id = input.id ?: 0,
            mediaType = input.mediaType ?: "",
            title = listOf(input.originalTitle, input.originalName).filter { it != null }.first()
                .toString(),
            releaseDate = listOf(input.firstAirDate, input.releaseDate).filter { it != null }
                .first().toString(),
            voteAverage = input.voteAverage ?: 0.0,
            posterPath = BuildConfig.IMAGE_BASE_PATH + input.backdropPath,
        )
    }
}