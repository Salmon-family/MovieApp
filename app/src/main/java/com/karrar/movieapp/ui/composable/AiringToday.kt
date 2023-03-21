package com.karrar.movieapp.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.karrar.movieapp.R
import com.karrar.movieapp.ui.category.uiState.MediaUIState
import com.karrar.movieapp.ui.theme.Typography

@Composable
fun AiringToday(
    modifier: Modifier = Modifier,
    medias: List<MediaUIState>,
    onClickMedia: (Int) -> Unit,
    cardTitleRes: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colors.surface)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = cardTitleRes),
                style = Typography.subtitle1,
                color = MaterialTheme.colors.primaryVariant,
            )
            Text(
                text = stringResource(id = R.string.text_movies, medias.size),
                style = Typography.body2,
                color = MaterialTheme.colors.primaryVariant,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = medias.take(6)) { media ->
                MediaItem(
                    media = media,
                    onClick = onClickMedia,
                    isSquareItem = true,
                    imageHeight = 88
                )
            }
        }
    }
}
