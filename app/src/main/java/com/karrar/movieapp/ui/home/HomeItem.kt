package com.karrar.movieapp.ui.home

import com.karrar.movieapp.domain.enums.HomeItemsType
import com.karrar.movieapp.ui.models.ActorUiState
import com.karrar.movieapp.ui.home.homeUiState.PopularUiState
import com.karrar.movieapp.ui.models.MediaUiState

sealed class HomeItem(val priority: Int) {

    data class Slider(val items: List<PopularUiState>) : HomeItem(0)

    data class TvShows(val items: List<MediaUiState>) : HomeItem(1)

    data class OnTheAiring(val items: List<MediaUiState>, val type: HomeItemsType = HomeItemsType.ON_THE_AIR) : HomeItem(2)

    data class Trending(val items: List<MediaUiState>, val type: HomeItemsType = HomeItemsType.TRENDING) : HomeItem(3)

    data class AiringToday(val items: List<MediaUiState>) : HomeItem(4)

    data class NowStreaming(val items: List<MediaUiState>, val type: HomeItemsType = HomeItemsType.NOW_STREAMING) : HomeItem(5)

    data class Upcoming(val items: List<MediaUiState>, val type: HomeItemsType = HomeItemsType.UPCOMING) : HomeItem(6)

    data class Mystery(val items: List<MediaUiState>, val type: HomeItemsType = HomeItemsType.MYSTERY) : HomeItem(7)

    data class Adventure(val items: List<MediaUiState>, val type: HomeItemsType = HomeItemsType.ADVENTURE) : HomeItem(8)

    data class Actor(val items: List<ActorUiState>) : HomeItem(9)

}