package com.karrar.movieapp.domain.home

import com.karrar.movieapp.domain.home.getData.GetTrendingActorsUseCase
import com.karrar.movieapp.domain.home.getData.movie.*
import com.karrar.movieapp.domain.home.getData.series.GetAiringTodaySeriesUseCase
import com.karrar.movieapp.domain.home.getData.series.GetOnTheAirSeriesUseCase
import com.karrar.movieapp.domain.home.getData.series.GetTopRatedTvShowSeriesUseCase
import com.karrar.movieapp.domain.home.refreshData.RefreshHomeDataUseCase
import javax.inject.Inject

class HomeUseCasesContainer @Inject constructor(
    val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    val getAiringTodayUseCase: GetAiringTodaySeriesUseCase,
    val getOnTheAirUseCase: GetOnTheAirSeriesUseCase,
    val getTopRatedTvShowUseCase: GetTopRatedTvShowSeriesUseCase,
    val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    val getMysteryMoviesUseCase: GetMysteryMoviesUseCase,
    val getAdventureMoviesUseCase: GetAdventureMoviesUseCase,
    val getNowStreamingMoviesUseCase: GetNowStreamingMoviesUseCase,
    val getTrendingActorsUseCase: GetTrendingActorsUseCase,
    val refreshHomeDataUseCase: RefreshHomeDataUseCase,
)