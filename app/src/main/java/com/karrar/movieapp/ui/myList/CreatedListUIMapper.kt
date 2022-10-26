package com.karrar.movieapp.ui.myList

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.CreatedList
import javax.inject.Inject

class CreatedListUIMapper @Inject constructor() : Mapper<CreatedList, CreatedListUI> {

    override fun map(input: CreatedList): CreatedListUI {
        return CreatedListUI(
            listID = input.id,
            name = input.name,
            mediaCounts = input.itemCount
        )
    }
}


