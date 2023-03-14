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

@Composable
fun MediaItem(
    modifier: Modifier = Modifier,
    mediaImageUrl: String,
    imageHeight: Int = 176,
    onClick: () -> Unit
) {
    Image(
        painter = rememberAsyncImagePainter(model = mediaImageUrl),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .wrapContentSize()
            .height(imageHeight.dp)
            .width((imageHeight + 50).dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .nonRippleEffect(onClick = onClick)

    )
}