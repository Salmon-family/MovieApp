package com.karrar.movieapp.ui.actors


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.ui.home.adapters.ActorsInteractionListener
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActorsViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel(), ActorsInteractionListener {

    val trendingActors = movieRepository.getTrendingActors().asLiveData()

    private val _clickActorEvent = MutableLiveData<Event<Int>>()
    val clickActorEvent = _clickActorEvent.toLiveData()

    override fun onClickActor(actorID: Int) {
        _clickActorEvent.postEvent(actorID)
    }
}