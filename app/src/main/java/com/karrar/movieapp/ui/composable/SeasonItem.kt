package com.karrar.movieapp.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
    seasonUi: SeasonUi,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(144.dp)
            .padding(horizontal = 16.dp)
            .clickable { onClick() }
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
                painter = rememberAsyncImagePainter(model = seasonUi.seasonImageUrl),
                contentDescription = seasonUi.seasonImageUrl,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp))
                    .background(DarkCardBackground)
                    .padding(vertical = 8.dp)
            ) {
                CardBody(
                    seasonUi = SeasonUi(
                        name = seasonUi.name,
                        year = seasonUi.year,
                        description = seasonUi.description,
                        episodeCount = seasonUi.episodeCount,
                    )
                )
            }
        }

    }
}

@Composable
fun CardBody(
    seasonUi: SeasonUi,
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = seasonUi.name,
                style = Typography.subtitle1,
                color = LightPrimaryWhiteColor
            )
            SpacingTooSmallHorizontal()
            Text(
                text = seasonUi.year,
                style = Typography.body2,
                color = LightTernaryWhiteColor
            )
            SpacingTooSmallHorizontal()
            Text(text = "|", style = Typography.body2, color = LightTernaryWhiteColor)
            SpacingTooSmallHorizontal()
            Text(
                text = seasonUi.episodeCount,
                style = Typography.body2,
                color = LightTernaryWhiteColor
            )
        }
        SpacingTooSmallVertical()
        Text(
            text = seasonUi.description,
            style = Typography.body2, color = LightPrimaryWhiteColor,
            maxLines = 4
        )
    }
}


data class SeasonUi(
    val name: String = "",
    val year: String = "",
    val description: String = "",
    val episodeCount: String = "",
    val seasonImageUrl: String = "",
)