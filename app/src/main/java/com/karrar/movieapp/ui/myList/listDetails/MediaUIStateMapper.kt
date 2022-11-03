package com.karrar.movieapp.ui.myList.listDetails

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.SaveListDetails
import com.karrar.movieapp.ui.myList.listDetails.listDetailsUIState.SavedMediaUIState
import javax.inject.Inject

class MediaUIStateMapper @Inject constructor() : Mapper<SaveListDetails, SavedMediaUIState> {

    override fun map(input: SaveListDetails): SavedMediaUIState {
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