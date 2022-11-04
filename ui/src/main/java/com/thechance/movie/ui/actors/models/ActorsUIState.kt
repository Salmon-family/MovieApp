package com.thechance.movie.ui.actors.models

import androidx.paging.PagingData
import com.thechance.movie.ui.models.ActorUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlin.Error

data class ActorsUIState(
    val actors: Flow<PagingData<ActorUiState>> = emptyFlow(),
    val isLoading: Boolean = false,
    val error: List<Error> = emptyList(),
)

