package com.karrar.movieapp.ui.models

import com.karrar.movieapp.domain.models.Media

data class MediaUiState(
    val id : Int,
    val imageUrl : String
)

fun Media.toUiState() :MediaUiState{
    return MediaUiState(
        id = this.mediaID,
        imageUrl = this.mediaImage,
    )
}