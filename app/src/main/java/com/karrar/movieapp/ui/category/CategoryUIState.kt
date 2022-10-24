package com.karrar.movieapp.ui.category

import androidx.paging.PagingData
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CategoryUIState(
    val genre: List<Genre> = emptyList(),
    val media: Flow<PagingData<Media>> = emptyFlow(),
    val mediaUIState: UIState<Boolean> = UIState.Loading,
)