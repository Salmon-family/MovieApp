package com.karrar.movieapp.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ActorItem(
    modifier: Modifier = Modifier,
    actorImageUrl: String,
    actorName: String = "",
    size: Int = 72,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.nonRippleEffect(onClick = onClick)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = actorImageUrl),
            contentDescription = actorName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(size.dp)
                .clip(CircleShape)
        )

        if (actorName.isNotEmpty()) {
            Text(
                text = actorName,
//            style = MaterialTheme.typography.subtitle2,
                textAlign = TextAlign.Center,
                modifier = Modifier.paddingFromBaseline(top = 8.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewActorItem() {
    ActorItem(
        modifier = Modifier.padding(8.dp),
        actorImageUrl = "https://image.tmdb.org/t/p/w500/8V1XPEDLtJoxOWlE6gYzn2sQaMp.jpg",
        onClick = { /* handle click event */ }
    )
}