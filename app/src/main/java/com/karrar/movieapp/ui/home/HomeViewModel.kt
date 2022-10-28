package com.karrar.movieapp.ui.home

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.AllMediaType
import com.karrar.movieapp.domain.enums.HomeItemsType
import com.karrar.movieapp.domain.home.HomeUseCasesContainer
import com.karrar.movieapp.domain.models.PopularMovie
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.adapters.MovieInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.home.adapter.TVShowInteractionListener
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
    private val homeUseCasesContainer: HomeUseCasesContainer,
) : BaseViewModel(), HomeInteractionListener, ActorsInteractionListener, MovieInteractionListener,
    MediaInteractionListener, TVShowInteractionListener {

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    private val _clickActorEvent = MutableLiveData<Event<Int>>()
    val clickActorEvent = _clickActorEvent.toLiveData()

    private val _clickSeriesEvent = MutableLiveData<Event<Int>>()
    val clickSeriesEvent = _clickSeriesEvent.toLiveData()

    private val _clickSeeAllMovieEvent = MutableLiveData<Event<AllMediaType>>()
    val clickSeeAllMovieEvent = _clickSeeAllMovieEvent.toLiveData()

    private val _clickSeeAllTVShowsEvent = MutableLiveData<Event<AllMediaType>>()
    val clickSeeAllTVShowsEvent = _clickSeeAllTVShowsEvent.toLiveData()

    private val _clickSeeAllActorEvent = MutableLiveData<Event<Boolean>>()
    val clickSeeAllActorEvent = _clickSeeAllActorEvent.toLiveData()

    val homeItemsLiveData = MutableLiveData<UIState<List<HomeRecyclerItem>>>()
    private val homeItems = mutableListOf<HomeRecyclerItem>()


    val homeUiState = MutableStateFlow(HomeUiState())


    private val _failedState = MutableLiveData(0)
    private var counter = 0
    val failedState = MediatorLiveData<UIState<Boolean>>().apply {
        addSource(_failedState, ::updateState)
    }

    private fun updateState(value: Any) {
        if (_failedState.value!! >= Constants.NUM_HOME_REQUEST) {
            failedState.postValue(UIState.Error(""))
            homeItemsLiveData.postValue(UIState.Error(""))
        }
    }

    init {
        getData()
        refreshHomeData()
    }

    private fun refreshHomeData() {
        wrapWithState({
            movieRepository.refreshPopularMovies()
        })
        wrapWithState({
            movieRepository.refreshTrendingMovies()
        })
        wrapWithState({
            movieRepository.refreshNowPlayingMovies()
        })
        wrapWithState({
            movieRepository.refreshAdventureMovies()
        })
        wrapWithState({
            movieRepository.refreshUpcomingMovies()
        })
        wrapWithState({
            movieRepository.refreshMysteryMovies()
        })
        wrapWithState({
            movieRepository.refreshTrendingActors()
        })
        wrapWithState({
            seriesRepository.refreshTopRatedTvShow()
        })
        wrapWithState({
            seriesRepository.refreshAiringToday()
        })
        wrapWithState({
            seriesRepository.refreshOnTheAir()
        })
    }


    private fun refreshDataOneTimeInDay(
        refreshData: () -> Unit,
    ) {
        viewModelScope.launch {
            val requestDate = movieRepository.getRequestDate()
            val currentDate = Date()
            if (requestDate != null) {
                val date = Date(requestDate)
                if (date.after(currentDate)) {
                    refreshData()
                    movieRepository.saveRequestDate(currentDate.time)
                }
            } else {
                refreshData()
                movieRepository.saveRequestDate(currentDate.time)
            }
        }
    }

    override fun getData() {
        homeItemsLiveData.postValue(UIState.Loading)
        getTrending()
        getNowStreaming()
        getUpcoming()
        getTopRatedTvShow()
        getOnTheAir()
        getAiringToday()
        getPopularMovies()
        getMystery()
        getAdventure()
        getActors()
    }




    private fun getPopularMovies() {
        collectData(homeUseCasesContainer.getPopularMoviesUseCase()) {list->
            if (list.isNotEmpty()) {
               homeUiState.update { it.copy(popularMovies = HomeRecyclerItem.Slider(list)) }
            }
        }

    }

    private fun getTrending() {
        collectData(homeUseCasesContainer.getTrendingMoviesUseCase()) {list->
            if (list.isNotEmpty()) {
                homeUiState.update { it.copy(trendingMovies = HomeRecyclerItem.Trending(list)) }
            }
        }
    }

    private fun getActors() {
        collectData(homeUseCasesContainer.getTrendingActorsUseCase()) { list->
            if (list.isNotEmpty()) {
                homeUiState.update { it.copy(actors = HomeRecyclerItem.Actor(list)) }
            }
        }

    }

    private fun getUpcoming() {
        collectData(homeUseCasesContainer.getUpcomingMoviesUseCase())  { list->
            if (list.isNotEmpty()) {
                homeUiState.update { it.copy(upcomingMovies = HomeRecyclerItem.Upcoming(list)) }
            }
        }

    }

    private fun getNowStreaming() {
        collectData(homeUseCasesContainer.getNowStreamingMoviesUseCase())  { list->
            if (list.isNotEmpty()) {
                homeUiState.update { it.copy(nowStreamingMovies = HomeRecyclerItem.NowStreaming(list)) }
            }
        }
    }

    private fun getTopRatedTvShow() {
        collectData(homeUseCasesContainer.getTopRatedTvShowUseCase())  { list->
            if (list.isNotEmpty()) {
                homeUiState.update { it.copy(tvShowsSeries = HomeRecyclerItem.TvShows(list)) }
            }
        }

    }

    private fun getOnTheAir() {
        collectData(homeUseCasesContainer.getOnTheAirUseCase())  { list->
            if (list.isNotEmpty()) {
                homeUiState.update { it.copy(onTheAiringSeries = HomeRecyclerItem.OnTheAiring(list)) }
            }
        }

    }

    private fun getAiringToday() {
        collectData(homeUseCasesContainer.getAiringTodayUseCase())  { list->
            if (list.isNotEmpty()) {
                homeUiState.update { it.copy(airingTodaySeries = HomeRecyclerItem.AiringToday(list)) }
            }
        }

    }

    private fun getMystery() {
        collectData(homeUseCasesContainer.getMysteryMoviesUseCase())  { list->
            if (list.isNotEmpty()) {
                homeUiState.update { it.copy(mysteryMovies = HomeRecyclerItem.Mystery(list)) }
            }
        }


    }

    private fun getAdventure() {
        collectData(homeUseCasesContainer.getAdventureMoviesUseCase())  { list->
            if (list.isNotEmpty()) {
                homeUiState.update { it.copy(adventureMovies = HomeRecyclerItem.Adventure(list)) }
            }
        }


    }

    override fun onClickMovie(movieId: Int) {
        _clickMovieEvent.postEvent(movieId)
    }

    override fun onClickActor(actorID: Int) {
        _clickActorEvent.postEvent(actorID)
    }

    override fun onClickSeeAllMovie(homeItemsType: HomeItemsType) {
        val type = when (homeItemsType) {
            HomeItemsType.ON_THE_AIR -> AllMediaType.ON_THE_AIR
            HomeItemsType.TRENDING -> AllMediaType.TRENDING
            HomeItemsType.NOW_STREAMING -> AllMediaType.NOW_STREAMING
            HomeItemsType.UPCOMING -> AllMediaType.UPCOMING
            HomeItemsType.MYSTERY -> AllMediaType.MYSTERY
            HomeItemsType.ADVENTURE -> AllMediaType.ADVENTURE
            HomeItemsType.NON -> AllMediaType.ACTOR_MOVIES
        }
        _clickSeeAllMovieEvent.postEvent(type)
    }

    override fun onClickSeeAllActors() {
        _clickSeeAllActorEvent.postEvent(true)
    }

    override fun onClickMedia(mediaId: Int) {
        _clickSeriesEvent.postEvent(mediaId)
    }

    override fun onClickTVShow(tVShowID: Int) {
        _clickSeriesEvent.postEvent(tVShowID)
    }

    override fun onClickSeeTVShow(type: AllMediaType) {
        _clickSeeAllTVShowsEvent.postEvent(type)
    }


}