package com.karrar.movieapp.ui.actorDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.domain.models.ActorDetails
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.home.adapters.MovieInteractionListener
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    val seriesRepository: SeriesRepository) :
    ViewModel(), MovieInteractionListener,MediaInteractionListener {

    private val _actorDetails = MutableLiveData<State<ActorDetails>>()
    val actorDetails: LiveData<State<ActorDetails>> = _actorDetails

    private val _actorMovies = MutableLiveData<State<List<Media>>>()
    val actorMovies: LiveData<State<List<Media>>> = _actorMovies

    private val _backEvent = MutableLiveData<Event<Boolean>>()
    val backEvent: LiveData<Event<Boolean>> = _backEvent

    private val _seeAllMovies = MutableLiveData<Event<Boolean>>()
    val seeAllMovies: LiveData<Event<Boolean>> = _seeAllMovies

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    fun getDetailsById(actorId: Int) {
        _actorDetails.postValue(State.Loading)
        collectResponse(movieRepository.getActorDetails(actorId)) {
            _actorDetails.postValue(it)
        }
    }

    fun getActorMoviesById(actorId: Int) {
        if (actorId != -1) {
            _actorMovies.postValue(State.Loading)
            collectResponse(movieRepository.getActorMovies(actorId)) {
                _actorMovies.postValue(it)
            }
        }
    }

    fun getTypeMovies(type: MovieType) {
        if (type != MovieType.NON) {
            _actorMovies.postValue(State.Loading)
            val request = when (type) {
                MovieType.TRENDING -> {
                    movieRepository.getTrendingMovies()
                }
                MovieType.UPCOMING -> {
                    movieRepository.getUpcomingMovies()
                }
                MovieType.MYSTERY -> {
                    movieRepository.getMovieListByGenre(Constants.MYSTERY_ID)
                }
                MovieType.ADVENTURE -> {
                    movieRepository.getMovieListByGenre(Constants.ADVENTURE_ID)
                }
                MovieType.NOW_STREAMING -> {
                    movieRepository.getNowPlayingMovies()
                }
                MovieType.ON_THE_AIR -> {
                    seriesRepository.getOnTheAir()
                }
                else -> {
                    throw Throwable("Error")
                }
            }
            collectResponse(request) {
                _actorMovies.postValue(it)
            }
        }
    }

    private fun <T> collectResponse(flow: Flow<State<T>>, function: (State<T>) -> Unit) {
        viewModelScope.launch {
            flow.flowOn(Dispatchers.IO)
                .collect { state ->
                    function(state)
                }
        }
    }

    fun onClickBack() {
        _backEvent.postValue(Event(true))
    }

    fun onClickSeeAll() {
        _seeAllMovies.postValue(Event(true))
    }

    override fun onClickMovie(movieId: Int) {
        _clickMovieEvent.postEvent(movieId)
    }

    override fun onClickSeeAllMovie(movieType: MovieType) {
        TODO("Not yet implemented")
    }

}