package com.thechance.viewmodel.movieDetails.saveMovie

import com.devfalah.models.CreatedList
import com.devfalah.usecases.mappers.Mapper
import com.thechance.viewmodel.movieDetails.saveMovie.uiState.MyListItemUI
import javax.inject.Inject

class MyListItemUIStateMapper @Inject constructor() :
    Mapper<CreatedList, MyListItemUI> {
    override fun map(input: CreatedList): MyListItemUI {
        return MyListItemUI(
            listID = input.id,
            name = input.name
        )
    }
}