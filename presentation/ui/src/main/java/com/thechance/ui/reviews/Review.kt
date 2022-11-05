package com.thechance.ui.reviews

data class Review(
    val content: String,
    val createDate: String,
    val userImage: String,
    val name: String,
    val userName: String,
    val rating: Float
)
