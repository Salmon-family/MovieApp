package com.karrar.movieapp.ui.actors


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel(), ActorsInteractionListener {

    lateinit var trendingActors: Flow<PagingData<Actor>>

    private val _actorsState = MutableLiveData<UIState<Boolean>>(UIState.Loading)
    val actorsState = _actorsState.toLiveData()

    private val _clickActorEvent = MutableLiveData<Event<Int>>()
    val clickActorEvent = _clickActorEvent.toLiveData()

    private val _clickRetryEvent = MutableLiveData<Event<Boolean>>()
    val clickRetryEvent = _clickRetryEvent.toLiveData()

    init {
        viewModelScope.launch {
            trendingActors = movieRepository.getActorData().flow.cachedIn(viewModelScope)
        }
    }

    override fun getData() {
        _clickRetryEvent.postEvent(true)
    }


    override fun onClickActor(actorID: Int) {
        _clickActorEvent.postEvent(actorID)
    }

    fun setErrorUiState(loadState: LoadState) {
        val result = if (loadState is LoadState.Error) {
            loadState.error.message ?: "Error"
        } else null

        if (!result.isNullOrBlank()) {
            _actorsState.postValue(UIState.Error(result))
        } else {
            _actorsState.postValue(UIState.Success(true))
        }
    }

}