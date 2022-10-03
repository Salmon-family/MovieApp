package com.karrar.movieapp.ui.home

import com.karrar.movieapp.domain.enums.Type
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.PopularMovie

sealed class HomeRecyclerItem(var priority: Int) {
    class SlideType(val items: List<PopularMovie>) : HomeRecyclerItem(0)
    class TvShows(val items: List<Media> ) :HomeRecyclerItem(1)
    class MoviesType(val items: List<Media>,val type: Type) : HomeRecyclerItem(2)
    class ActorType(val items: List<Actor>) : HomeRecyclerItem(3)
}