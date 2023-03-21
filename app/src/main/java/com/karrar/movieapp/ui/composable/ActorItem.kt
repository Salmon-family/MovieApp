package com.karrar.movieapp.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
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
import com.karrar.movieapp.ui.models.ActorUiState
import com.karrar.movieapp.ui.theme.LightSecondaryWhiteColor
import com.karrar.movieapp.ui.theme.LightTernaryWhiteColor
import com.karrar.movieapp.ui.theme.Typography

@Composable
fun ActorItem(
    modifier: Modifier = Modifier,
    showActorName: Boolean = true,
    actor: ActorUiState,
    size: Int = 72,
    onClick: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .wrapContentHeight()
            .nonRippleEffect(onClick = { onClick(actor.id) })
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = actor.imageUrl),
            contentDescription = actor.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(size.dp)
                .clip(CircleShape)
        )

        if (showActorName) {
            Text(
                text = actor.name,
                style = Typography.body2,
                color = LightSecondaryWhiteColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.paddingFromBaseline(top = 4.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewActorItem() {
    ActorItem(
        modifier = Modifier.padding(8.dp),
        actor = ActorUiState(),
        onClick = { /* handle click event */ }
    )
}