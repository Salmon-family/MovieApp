package com.karrar.movieapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.ui.actorDetails.MovieInteractionListener
import com.karrar.movieapp.ui.actors.adapters.ActorsInteractionListener
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieRepository: MovieRepository,
    seriesRepository: SeriesRepository,
) : ViewModel(), HomeInteractionListener, ActorsInteractionListener, MovieInteractionListener {

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

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    private val _clickActorEvent = MutableLiveData<Event<Int>>()
    val clickActorEvent = _clickActorEvent.toLiveData()

    private val _clickSeeAllMovieEvent = MutableLiveData<Event<MovieType>>()
    val clickSeeAllMovieEvent = _clickSeeAllMovieEvent.toLiveData()

    private val _clickSeeAllActorEvent = MutableLiveData<Event<Boolean>>()
    val clickSeeAllActorEvent = _clickSeeAllActorEvent.toLiveData()

    override fun onClickMovie(movieID: Int) {
//        when(type){
//            MovieType.ON_THE_AIR -> TODO()
//            MovieType.TRENDING -> TODO()
//            MovieType.NOW_STREAMING -> TODO()
//            MovieType.UPCOMING -> TODO()
//            MovieType.MYSTERY -> TODO()
//            MovieType.ADVENTURE -> TODO()
//        }
    }

    override fun onClickActor(actorID: Int) {
    }

    override fun onClickAiringToday(airingTodayID: Int) {
    }

    override fun onClickSeeAllMovie(movieType: MovieType) {
        Log.e("DEVFALAH", movieType.name)

    }

    override fun onClickSeeAllActors() {
        _clickSeeAllActorEvent.postEvent(true)
    }


}