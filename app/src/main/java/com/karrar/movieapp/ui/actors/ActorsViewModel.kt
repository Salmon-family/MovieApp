package com.karrar.movieapp.ui.actors


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.karrar.movieapp.data.repository.ActorDataSource
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ActorsViewModel @Inject constructor(
    private val dataSource: ActorDataSource
) : BaseViewModel(), ActorsInteractionListener {

    val trendingActors: Flow<PagingData<Actor>> =
        Pager(config = config, pagingSourceFactory = { dataSource }).flow.cachedIn(viewModelScope)

    private val _actorsState = MutableLiveData<UIState<Boolean>>(UIState.Loading)
    val actorsState = _actorsState.toLiveData()

    private val _clickActorEvent = MutableLiveData<Event<Int>>()
    val clickActorEvent = _clickActorEvent.toLiveData()

    override fun onClickActor(actorID: Int) {
        _clickActorEvent.postEvent(actorID)
    }

    override fun getData() {

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