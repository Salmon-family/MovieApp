package com.thechance.viewmodel.actors

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.map
import com.thechance.ui.utilities.Event
import com.thechance.viewmodel.BaseViewModel
import com.thechance.viewmodel.actors.models.ActorsUIState
import com.thechance.viewmodel.mappers.ActorUiMapper
import com.thechance.viewmodel.movieDetails.ActorsInteractionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorsViewModel @Inject constructor(
    private val getActorsDataUseCase: com.devfalah.usecases.GetActorsDataUseCase,
    private val actorMapper: ActorUiMapper
) : BaseViewModel(), ActorsInteractionListener {

    private val _uiState = MutableStateFlow(ActorsUIState())
    val uiState = _uiState.asStateFlow()

    private val _actorsUIEventFlow: MutableStateFlow<Event<ActorsUIEvent>?> = MutableStateFlow(null)
    val actorsUIEventFlow = _actorsUIEventFlow.asStateFlow()

    init {
        getData()
    }

    override fun getData() {
        _uiState.update { it.copy(isLoading = true) }
        getActors()
        _actorsUIEventFlow.update { Event(ActorsUIEvent.RetryEvent) }
    }

    private fun getActors() {
        viewModelScope.launch {
            val actorsItems =
                getActorsDataUseCase().map { pager -> pager.map { actorMapper.map(it) } }
            _uiState.update {
                it.copy(
                    isLoading = false,
                    actors = actorsItems,
                    error = emptyList()
                )
            }
        }
    }

    override fun onClickActor(actorID: Int) {
        _actorsUIEventFlow.update { Event(ActorsUIEvent.ActorEvent(actorID)) }
    }

    fun setErrorUiState(combinedLoadStates: CombinedLoadStates) {
        when (combinedLoadStates.refresh) {
            is LoadState.NotLoading -> {
                _uiState.update {
                    it.copy(isLoading = false, error = emptyList())
                }
            }
            LoadState.Loading -> {
                _uiState.update {
                    it.copy(isLoading = true, error = emptyList())
                }
            }
            is LoadState.Error -> {
                _uiState.update {
                    it.copy(isLoading = false, error = listOf(Error("")))
                }
            }
        }
    }

}