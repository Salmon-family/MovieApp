package com.karrar.movieapp.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.karrar.movieapp.ui.ui.theme.*
import com.karrar.movieapp.ui.ui.theme.specing.SpacingTooSmallHorizontal
import com.karrar.movieapp.ui.ui.theme.specing.SpacingTooSmallVertical


@Composable
fun SeasonItem(
    seasonName: String,
    seasonYear: String,
    seasonDescription: String,
    episodeCount: String,
    seasonImageUrl: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(144.dp)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(144.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .width(90.dp)
                    .clip(RoundedCornerShape(16.dp)),
                painter = rememberAsyncImagePainter(model = seasonImageUrl),
                contentDescription = seasonImageUrl,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp))
                    .background(DarkCardBackground)
                    .padding(vertical = 8.dp)
            ) {
                CardBody(
                    seasonName = seasonName,
                    year = seasonYear,
                    episodeCount = episodeCount,
                    description = seasonDescription,
                )
            }
        }

    }
}

@Composable
fun CardBody(
    seasonName: String,
    year: String,
    episodeCount: String,
    description: String,
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = seasonName,
                style = Typography.subtitle1,
                color = LightPrimaryWhiteColor
            )
            SpacingTooSmallHorizontal()
            Text(
                text = year,
                style = Typography.body2,
                color = LightTernaryWhiteColor
            )
            SpacingTooSmallHorizontal()
            Text(text = "|", style = Typography.body2, color = LightTernaryWhiteColor)
            SpacingTooSmallHorizontal()
            Text(
                text = episodeCount,
                style = Typography.body2,
                color = LightTernaryWhiteColor
            )
        }
        SpacingTooSmallVertical()
        Text(
            text = description,
            style = Typography.body2, color = LightPrimaryWhiteColor,
            maxLines = 4
        )
    }
}
