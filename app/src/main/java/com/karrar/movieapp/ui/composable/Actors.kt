package com.karrar.movieapp.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.models.ActorUiState

@Composable
fun Actors(
    modifier: Modifier = Modifier,
    numberOfRows: Int = 2,
    actors: List<ActorUiState>,
    showActorName: Boolean = true,
    onActorClick: (Int) -> Unit
) {
    LazyHorizontalGrid(
        modifier = modifier.heightIn(max = 200.dp),
        rows = GridCells.Fixed(numberOfRows),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(actors) { actor ->
            ActorItem(
                actor = actor,
                showActorName = showActorName,
                onClick = { onActorClick(actor.id) })
        }
    }
}

