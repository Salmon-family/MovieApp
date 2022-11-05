package com.thechance.viewmodel.myList

import com.devfalah.models.CreatedList
import com.devfalah.usecases.mappers.Mapper
import com.thechance.viewmodel.myList.myListUIState.CreatedListUIState
import javax.inject.Inject

class CreatedListUIMapper @Inject constructor() :
    Mapper<CreatedList, CreatedListUIState> {

    override fun map(input: CreatedList): CreatedListUIState {
        return CreatedListUIState(
            listID = input.id,
            name = input.name,
            mediaCounts = input.itemCount
        )
    }
}


