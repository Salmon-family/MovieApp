package com.karrar.movieapp.ui.home

data class HomeUiState (
    val popularMovies: HomeRecyclerItem = HomeRecyclerItem.Slider(emptyList()),
    val trendingMovies: HomeRecyclerItem = HomeRecyclerItem.Trending(emptyList()),
    val nowStreamingMovies: HomeRecyclerItem = HomeRecyclerItem.NowStreaming(emptyList()),
    val adventureMovies: HomeRecyclerItem = HomeRecyclerItem.Adventure(emptyList()),
    val mysteryMovies: HomeRecyclerItem = HomeRecyclerItem.Mystery(emptyList()),
    val upcomingMovies: HomeRecyclerItem = HomeRecyclerItem.Upcoming(emptyList()),
    val onTheAiringSeries: HomeRecyclerItem = HomeRecyclerItem.OnTheAiring(emptyList()),
    val airingTodaySeries: HomeRecyclerItem = HomeRecyclerItem.AiringToday(emptyList()),
    val tvShowsSeries: HomeRecyclerItem = HomeRecyclerItem.TvShows(emptyList()),
    val actors: HomeRecyclerItem = HomeRecyclerItem.Actor(emptyList()),
)

