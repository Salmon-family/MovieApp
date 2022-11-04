package com.thechance.movie.ui.myList.listDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.thechance.movie.ui.base.BaseViewModel
import com.thechance.movie.ui.category.uiState.ErrorUIState
import com.thechance.movie.ui.myList.listDetails.listDetailsUIState.ListDetailsUIEvent
import com.thechance.movie.ui.myList.listDetails.listDetailsUIState.ListDetailsUIState
import com.thechance.movie.ui.myList.listDetails.listDetailsUIState.SavedMediaUIState
import com.thechance.movie.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListDetailsViewModel @Inject constructor(
    private val getMyMediaListDetailsUseCase: com.devfalah.usecases.mylist.GetMyMediaListDetailsUseCase,
    private val mediaUIStateMapper: MediaUIStateMapper,
    saveStateHandle: SavedStateHandle
) : BaseViewModel(), ListDetailsInteractionListener {

    val args = ListDetailsFragmentArgs.fromSavedStateHandle(saveStateHandle)

    private val _listDetailsUIState = MutableStateFlow(ListDetailsUIState())
    val listDetailsUIState = _listDetailsUIState.asStateFlow()

    private val _listDetailsUIEvent = MutableStateFlow<Event<ListDetailsUIEvent?>>(Event(null))
    val listDetailsUIEvent = _listDetailsUIEvent.asStateFlow()

    init {
        getData()
    }

    override fun getData() {
        _listDetailsUIState.update {
            it.copy(isLoading = true, isEmpty = false, error = emptyList())
        }
        viewModelScope.launch {
            try {
                val result =
                    getMyMediaListDetailsUseCase(args.id).map { mediaUIStateMapper.map(it) }
                _listDetailsUIState.update {
                    it.copy(
                        isLoading = false,
                        isEmpty = result.isEmpty(),
                        savedMedia = result
                    )
                }

            } catch (t: Throwable) {
                _listDetailsUIState.update {
                    it.copy(
                        isLoading = false, error = listOf(
                            ErrorUIState(0, t.message.toString())
                        )
                    )
                }
            }
        }
    }

    override fun onItemClick(item: SavedMediaUIState) {
        _listDetailsUIEvent.update { Event(ListDetailsUIEvent.OnItemSelected(item)) }
    }

}

