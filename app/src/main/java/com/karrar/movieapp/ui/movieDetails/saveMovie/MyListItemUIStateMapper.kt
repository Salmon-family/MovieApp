package com.karrar.movieapp.ui.movieDetails.saveMovie

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.CreatedList
import javax.inject.Inject

class MyListItemUIStateMapper @Inject constructor() : Mapper<CreatedList, MyListItemUI> {
    override fun map(input: CreatedList): MyListItemUI {
        return MyListItemUI(
            listID = input.id,
            name = input.name
        )
    }
}