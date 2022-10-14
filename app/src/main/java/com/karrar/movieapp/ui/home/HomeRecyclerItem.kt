package com.karrar.movieapp.ui.home

import com.karrar.movieapp.domain.enums.HomeItemsType
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.PopularMovie

sealed class HomeRecyclerItem(var priority: Int) {

    class Slider(val items: List<PopularMovie>) : HomeRecyclerItem(0)

    class TvShows(val items: List<Media>) : HomeRecyclerItem(1)

    class OnTheAiring(val items: List<Media>, val type: HomeItemsType) : HomeRecyclerItem(2)

    class Trending(val items: List<Media>, val type: HomeItemsType) : HomeRecyclerItem(3)

    class AiringToday(val items: List<Media>) : HomeRecyclerItem(4)

    class NowStreaming(val items: List<Media>, val type: HomeItemsType) : HomeRecyclerItem(5)

    class Upcoming(val items: List<Media>, val type: HomeItemsType) : HomeRecyclerItem(6)

    class Mystery(val items: List<Media>, val type: HomeItemsType) : HomeRecyclerItem(7)

    class Adventure(val items: List<Media>, val type: HomeItemsType) : HomeRecyclerItem(8)



    class Actor(val items: List<com.karrar.movieapp.domain.models.Actor>) : HomeRecyclerItem(9)

}