package com.karrar.movieapp.ui.profile.myratings

import com.karrar.movieapp.domain.models.Rated

data class MyRateUIState(
    val isLoading: Boolean = false,
    val ratedList: List<RatedUIState> = emptyList(),
    val error: List<Error> = emptyList()
)

fun Rated.ratedToUiSate(): RatedUIState {
    return RatedUIState(
        id = this.id,
        title = this.title,
        posterPath = this.posterPath,
        rating = this.rating,
        mediaType = this.mediaType,
        releaseDate = this.releaseDate
    )
}