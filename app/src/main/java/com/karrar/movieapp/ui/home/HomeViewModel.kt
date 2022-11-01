package com.karrar.movieapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.RequestStatus
import com.karrar.movieapp.domain.enums.AllMediaType
import com.karrar.movieapp.domain.enums.HomeItemsType
import com.karrar.movieapp.domain.usecases.home.HomeUseCasesContainer
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.adapters.MovieInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.home.adapter.TVShowInteractionListener
import com.karrar.movieapp.ui.home.homeUiState.HomeUiState
import com.karrar.movieapp.ui.mappers.ActorUiMapper
import com.karrar.movieapp.ui.mappers.MediaUiMapper
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
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

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()


    init {
        viewModelScope.launch {
            getHomeData()
            refreshHomeDataOneTimePerDayUseCase()
        }
    }

    private suspend fun refreshHomeDataOneTimePerDayUseCase(){
        try {
            _homeUiState.update { it.copy(isLoading = true) }
            homeUseCasesContainer.refreshHomeDataOneTimePerDayUseCase()
        }catch (e:Throwable){
            _homeUiState.update { it.copy(isLoading = false) }
            _homeUiState.update { it.copy(error = e.message.toString()) }
        }
    }

    private fun getHomeData(){
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
        viewModelScope.launch {
            _homeUiState.update { it.copy(isLoading = true) }
            when(val requestStatus = homeUseCasesContainer.refreshHomeDataUseCase()){
                is RequestStatus.Failure -> _homeUiState.update { it.copy(isLoading = false, error = requestStatus.message) }
                RequestStatus.Success -> _homeUiState.update { it.copy(isLoading = false, error = "") }
            }
        }
    }


    private fun getPopularMovies() {
        collectData(homeUseCasesContainer.getPopularMoviesUseCase()) { list ->
            if (list.isNotEmpty()) {
                val items = list.map(popularUiMapper::map)
                _homeUiState.update {
                    it.copy(popularMovies = HomeItem.Slider(items),
                        isLoading = false)
                }
            }
        }

    }

    private fun getTrending() {
        collectData(homeUseCasesContainer.getTrendingMoviesUseCase()) { list ->
            if (list.isNotEmpty()) {
                val items = list.map(mediaUiMapper::map)
                _homeUiState.update {
                    it.copy(trendingMovies = HomeItem.Trending(items),
                        isLoading = false)
                }
            }
        }
    }

    private fun getActors() {
        collectData(homeUseCasesContainer.getTrendingActorsUseCase()) { list ->
            if (list.isNotEmpty()) {
                val items = list.map(actorUiMapper::map)
                _homeUiState.update { it.copy(actors = HomeItem.Actor(items), isLoading = false) }
            }
        }

    }

    private fun getUpcoming() {
        collectData(homeUseCasesContainer.getUpcomingMoviesUseCase()) { list ->
            if (list.isNotEmpty()) {
                val items = list.map(mediaUiMapper::map)
                _homeUiState.update {
                    it.copy(upcomingMovies = HomeItem.Upcoming(items),
                        isLoading = false)
                }
            }
        }

    }

    private fun getNowStreaming() {
        collectData(homeUseCasesContainer.getNowStreamingMoviesUseCase()) { list ->
            if (list.isNotEmpty()) {
                val items = list.map(mediaUiMapper::map)
                _homeUiState.update {
                    it.copy(nowStreamingMovies = HomeItem.NowStreaming(items),
                        isLoading = false)
                }
            }
        }
    }

    private fun getTopRatedTvShow() {
        collectData(homeUseCasesContainer.getTopRatedTvShowUseCase()) { list ->
            if (list.isNotEmpty()) {
                val items = list.map(mediaUiMapper::map)
                _homeUiState.update {
                    it.copy(tvShowsSeries = HomeItem.TvShows(items),
                        isLoading = false)
                }
            }
        }

    }

    private fun getOnTheAir() {
        collectData(homeUseCasesContainer.getOnTheAirUseCase()) { list ->
            if (list.isNotEmpty()) {
                val items = list.map(mediaUiMapper::map)
                _homeUiState.update {
                    it.copy(onTheAiringSeries = HomeItem.OnTheAiring(items),
                        isLoading = false)
                }
            }
        }

    }

    private fun getAiringToday() {
        collectData(homeUseCasesContainer.getAiringTodayUseCase()) { list ->
            if (list.isNotEmpty()) {
                val items = list.map(mediaUiMapper::map)
                _homeUiState.update {
                    it.copy(airingTodaySeries = HomeItem.AiringToday(items),
                        isLoading = false)
                }
            }
        }

    }

    private fun getMystery() {
        collectData(homeUseCasesContainer.getMysteryMoviesUseCase()) { list ->
            if (list.isNotEmpty()) {
                val items = list.map(mediaUiMapper::map)
                _homeUiState.update {
                    it.copy(mysteryMovies = HomeItem.Mystery(items),
                        isLoading = false)
                }
            }
        }


    }

    private fun getAdventure() {
        collectData(homeUseCasesContainer.getAdventureMoviesUseCase()) { list ->
            if (list.isNotEmpty()) {
                val items = list.map(mediaUiMapper::map)
                _homeUiState.update {
                    it.copy(adventureMovies = HomeItem.Adventure(items),
                        isLoading = false)
                }
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