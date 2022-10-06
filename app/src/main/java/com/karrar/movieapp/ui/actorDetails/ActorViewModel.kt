package com.karrar.movieapp.ui.actorDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.domain.models.ActorDetails
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.home.adapters.MovieInteractionListener
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
    private val state: SavedStateHandle
) : ViewModel(), MovieInteractionListener {

    private val args = ActorDetailsFragmentArgs.fromSavedStateHandle(state)
    val actorId = args.id

    private val _actorDetails = MutableLiveData<State<ActorDetails>>()
    val actorDetails = _actorDetails.toLiveData()

    private val _actorMovies = MutableLiveData<State<List<Media?>>>()
    val actorMovies = _actorMovies.toLiveData()

    private val _backEvent = MutableLiveData<Event<Boolean>>()
    val backEvent = _backEvent.toLiveData()

    private val _seeAllMovies = MutableLiveData<Event<Boolean>>()
    val seeAllMovies = _seeAllMovies.toLiveData()

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    init {
        getActorDetailsById()
    }

    private fun getActorDetailsById() {
        _actorDetails.postValue(State.Loading)
        collectResponse(movieRepository.getActorDetails(actorId)) {
            _actorDetails.postValue(it)
        }
        collectResponse(movieRepository.getActorMovies(actorId)) {
            _actorMovies.postValue(it)
        }
    }

    fun onClickBack() {
        _backEvent.postValue(Event(true))
    }

    override fun onClickMovie(movieId: Int) {
        _clickMovieEvent.postEvent(movieId)
    }

    override fun onClickSeeAllMovie(movieType: MovieType) {
        _seeAllMovies.postValue(Event(true))
    }

    private fun <T> collectResponse(flow: Flow<State<T>>, function: (State<T>) -> Unit) {
        viewModelScope.launch {
            flow.flowOn(Dispatchers.IO)
                .collect { state ->
                    function(state)
                }
        }
    }

}