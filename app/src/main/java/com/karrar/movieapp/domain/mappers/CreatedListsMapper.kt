package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.CreatedListDto
import com.karrar.movieapp.domain.models.CreatedList
import javax.inject.Inject

class CreatedListsMapper @Inject constructor() : Mapper<CreatedListDto, CreatedList> {
    override fun map(input: CreatedListDto): CreatedList {
        return CreatedList(
            id = input.id ?: 0,
            name = input.name ?: "unknown",
            itemCount = input.itemCount ?: 0,

            )
    }
}