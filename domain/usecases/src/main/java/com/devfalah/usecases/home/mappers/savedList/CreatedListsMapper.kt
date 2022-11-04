package com.devfalah.usecases.home.mappers.savedList

import com.thechance.remote.response.CreatedListDto
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.CreatedList
import javax.inject.Inject

class CreatedListsMapper @Inject constructor() :
    Mapper<CreatedListDto, CreatedList> {
    override fun map(input: CreatedListDto): CreatedList {
        return CreatedList(
            id = input.id ?: 0,
            name = input.name ?: "unknown",
            itemCount = input.itemCount ?: 0,
        )
    }
}