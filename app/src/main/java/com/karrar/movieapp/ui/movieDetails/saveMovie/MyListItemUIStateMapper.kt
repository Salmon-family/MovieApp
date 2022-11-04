package com.karrar.movieapp.ui.movieDetails.saveMovie

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.CreatedList
import com.karrar.movieapp.ui.movieDetails.saveMovie.uiState.MyListItemUI
import javax.inject.Inject

class MyListItemUIStateMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.CreatedList, MyListItemUI> {
    override fun map(input: com.devfalah.models.CreatedList): MyListItemUI {
        return MyListItemUI(
            listID = input.id,
            name = input.name
        )
    }
}