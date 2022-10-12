package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.ListItems
import com.karrar.movieapp.domain.models.SaveListDetails
import com.karrar.movieapp.utilities.Constants.IMAGE_BASE_PATH
import javax.inject.Inject

class SaveListDetailsMapper @Inject constructor() : Mapper<ListItems, SaveListDetails> {
    override fun map(input: ListItems): SaveListDetails {
        return SaveListDetails(
            id = input.id?:0,
            mediaType = input.mediaType?:"",
            title = listOf(input.originalTitle,input.originalName).filter { it!=null }.first().toString(),
            releaseDate = listOf(input.firstAirDate,input.releaseDate).filter { it!=null }.first().toString(),
            voteAverage = input.voteAverage?:0.0,
            posterPath = IMAGE_BASE_PATH + input.posterPath,
        )
    }
}