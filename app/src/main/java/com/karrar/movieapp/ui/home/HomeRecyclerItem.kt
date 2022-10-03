package com.karrar.movieapp.ui.home

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.PopularMovie

sealed class HomeRecyclerItem(var priority: Int, val layoutID: Int) {

    class Slider(val items: List<PopularMovie>) : HomeRecyclerItem(0, R.layout.list_popular)

    class TvShows(val items: List<Media>) : HomeRecyclerItem(1, R.layout.list_tv_shows)

    class Movie(val items: List<Media>, val type: MovieType) : HomeRecyclerItem(2, R.layout.list_movie)

    class AiringToday(val items: List<Media>) : HomeRecyclerItem(4, R.layout.list_airing_today)

    class Actor(val items: List<com.karrar.movieapp.domain.models.Actor>) : HomeRecyclerItem(9, R.layout.list_actor)

}