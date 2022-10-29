package com.karrar.movieapp.domain.home.refreshData

import com.karrar.movieapp.domain.RequestStatus
import com.karrar.movieapp.domain.home.refreshData.movie.*
import com.karrar.movieapp.domain.home.refreshData.series.RefreshAiringTodaySeriesUseCase
import com.karrar.movieapp.domain.home.refreshData.series.RefreshOnTheAirSeriesUseCase
import com.karrar.movieapp.domain.home.refreshData.series.RefreshTopRatedTvShowSeriesUseCase
import javax.inject.Inject

class RefreshHomeDataUseCase @Inject constructor(
    private val refreshPopularMoviesUseCase: RefreshPopularMoviesUseCase,
    private val refreshTrendingMoviesUseCase: RefreshTrendingMoviesUseCase,
    private val refreshMysteryMoviesUseCase: RefreshMysteryMoviesUseCase,
    private val refreshNowStreamingMoviesUseCase: RefreshNowStreamingMoviesUseCase,
    private val refreshAdventureMoviesUseCase: RefreshAdventureMoviesUseCase,
    private val refreshUpcomingMoviesUseCase: RefreshUpcomingMoviesUseCase,
    private val refreshTrendingActorsUseCase: RefreshTrendingActorsUseCase,
    private val onTheAirUseCase: RefreshOnTheAirSeriesUseCase,
    private val airingTodayUseCase: RefreshAiringTodaySeriesUseCase,
    private val topRatedTvShowUseCase: RefreshTopRatedTvShowSeriesUseCase,
) {
    suspend operator fun invoke() : RequestStatus{
       return try {
           refreshPopularMoviesUseCase()
           refreshTrendingMoviesUseCase()
           refreshMysteryMoviesUseCase()
           refreshNowStreamingMoviesUseCase()
           refreshAdventureMoviesUseCase()
           refreshUpcomingMoviesUseCase()
           refreshTrendingActorsUseCase()
           onTheAirUseCase()
           airingTodayUseCase()
           topRatedTvShowUseCase()
           RequestStatus.Success
       }catch (e:Throwable){
           RequestStatus.Failure(e.message.toString())
       }

    }
}