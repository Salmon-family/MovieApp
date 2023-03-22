package com.karrar.movieapp.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.karrar.movieapp.R
import com.karrar.movieapp.ui.models.MediaUiState
import com.karrar.movieapp.ui.theme.Typography

@Composable
fun TvShowsItem(
    modifier: Modifier = Modifier,
    series: List<MediaUiState>,
    onClickSeriesCategory: (Int) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = stringResource(id = R.string.tv_shows),
            style = Typography.subtitle1,
            color = MaterialTheme.colors.primaryVariant,
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(192.dp)
        ) {

            SeriesCategory(
                modifier = Modifier.weight(1f),
                media = series.first(),
                onClick = onClickSeriesCategory,
                title = stringResource(id = R.string.top_rated)
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(.7f)
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                SeriesCategory(
                    modifier = Modifier.weight(1f),
                    media = series[1],
                    onClick = onClickSeriesCategory,
                    title = stringResource(id = R.string.latest)
                )

                SeriesCategory(
                    modifier = Modifier.weight(1f),
                    media = series[2],
                    onClick = onClickSeriesCategory,
                    title = stringResource(id = R.string.popular)
                )
            }
        }
    }
}