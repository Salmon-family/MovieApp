package com.karrar.movieapp.ui.actorDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.domain.models.ActorDetails
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.MovieInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActorViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val state: SavedStateHandle,
) : BaseViewModel(), MovieInteractionListener {

    private val args = ActorDetailsFragmentArgs.fromSavedStateHandle(state)
    val actorId = args.id

    private val _actorDetails = MutableLiveData<UIState<ActorDetails>>()
    val actorDetails = _actorDetails.toLiveData()

    private val _actorMovies = MutableLiveData<UIState<List<Media?>>>()
    val actorMovies = _actorMovies.toLiveData()

    private val _backEvent = MutableLiveData<Event<Boolean>>()
    val backEvent = _backEvent.toLiveData()

    private val _seeAllMovies = MutableLiveData<Event<Boolean>>()
    val seeAllMovies = _seeAllMovies.toLiveData()

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    init {
        getActorDetails()
    }

    private fun getActorDetails() {
        _actorDetails.postValue(UIState.Loading)
        wrapWithState({
            val result = movieRepository.getActorDetails(actorId)
            _actorDetails.postValue(UIState.Success(result))
        }, {
            _actorDetails.postValue(UIState.Error(it.message.toString()))
        })
        wrapWithState({
            val result = movieRepository.getActorMovies(actorId)
            _actorMovies.postValue(UIState.Success(result))
        }, {
            _actorMovies.postValue(UIState.Error(it.message.toString()))
        })
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

}