package com.karrar.movieapp.ui.home

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.adapters.MovieInteractionListener
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieRepository: MovieRepository,
    seriesRepository: SeriesRepository
) : ViewModel(), HomeInteractionListener , ActorsInteractionListener, MovieInteractionListener ,
    MediaInteractionListener {


    private val homeItems = MediatorLiveData<HomeRecyclerItem>()
    fun successItems (): MediatorLiveData<HomeRecyclerItem> {
        return homeItems.apply {
            addSource(popularMovie){ handleState(it){items-> value = (HomeRecyclerItem.Slider(items))}}
            addSource(topRatedTvShow){handleState(it) {items-> value = (HomeRecyclerItem.TvShows(items)) }}
            addSource(onTheAiring){handleState(it){items-> value = HomeRecyclerItem.OnTheAiring(items,MovieType.ON_THE_AIR)}}
            addSource(trending){handleState(it){items-> value = HomeRecyclerItem.Trending(items,MovieType.TRENDING)}}
            addSource(airingToday){handleState(it){items-> value = (HomeRecyclerItem.AiringToday(items)) }}
            addSource(nowStreaming) {handleState(it){items-> value = HomeRecyclerItem.NowStreaming(items,MovieType.NOW_STREAMING)}}
            addSource(upcoming){handleState(it){items-> value = HomeRecyclerItem.Upcoming(items,MovieType.UPCOMING)}}
            addSource(mysteryMovie){handleState(it){items-> value = HomeRecyclerItem.Mystery(items,MovieType.MYSTERY)}}
            addSource(adventureMovie){handleState(it){items-> value = HomeRecyclerItem.Adventure(items,MovieType.ADVENTURE)}}
            addSource(actors){handleState(it){items-> value = (HomeRecyclerItem.Actor(items)) }}
        }
    }

    fun removeAllHomeItemsMediatorSource(){
        homeItems.removeSource(popularMovie)
        homeItems.removeSource(topRatedTvShow)
        homeItems.removeSource(onTheAiring)
        homeItems.removeSource(trending)
        homeItems.removeSource(airingToday)
        homeItems.removeSource(nowStreaming)
        homeItems.removeSource(upcoming)
        homeItems.removeSource(mysteryMovie)
        homeItems.removeSource(adventureMovie)
        homeItems.removeSource(actors)
    }

    private fun <T>handleState(state: State<List<T>>, function: (List<T>) -> Unit){
        state.toData()?.let {
            function(it)
        }

    }

    private val popularMovie = movieRepository.getPopularMovies().asLiveData()
    private val trending = movieRepository.getTrendingMovies().asLiveData()
    private val nowStreaming = movieRepository.getNowPlayingMovies().asLiveData()
    private val upcoming = movieRepository.getUpcomingMovies().asLiveData()
    private val mysteryMovie = movieRepository.getMovieListByGenreID(Constants.MYSTERY_ID).asLiveData()
    private val adventureMovie = movieRepository.getMovieListByGenreID(Constants.ADVENTURE_ID).asLiveData()
    private val onTheAiring = seriesRepository.getOnTheAir().asLiveData()
    private val actors = movieRepository.getTrendingActors().asLiveData()
    private val airingToday = seriesRepository.getAiringToday().asLiveData()
    private val topRatedTvShow = seriesRepository.getTopRatedTvShow().asLiveData()
    val latestTvShow = seriesRepository.getLatestTvShows().asLiveData()
    val popularTvShow = seriesRepository.getPopularTvShow().asLiveData()

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    private val _clickActorEvent = MutableLiveData<Event<Int>>()
    val clickActorEvent = _clickActorEvent.toLiveData()

    private val _clickSeriesEvent = MutableLiveData<Event<Int>>()
    val clickSeriesEvent = _clickSeriesEvent.toLiveData()

    private val _clickSeeAllMovieEvent = MutableLiveData<Event<MovieType>>()
    val clickSeeAllMovieEvent = _clickSeeAllMovieEvent.toLiveData()

    private val _clickSeeAllActorEvent = MutableLiveData<Event<Boolean>>()
    val clickSeeAllActorEvent = _clickSeeAllActorEvent.toLiveData()

    override fun onClickMovie(movieId: Int) {
        _clickMovieEvent.postEvent(movieId)
    }

    override fun onClickActor(actorID: Int) {
        _clickActorEvent.postEvent(actorID)
    }

    override fun onClickSeeAllMovie(movieType: MovieType) {
        _clickSeeAllMovieEvent.postEvent(movieType)
    }

    override fun onClickSeeAllActors() {
        _clickSeeAllActorEvent.postEvent(true)
    }

    override fun onClickMedia(mediaId: Int) {
        _clickSeriesEvent.postEvent(mediaId)
    }


}