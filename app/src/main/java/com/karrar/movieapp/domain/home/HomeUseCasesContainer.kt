package com.karrar.movieapp.domain.home

import javax.inject.Inject

class HomeUseCasesContainer @Inject constructor(
    val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    val getAiringTodayUseCase: GetAiringTodayUseCase,
    val getOnTheAirUseCase: GetOnTheAirUseCase,
    val getTopRatedTvShowUseCase: GetTopRatedTvShowUseCase,
    val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    val getMysteryMoviesUseCase: GetMysteryMoviesUseCase,
    val getAdventureMoviesUseCase: GetAdventureMoviesUseCase,
    val getNowStreamingMoviesUseCase: GetNowStreamingMoviesUseCase,
    val getTrendingActorsUseCase: GetTrendingActorsUseCase,
)