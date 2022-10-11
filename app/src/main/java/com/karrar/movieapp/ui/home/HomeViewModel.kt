package com.karrar.movieapp.ui.home

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.adapters.MovieInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository
) : BaseViewModel(), HomeInteractionListener, ActorsInteractionListener, MovieInteractionListener,
    MediaInteractionListener {

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

    val homeItemsLiveData = MutableLiveData<UIState<List<HomeRecyclerItem>>>()
    private val homeItems = mutableListOf<HomeRecyclerItem>()

    private val _failedState = MutableLiveData(0)
    private var counter = 0
    val failedState = MediatorLiveData<UIState<Boolean>>().apply {
        addSource(_failedState, ::updateState)
    }

    private fun updateState(value: Any) {
        if (_failedState.value!! >= Constants.NUM_HOME_REQUEST) {
            failedState.postValue(UIState.Error)
        }
    }

    init {
        homeItemsLiveData.postValue(UIState.Loading)
        getTrending()
        getNowStreaming()
        getUpcoming()
        getActors()
        getTopRatedTvShow()
        getOnTheAir()
        getAiringToday()
        getPopularMovies()
        getMovieListByGenreID(Constants.ADVENTURE_ID, MovieType.ADVENTURE)
        getMovieListByGenreID(Constants.MYSTERY_ID, MovieType.MYSTERY)
    }

    private fun updateHomeItems(item: HomeRecyclerItem) {
        homeItems.add(item)
        homeItemsLiveData.postValue(UIState.Success(homeItems))
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            val responseGenre = movieRepository.getMovieGenreList2()
            val responseMovie = movieRepository.getPopularMovies2(responseGenre)
            updateHomeItems(HomeRecyclerItem.Slider(responseMovie))
        }
    }

    private fun getTrending() {
        viewModelScope.launch {
            val response = movieRepository.getTrendingMovies2()
            if (response.isEmpty()) {
                _failedState.postValue(++counter)
            } else {
                updateHomeItems(HomeRecyclerItem.Trending(response, MovieType.TRENDING))
            }
        }
    }

    private fun getActors() {
        viewModelScope.launch {
            val response = movieRepository.getTrendingActors()
            if (response.isNullOrEmpty()) {
                _failedState.postValue(++counter)
            } else {
                updateHomeItems(HomeRecyclerItem.Actor(response))
            }
        }
    }

    private fun getUpcoming() {
        viewModelScope.launch {
            val response = movieRepository.getUpcomingMovies2()
            if (response.isEmpty()) {
                _failedState.postValue(++counter)
            } else {
                updateHomeItems(HomeRecyclerItem.Upcoming(response, MovieType.UPCOMING))
            }
        }
    }

    private fun getNowStreaming() {
        viewModelScope.launch {
            val response = movieRepository.getNowPlayingMovies2()
            if (response.isEmpty()) {
                _failedState.postValue(++counter)
            } else {
                updateHomeItems(HomeRecyclerItem.NowStreaming(response, MovieType.NOW_STREAMING))
            }
        }
    }

    private fun getTopRatedTvShow() {
        viewModelScope.launch {
            val response = seriesRepository.getTopRatedTvShow2()
            if (response.isEmpty()) {
                _failedState.postValue(++counter)
            } else {
                updateHomeItems(HomeRecyclerItem.TvShows(response))
            }
        }
    }

    private fun getOnTheAir() {
        viewModelScope.launch {
            val response = seriesRepository.getOnTheAir2()
            if (response.isEmpty()) {
                _failedState.postValue(++counter)
            } else {
                updateHomeItems(HomeRecyclerItem.OnTheAiring(response, MovieType.ON_THE_AIR))
            }
        }
    }

    private fun getAiringToday() {
        viewModelScope.launch {
            val response = seriesRepository.getAiringToday2()
            if (response.isEmpty()) {
                _failedState.postValue(++counter)
            } else {
                updateHomeItems(HomeRecyclerItem.AiringToday(response))
            }
        }
    }

    private fun getMovieListByGenreID(genreID: Int, type: MovieType) {
        viewModelScope.launch {
            val movieList =
                movieRepository.getMovieListByGenreID2(genreID)

            if (movieList.isEmpty()) {
                _failedState.postValue(++counter)
            } else {
                val item = when (type) {
                    MovieType.MYSTERY -> {
                        HomeRecyclerItem.Mystery(movieList, type)
                    }

                    else -> {
                        HomeRecyclerItem.Adventure(movieList, type)
                    }
                }
                updateHomeItems(item)
            }

        }
    }

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