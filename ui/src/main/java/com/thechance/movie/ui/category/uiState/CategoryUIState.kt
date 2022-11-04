package com.thechance.movie.ui.category.uiState

import androidx.paging.PagingData
import com.thechance.movie.utilities.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CategoryUIState(
    val genre: List<GenreUIState> = emptyList(),
    val selectedCategoryID :Int = Constants.FIRST_CATEGORY_ID,
    val media: Flow<PagingData<MediaUIState>> = emptyFlow(),
    val isLoading: Boolean = false,
    val error: List<ErrorUIState> = emptyList()
)



