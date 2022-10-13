package com.karrar.movieapp.ui.home

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
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
            failedState.postValue(UIState.Error(""))
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
        wrapWithState({
            val responseGenre = movieRepository.getMovieGenreList2()
            val responseMovie = movieRepository.getPopularMovies2(responseGenre)
            updateHomeItems(HomeRecyclerItem.Slider(responseMovie))
        }, {
            _failedState.postValue(++counter)
        })
    }

    private fun getTrending() {
        wrapWithState(
            {
                updateHomeItems(
                    HomeRecyclerItem.Trending(
                        movieRepository.getTrendingMovies2(1),
                        MovieType.TRENDING
                    )
                )
            }, {
                _failedState.postValue(++counter)
            })
    }

    private fun getActors() {
        wrapWithState(
            { updateHomeItems(HomeRecyclerItem.Actor(movieRepository.getTrendingActors())) })
    }

    private fun getUpcoming() {
        wrapWithState(
            {
                updateHomeItems(
                    HomeRecyclerItem.Upcoming(
                        movieRepository.getUpcomingMovies2(1),
                        MovieType.UPCOMING
                    )
                )
            }, {
                _failedState.postValue(++counter)
            })
    }

    private fun getNowStreaming() {
        wrapWithState({
            updateHomeItems(
                HomeRecyclerItem.NowStreaming(
                    movieRepository.getNowPlayingMovies2(1),
                    MovieType.NOW_STREAMING
                )
            )
        }, {
            _failedState.postValue(++counter)
        })
    }

    private fun getTopRatedTvShow() {
        wrapWithState({
            updateHomeItems(HomeRecyclerItem.TvShows(seriesRepository.getTopRatedTvShow2()))
        }, {
            _failedState.postValue(++counter)
        })
    }

    private fun getOnTheAir() {
        wrapWithState({
            updateHomeItems(
                HomeRecyclerItem.OnTheAiring(
                    seriesRepository.getOnTheAir2(),
                    MovieType.ON_THE_AIR
                )
            )
        }, {
            _failedState.postValue(++counter)
        })
    }

    private fun getAiringToday() {
        wrapWithState({
            updateHomeItems(HomeRecyclerItem.AiringToday(seriesRepository.getAiringToday2()))
        }, {
            _failedState.postValue(++counter)
        })
    }

    private fun getMovieListByGenreID(genreID: Int, type: MovieType) {
        when (type) {
            MovieType.MYSTERY -> {
                wrapWithState({
                    updateHomeItems(
                        HomeRecyclerItem.Mystery(
                            movieRepository.getMovieListByGenreID2(
                                genreID,
                            1), type
                        )
                    )
                }, {
                    _failedState.postValue(++counter)
                })
            }
            else -> {
                wrapWithState({
                    updateHomeItems(
                        HomeRecyclerItem.Adventure(
                            movieRepository.getMovieListByGenreID2(
                                genreID,
                            1), type
                        )
                    )
                }, {
                    _failedState.postValue(++counter)
                })
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