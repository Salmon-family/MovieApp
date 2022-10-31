package com.example.usecase.mappers.savedList

import com.example.models.models.CreatedList
import com.example.usecase.mappers.Mapper
import com.thechance.repository.remote.response.CreatedListDto
import javax.inject.Inject

class CreatedListsMapper @Inject constructor() : Mapper<CreatedListDto, CreatedList> {
    override fun map(input: CreatedListDto): CreatedList {
        return CreatedList(
            id = input.id ?: 0,
            name = input.name ?: "unknown",
            itemCount = input.itemCount ?: 0
        )
    }
}
