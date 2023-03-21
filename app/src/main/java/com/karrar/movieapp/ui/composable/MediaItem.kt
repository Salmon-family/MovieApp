package com.karrar.movieapp.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.karrar.movieapp.ui.category.uiState.MediaUIState

@Composable
fun MediaItem(
    modifier: Modifier = Modifier,
    media: MediaUIState,
    isSquareItem: Boolean = false,
    imageHeight: Int = 176,
    onClick: (Int) -> Unit
) {
    Image(
        painter = rememberAsyncImagePainter(model = media.mediaImage),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .wrapContentSize()
            .height(imageHeight.dp)
            .width(
                if (isSquareItem) {
                    imageHeight.dp
                } else {
                    (imageHeight + 50).dp
                }
            )
            .clip(shape = RoundedCornerShape(16.dp))
            .nonRippleEffect(onClick = { onClick(media.mediaID) })

    )
}