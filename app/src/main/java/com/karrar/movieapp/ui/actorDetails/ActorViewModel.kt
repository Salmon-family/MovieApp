package com.karrar.movieapp.ui.actorDetails

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.domain.models.ActorDetails
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel(), MovieInteractionListener {

    private val _actorDetails = MutableLiveData<State<ActorDetails>>()
    val actorDetails: LiveData<State<ActorDetails>> = _actorDetails

    private val _actorMovies = MutableLiveData<State<List<Media>>>()
    val actorMovies: LiveData<State<List<Media>>> = _actorMovies

    private val _backEvent = MutableLiveData<Event<Boolean>>()
    val backEvent: LiveData<Event<Boolean>> = _backEvent

    private val _seeAllMovies = MutableLiveData<Event<Boolean>>()
    val seeAllMovies: LiveData<Event<Boolean>> = _seeAllMovies

    fun getDetailsById(actorId: Int) {
        _actorDetails.postValue(State.Loading)
        collectResponse(movieRepository.getActorDetails(actorId)) {
            _actorDetails.postValue(it)
        }
    }

    fun getActorMoviesById(actorId: Int) {
        _actorMovies.postValue(State.Loading)
        collectResponse(movieRepository.getActorMovies(actorId)) {
            _actorMovies.postValue(it)
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

    override fun onClickMovie(movieId: Int) {}
}