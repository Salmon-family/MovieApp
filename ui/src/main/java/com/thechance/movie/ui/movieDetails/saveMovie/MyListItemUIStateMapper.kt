package com.thechance.movie.ui.movieDetails.saveMovie

import com.thechance.movie.ui.movieDetails.saveMovie.uiState.MyListItemUI
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