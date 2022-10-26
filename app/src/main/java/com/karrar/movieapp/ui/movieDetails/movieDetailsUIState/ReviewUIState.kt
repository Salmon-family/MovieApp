package com.karrar.movieapp.ui.movieDetails.movieDetailsUIState

data class ReviewUIState(
    val content: String = "",
    val createDate: String = "",
    val user: UserUIState = UserUIState()
)

data class UserUIState(
    val userImage: String = "",
    val name: String = "",
    val userName: String = "",
    val rating: Float = 0f
)