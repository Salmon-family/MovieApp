package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.ListItem
import com.karrar.movieapp.domain.models.SaveListDetails
import com.karrar.movieapp.utilities.Constants.IMAGE_BASE_PATH
import javax.inject.Inject

class SaveListDetailsMapper @Inject constructor() : Mapper<ListItem, SaveListDetails> {
    override fun map(input: ListItem): SaveListDetails {
        return SaveListDetails(
            id = input.id ?: 0,
            title = input.title ?: "",
            releaseDate = input.releaseDate ?: "",
            voteAverage = input.voteAverage ?: 0.0,
            posterPath = IMAGE_BASE_PATH + input.posterPath,
        )
    }
}