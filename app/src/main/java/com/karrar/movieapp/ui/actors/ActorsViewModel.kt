package com.karrar.movieapp.ui.actors


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.domain.usecase.GetActorsDataUseCase
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorsViewModel @Inject constructor(
    private val getActorsDataUseCase: GetActorsDataUseCase
) : BaseViewModel(), ActorsInteractionListener {

    private val _uiState = MutableStateFlow(ActorsUIState())
    val uiState: StateFlow<ActorsUIState> = _uiState.asStateFlow()

    private val _clickActorEvent = MutableLiveData<Event<Int>>()
    val clickActorEvent = _clickActorEvent.toLiveData()

    private val _clickRetryEvent = MutableLiveData<Event<Boolean>>()
    val clickRetryEvent = _clickRetryEvent.toLiveData()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(actorsState = UIState.Loading) }
            val actorsResult = getActorsDataUseCase().flow
            _uiState.update { it.copy(actorsState = UIState.Success(true), actors = actorsResult) }
        }
    }

    override fun getData() {
        _clickRetryEvent.postEvent(true)
    }


    override fun onClickActor(actorID: Int) {
        _clickActorEvent.postEvent(actorID)
    }

    fun setUiState(loadState: LoadState) {
        when(loadState){
            is LoadState.Loading -> {_uiState.update { it.copy(actorsState = UIState.Loading) }}
            is LoadState.Error -> {
                _uiState.update { it.copy(actorsState = UIState.Error("")) }
            }
            else -> {
                _uiState.update { it.copy(actorsState = UIState.Success(true)) }
            }
        }
    }

}