package com.karrar.movieapp.ui.actorDetails

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActorViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel(), MovieInteractionListener {

    var actorDetails = movieRepository.getActorDetails(257).asLiveData()
    var actorMovies = movieRepository.getMovieDetails(257).asLiveData()

    private val _backEvent = MutableLiveData<Event<Boolean>>()
    val backEvent: LiveData<Event<Boolean>> = _backEvent


    fun onClickBack() {
        _backEvent.postValue(Event(true))
    }

    override fun onClickMovie(movieId: Int) {}
}