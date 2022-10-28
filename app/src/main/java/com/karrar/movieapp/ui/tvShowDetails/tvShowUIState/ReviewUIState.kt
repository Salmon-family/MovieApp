package com.karrar.movieapp.ui.tvShowDetails.tvShowUIState

data class ReviewUIState(
    val content: String = "",
    val createDate: String = "",
    val user: UserUIState = UserUIState()
)
