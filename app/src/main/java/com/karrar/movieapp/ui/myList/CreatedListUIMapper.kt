package com.karrar.movieapp.ui.myList

import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.CreatedList
import com.karrar.movieapp.ui.myList.myListUIState.CreatedListUIState
import javax.inject.Inject

class CreatedListUIMapper @Inject constructor() : Mapper<CreatedList, CreatedListUIState> {

    override fun map(input: CreatedList): CreatedListUIState {
        return CreatedListUIState(
            listID = input.id,
            name = input.name,
            mediaCounts = input.itemCount
        )
    }
}


