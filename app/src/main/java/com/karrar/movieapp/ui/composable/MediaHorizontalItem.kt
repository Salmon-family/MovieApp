package com.karrar.movieapp.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karrar.movieapp.R
import com.karrar.movieapp.ui.search.mediaSearchUIState.MediaUIState
import com.karrar.movieapp.ui.ui.theme.HorizontalMediaCardBackground
import com.karrar.movieapp.ui.ui.theme.Yellow

@Composable
fun MediaHorizontalItem(
    mediaUIState: MediaUIState,
    modifier: Modifier = Modifier,
) {
    //fix text font, size and color
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(HorizontalMediaCardBackground)
    ) {
        Image(
            painter = painterResource(id = R.drawable.chris_evans),
            contentDescription = null,
            modifier = Modifier
                .width(142.dp)
                .fillMaxHeight(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = mediaUIState.mediaName,
                color = Color.White
            )

            Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_star_small),
                    contentDescription = null,
                    tint =  Yellow
                )

                Text(
                    text = mediaUIState.mediaVoteAverage.toString(),
                    color = Yellow,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )

                Text(
                    text = mediaUIState.mediaReleaseDate,
                    color = Color.White,
                )
            }
        }

    }


}


@Preview
@Composable
private fun Preview() {
    MediaHorizontalItem(
        mediaUIState = MediaUIState(
            23,
            "Avengers",
            "imageUrl",
            "imageUrl",
            9.8f,
            "2012"
        )
    )
}