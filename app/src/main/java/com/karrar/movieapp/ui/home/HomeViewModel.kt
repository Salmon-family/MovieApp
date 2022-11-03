package com.karrar.movieapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.RequestStatus
import com.karrar.movieapp.domain.enums.AllMediaType
import com.karrar.movieapp.domain.enums.HomeItemsType
import com.karrar.movieapp.domain.usecase.home.HomeUseCasesContainer
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.adapters.MovieInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.home.adapter.TVShowInteractionListener
import com.karrar.movieapp.ui.home.homeUiState.HomeUIEvent
import com.karrar.movieapp.ui.home.homeUiState.HomeUiState
import com.karrar.movieapp.ui.mappers.ActorUiMapper
import com.karrar.movieapp.ui.mappers.MediaUiMapper
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCasesContainer: HomeUseCasesContainer,
    private val mediaUiMapper: MediaUiMapper,
    private val actorUiMapper: ActorUiMapper,
    private val popularUiMapper: PopularUiMapper,
) : BaseViewModel(), HomeInteractionListener, ActorsInteractionListener, MovieInteractionListener,
    MediaInteractionListener, TVShowInteractionListener {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    private val _homeUIEvent = MutableStateFlow<Event<HomeUIEvent?>>(Event(null))
    val homeUIEvent = _homeUIEvent.asStateFlow()

    init {
        getHomeData()
    }


    private fun getHomeData() {
        _homeUiState.update { it.copy(isLoading = true) }
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

    override fun getData() {
        getHomeData()
        _homeUiState.update { it.copy(error = emptyList()) }
    }


    private fun getPopularMovies() {
        viewModelScope.launch {
            try {
                homeUseCasesContainer.getPopularMoviesUseCase().collect { list ->
                    if (list.isNotEmpty()) {
                        val items = list.map(popularUiMapper::map)
                        _homeUiState.update {
                            it.copy(popularMovies = HomeItem.Slider(items),
                                isLoading = false)
                        }
                    }
                }
            } catch (th: Throwable) {
                onError(th.message.toString())
            }
        }
    }

    private fun onError(message: String) {
        val errors = _homeUiState.value.error.toMutableList()
        errors.add(message)
        _homeUiState.update { it.copy(error = errors, isLoading = false) }
    }

    private fun getTrending() {
        viewModelScope.launch {
            try {
                homeUseCasesContainer.getTrendingMoviesUseCase().collect { list ->
                    if (list.isNotEmpty()) {
                        val items = list.map(mediaUiMapper::map)
                        _homeUiState.update {
                            it.copy(trendingMovies = HomeItem.Trending(items),
                                isLoading = false)
                        }
                    }
                }
            } catch (th: Throwable) {
                onError(th.message.toString())
            }
        }

    }

    private fun getActors() {
        viewModelScope.launch {
            try {
                homeUseCasesContainer.getTrendingActorsUseCase().collect { list ->
                    if (list.isNotEmpty()) {
                        val items = list.map(actorUiMapper::map)
                        _homeUiState.update {
                            it.copy(actors = HomeItem.Actor(items),
                                isLoading = false)
                        }
                    }
                }
            } catch (th: Throwable) {
                onError(th.message.toString())
            }
        }

    }

    private fun getUpcoming() {
        viewModelScope.launch {
            try {
                homeUseCasesContainer.getUpcomingMoviesUseCase().collect { list ->
                    if (list.isNotEmpty()) {
                        val items = list.map(mediaUiMapper::map)
                        _homeUiState.update {
                            it.copy(upcomingMovies = HomeItem.Upcoming(items),
                                isLoading = false)
                        }
                    }
                }
            } catch (th: Throwable) {
                onError(th.message.toString())
            }
        }


    }

    private fun getNowStreaming() {
        viewModelScope.launch {
            try {
                homeUseCasesContainer.getNowStreamingMoviesUseCase().collect { list ->
                    if (list.isNotEmpty()) {
                        val items = list.map(mediaUiMapper::map)
                        _homeUiState.update {
                            it.copy(nowStreamingMovies = HomeItem.NowStreaming(items),
                                isLoading = false)
                        }
                    }
                }
            } catch (th: Throwable) {
                onError(th.message.toString())
            }
        }

    }

    private fun getTopRatedTvShow() {
        viewModelScope.launch {
            try {
                homeUseCasesContainer.getTopRatedTvShowUseCase().collect { list ->
                    if (list.isNotEmpty()) {
                        val items = list.map(mediaUiMapper::map)
                        _homeUiState.update {
                            it.copy(tvShowsSeries = HomeItem.TvShows(items),
                                isLoading = false)
                        }
                    }
                }
            }
        }

    }

    private fun getOnTheAir() {
        viewModelScope.launch {
            try {
                homeUseCasesContainer.getOnTheAirUseCase().collect { list ->
                    if (list.isNotEmpty()) {
                        val items = list.map(mediaUiMapper::map)
                        _homeUiState.update {
                            it.copy(onTheAiringSeries = HomeItem.OnTheAiring(items),
                                isLoading = false)
                        }
                    }
                }
            } catch (th: Throwable) {
                onError(th.message.toString())
            }
        }

    }

    private fun getAiringToday() {
        viewModelScope.launch {
            try {
                homeUseCasesContainer.getAiringTodayUseCase().collect { list ->
                    if (list.isNotEmpty()) {
                        val items = list.map(mediaUiMapper::map)
                        _homeUiState.update {
                            it.copy(airingTodaySeries = HomeItem.AiringToday(items),
                                isLoading = false)
                        }
                    }
                }
            } catch (th: Throwable) {
                onError(th.message.toString())
            }
        }


    }

    private fun getMystery() {
        viewModelScope.launch {
            try {
                homeUseCasesContainer.getMysteryMoviesUseCase().collect { list ->
                    if (list.isNotEmpty()) {
                        val items = list.map(mediaUiMapper::map)
                        _homeUiState.update {
                            it.copy(mysteryMovies = HomeItem.Mystery(items),
                                isLoading = false)
                        }
                    }
                }
            } catch (th: Throwable) {
                onError(th.message.toString())
            }
        }

    }

    private fun getAdventure() {
        viewModelScope.launch {
            try {
                homeUseCasesContainer.getAdventureMoviesUseCase().collect { list ->
                    if (list.isNotEmpty()) {
                        val items = list.map(mediaUiMapper::map)
                        _homeUiState.update {
                            it.copy(adventureMovies = HomeItem.Adventure(items),
                                isLoading = false)
                        }
                    }
                }
            } catch (th: Throwable) {
                onError(th.message.toString())
            }
        }

    }

    override fun onClickMovie(movieId: Int) {
        _homeUIEvent.update { Event(HomeUIEvent.ClickMovieEvent(movieId)) }
    }

    override fun onClickActor(actorID: Int) {
        _homeUIEvent.update { Event(HomeUIEvent.ClickActorEvent(actorID)) }
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
        _homeUIEvent.update { Event(HomeUIEvent.ClickSeeAllMovieEvent(type)) }
    }

    override fun onClickSeeAllActors() {
        _homeUIEvent.update { Event(HomeUIEvent.ClickSeeAllActorEvent) }

    }

    override fun onClickMedia(mediaId: Int) {
        _homeUIEvent.update { Event(HomeUIEvent.ClickSeriesEvent(mediaId)) }
    }

    override fun onClickTVShow(tVShowID: Int) {
        _homeUIEvent.update { Event(HomeUIEvent.ClickSeriesEvent(tVShowID)) }
    }

    override fun onClickSeeTVShow(type: AllMediaType) {
        _homeUIEvent.update { Event(HomeUIEvent.ClickSeeAllTVShowsEvent(type)) }
    }


}