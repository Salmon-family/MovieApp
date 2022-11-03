package com.karrar.movieapp.ui.home.homeUiState

import com.karrar.movieapp.ui.home.HomeItem

data class HomeUiState (
    val popularMovies: HomeItem = HomeItem.Slider(emptyList()),
    val trendingMovies: HomeItem = HomeItem.Trending(emptyList()),
    val nowStreamingMovies: HomeItem = HomeItem.NowStreaming(emptyList()),
    val adventureMovies: HomeItem = HomeItem.Adventure(emptyList()),
    val mysteryMovies: HomeItem = HomeItem.Mystery(emptyList()),
    val upcomingMovies: HomeItem = HomeItem.Upcoming(emptyList()),
    val onTheAiringSeries: HomeItem = HomeItem.OnTheAiring(emptyList()),
    val airingTodaySeries: HomeItem = HomeItem.AiringToday(emptyList()),
    val tvShowsSeries: HomeItem = HomeItem.TvShows(emptyList()),
    val actors: HomeItem = HomeItem.Actor(emptyList()),
    val isLoading:Boolean = false,
    val error : List<String> = emptyList(),
)