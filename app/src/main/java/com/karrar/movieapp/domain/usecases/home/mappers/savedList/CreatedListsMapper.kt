package com.karrar.movieapp.domain.usecases.home.mappers.savedList

import com.thechance.remote.response.CreatedListDto
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.CreatedList
import javax.inject.Inject

class CreatedListsMapper @Inject constructor() : Mapper<CreatedListDto, CreatedList> {
    override fun map(input: com.thechance.remote.response.CreatedListDto): CreatedList {
        return CreatedList(
            id = input.id ?: 0,
            name = input.name ?: "unknown",
            itemCount = input.itemCount ?: 0,)
    }
}