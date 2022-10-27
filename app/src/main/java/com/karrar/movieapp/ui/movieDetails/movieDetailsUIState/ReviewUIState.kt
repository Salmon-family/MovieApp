package com.karrar.movieapp.ui.movieDetails.movieDetailsUIState

data class ReviewUIState(
    val content: String = "",
    val createDate: String = "",
    val user: UserUIState = UserUIState()
)