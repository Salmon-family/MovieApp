package com.karrar.movieapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.utilities.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieRepository: MovieRepository,
     seriesRepository: SeriesRepository,
) : ViewModel(), HomeInteractionListener {
    val popularMovie = movieRepository.getPopularMovies().asLiveData()


    val trending = movieRepository.getTrendingMovies().asLiveData()

    val nowStreaming = movieRepository.getNowPlayingMovies().asLiveData()

    val upcoming = movieRepository.getUpcomingMovies().asLiveData()

    val mysteryMovie = movieRepository.getMovieListByGenre(Constants.MYSTERY_ID).asLiveData()

    val adventureMovie = movieRepository.getMovieListByGenre(Constants.ADVENTURE_ID).asLiveData()

    val onTheAiring = seriesRepository.getOnTheAir().asLiveData()

    val actors = movieRepository.getTrendingPersons().asLiveData()
    val airingToday = seriesRepository.getAiringToday().asLiveData()
    val topRatedTvShow = seriesRepository.getTopRatedTvShow().asLiveData()
    val latestTvShow = seriesRepository.getLatestTvShow().asLiveData()
    val popularTvShow = seriesRepository.getPopularTvShow().asLiveData()
    override fun onClickMovie(movieID: Int) {
        Log.e("DEVFALAH",movieID.toString())
    }

    override fun onClickActor(actorID: Int) {
        Log.e("DEVFALAH",actorID.toString())
    }

    override fun onClickAiringToday(airingTodayID: Int) {
        Log.e("DEVFALAH",airingTodayID.toString())

    }

    override fun onClickSeeAllMovie(movieType: MovieType) {
        Log.e("DEVFALAH",movieType.name)

    }


}