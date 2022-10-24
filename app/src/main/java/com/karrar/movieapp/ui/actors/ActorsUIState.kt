package com.karrar.movieapp.ui.actors

import androidx.paging.PagingData
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ActorsUIState(
    val actorsState: UIState<Boolean> = UIState.Loading,
    val actors: Flow<PagingData<Actor>> = emptyFlow(),
)
