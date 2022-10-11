package com.karrar.movieapp.ui.actors


import androidx.lifecycle.MutableLiveData
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActorsViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    BaseViewModel(), ActorsInteractionListener {

    private val _clickActorEvent = MutableLiveData<Event<Int>>()
    val clickActorEvent = _clickActorEvent.toLiveData()

    private val _trendingActors = MutableLiveData<UIState<List<Actor>>>()
    val trendingActors = _trendingActors.toLiveData()

    init {
        getActors()
    }

    private fun getActors() {
        _trendingActors.postValue(UIState.Loading)
        wrapWithState({
            val result = movieRepository.getTrendingActors()
            _trendingActors.postValue(UIState.Success(result))
        }, {
            _trendingActors.postValue(UIState.Error)
        })
    }

    override fun onClickActor(actorID: Int) {
        _clickActorEvent.postEvent(actorID)
    }
}