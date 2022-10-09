package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.saveDetails.CreatedListsDto
import com.karrar.movieapp.domain.models.CreatedLists
import com.karrar.movieapp.utilities.Constants.IMAGE_BASE_PATH
import javax.inject.Inject

class CreatedListsMapper @Inject constructor() : Mapper<CreatedListsDto, CreatedLists> {
    override fun map(input: CreatedListsDto): CreatedLists {
        return CreatedLists(
            id = input.id,
            name = input.name,
            itemCount = input.itemCount ?: 0,
            posterPath = IMAGE_BASE_PATH+input.posterPath
        )
    }
}