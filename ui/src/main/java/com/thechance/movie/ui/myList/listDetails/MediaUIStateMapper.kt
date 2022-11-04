package com.thechance.movie.ui.myList.listDetails

import com.thechance.movie.ui.myList.listDetails.listDetailsUIState.SavedMediaUIState
import javax.inject.Inject

class MediaUIStateMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.SaveListDetails, SavedMediaUIState> {

    override fun map(input: com.devfalah.models.SaveListDetails): SavedMediaUIState {
        return SavedMediaUIState(
            image = input.posterPath,
            mediaID = input.id,
            title = input.title,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate,
            mediaType = input.mediaType
        )
    }
}