package com.karrar.movieapp.ui.actors


import androidx.lifecycle.MutableLiveData
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.map
import com.karrar.movieapp.domain.usecase.GetActorsDataUseCase
import com.karrar.movieapp.ui.actors.models.ActorsUIState
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ActorsViewModel @Inject constructor(
    private val getActorsDataUseCase: GetActorsDataUseCase,
    private val actorMapper: ActorMapper
) : BaseViewModel(), ActorsInteractionListener {

    private val _uiState = MutableStateFlow(ActorsUIState())
    val uiState = _uiState.asStateFlow()

    private val _clickActorEvent = MutableLiveData<Event<Int>>()
    val clickActorEvent = _clickActorEvent.toLiveData()

    private val _clickRetryEvent = MutableLiveData<Event<Boolean>>()
    val clickRetryEvent = _clickRetryEvent.toLiveData()

    init {
        getActors()
    }

    private fun getActors(){
        _uiState.update { it.copy(isLoading = true) }
        val actorsItems = getActorsDataUseCase().map { pager -> pager.map { actorMapper.map(it) } }
        _uiState.update { it.copy(isLoading = false, actors = actorsItems) }
    }

    override fun getData() {
        _clickRetryEvent.postEvent(true)
    }


    override fun onClickActor(actorID: Int) {
        _clickActorEvent.postEvent(actorID)
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