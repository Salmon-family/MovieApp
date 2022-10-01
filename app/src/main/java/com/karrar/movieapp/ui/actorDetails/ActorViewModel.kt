package com.karrar.movieapp.ui.actorDetails

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.domain.models.Movie
import com.karrar.movieapp.utilities.Constants.ACTOR_ID
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel(), MovieInteractionListener {

    var actorDetails = movieRepository.getActorDetails(ACTOR_ID).asLiveData()
    var actorMovies = movieRepository.getMovieDetails(ACTOR_ID).asLiveData()

    private val _backEvent = MutableLiveData<Event<Boolean>>()
    val backEvent: LiveData<Event<Boolean>> = _backEvent


    fun onClickBack() {
        _backEvent.postValue(Event(true))
    }

    override fun onClickMovie(movieId: Int) {}
}