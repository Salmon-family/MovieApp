package com.karrar.movieapp.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.karrar.movieapp.ui.category.uiState.MediaUIState

@Composable
fun MediaItem(
    modifier: Modifier = Modifier,
    media: MediaUIState,
    isSquareItem: Boolean = false,
    hasTitle: Boolean = false,
    title: String = "",
    imageHeight: Int = 176,
    onClick: (Int) -> Unit
) {
    Box(
        modifier = modifier
            .width(if (isSquareItem) { imageHeight.dp } else { (imageHeight + 50).dp })
            .height(imageHeight.dp)
            .wrapContentSize(align = Alignment.BottomStart)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = media.mediaImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))
                .nonRippleEffect(onClick = { onClick(media.mediaID) })
        )
        if (hasTitle) {
            Text(
                text = title,
                style = MaterialTheme.typography.body2,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}