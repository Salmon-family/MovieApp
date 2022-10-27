package com.karrar.movieapp.ui.actors.models

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlin.Error

data class ActorsUIState(
    val actors: Flow<PagingData<ActorInfoUIState>> = emptyFlow(),
    val isLoading: Boolean = false,
    val error: List<Error> = emptyList(),
)

