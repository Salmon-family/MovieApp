package com.thechance.viewmodel.movieDetails.movieDetailsUIState

data class ReviewUIState(
    val content: String = "",
    val createDate: String = "",
    val userImage: String = "",
    val name: String = "",
    val userName: String = "",
    val rating: Float = 0f
)