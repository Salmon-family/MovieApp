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

@Composable
fun Actors(
    modifier: Modifier = Modifier,
    numberOfRows: Int = 2,
    actorsState: List<Actor>,
    showActorName: Boolean = false,
    onActorClick: (Int) -> Unit
) {
    LazyHorizontalGrid(
        modifier = modifier.heightIn(max = 200.dp),
        rows = GridCells.Fixed(numberOfRows),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(actorsState) { actor ->
            ActorItem(
                actorImageUrl = actor.actorImage,
                actorName = if (showActorName) actor.actorName else "",
                onClick = { onActorClick(actor.actorID) })
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun previewActors() {
    Actors(
        actorsState = listOf(
            Actor(1, "https://image.tmdb.org/t/p/w500/8V1XPEDLtJoxOWlE6gYzn2sQaMp.jpg", "test"),
            Actor(2, "https://image.tmdb.org/t/p/w500/8V1XPEDLtJoxOWlE6gYzn2sQaMp.jpg", "test"),
            Actor(3, "https://image.tmdb.org/t/p/w500/8V1XPEDLtJoxOWlE6gYzn2sQaMp.jpg", "test"),
            Actor(4, "https://image.tmdb.org/t/p/w500/8V1XPEDLtJoxOWlE6gYzn2sQaMp.jpg", "test"),
            Actor(4, "https://image.tmdb.org/t/p/w500/8V1XPEDLtJoxOWlE6gYzn2sQaMp.jpg", "test"),
            Actor(4, "https://image.tmdb.org/t/p/w500/8V1XPEDLtJoxOWlE6gYzn2sQaMp.jpg", "test"),
            Actor(4, "https://image.tmdb.org/t/p/w500/8V1XPEDLtJoxOWlE6gYzn2sQaMp.jpg", "test"),
            Actor(4, "https://image.tmdb.org/t/p/w500/8V1XPEDLtJoxOWlE6gYzn2sQaMp.jpg", "test"),
            Actor(4, "https://image.tmdb.org/t/p/w500/8V1XPEDLtJoxOWlE6gYzn2sQaMp.jpg", "test"),
        ),
        onActorClick = {}
    )
}