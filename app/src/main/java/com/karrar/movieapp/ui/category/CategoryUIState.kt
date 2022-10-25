package com.karrar.movieapp.ui.category

import androidx.paging.PagingData
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CategoryUIState(
    val genre: List<GenreUIState> = emptyList(),
    val media: Flow<PagingData<MediaUIState>> = emptyFlow(),
    val isLoading: Boolean = false,
    val error: String = ""
)

data class GenreUIState(
    val genreID: Int = Constants.FIRST_CATEGORY_ID,
    val genreName: String = ""
)

data class MediaUIState(
    val mediaID: Int,
    val mediaImage: String,
    val mediaType: String
)


fun Genre.toUiState(): GenreUIState {
    return GenreUIState(
        genreID = genreID,
        genreName = genreName
    )
}

fun Media.toUiState(): MediaUIState {
    return MediaUIState(
        mediaID = mediaID,
        mediaImage = mediaImage,
        mediaType = mediaType
    )
}