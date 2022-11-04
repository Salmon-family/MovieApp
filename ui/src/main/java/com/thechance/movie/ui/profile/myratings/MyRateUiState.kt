package com.thechance.movie.ui.profile.myratings

data class MyRateUIState(
    val isLoading: Boolean = false,
    val ratedList: List<RatedUIState> = emptyList(),
    val error: List<Error> = emptyList()
)