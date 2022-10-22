package com.karrar.movieapp.ui.home

import com.karrar.movieapp.domain.enums.HomeItemsType
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.PopularMovie

sealed class HomeRecyclerItem(val priority: Int) {

    data class Slider(val items: List<PopularMovie>) : HomeRecyclerItem(0)

    data class TvShows(val items: List<Media>) : HomeRecyclerItem(1)

    data class OnTheAiring(val items: List<Media>, val type: HomeItemsType) : HomeRecyclerItem(2)

    data class Trending(val items: List<Media>, val type: HomeItemsType) : HomeRecyclerItem(3)

    data class AiringToday(val items: List<Media>) : HomeRecyclerItem(4)

    data class NowStreaming(val items: List<Media>, val type: HomeItemsType) : HomeRecyclerItem(5)

    data class Upcoming(val items: List<Media>, val type: HomeItemsType) : HomeRecyclerItem(6)

    data class Mystery(val items: List<Media>, val type: HomeItemsType) : HomeRecyclerItem(7)

    data class Adventure(val items: List<Media>, val type: HomeItemsType) : HomeRecyclerItem(8)

    data class Actor(val items: List<com.karrar.movieapp.domain.models.Actor>) : HomeRecyclerItem(9)

}