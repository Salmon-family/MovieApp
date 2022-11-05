package com.thechance.viewmodel.myList.listDetails

import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.mylist.GetMyMediaListDetailsUseCase
import com.thechance.ui.utilities.Event
import com.thechance.viewmodel.BaseViewModel
import com.thechance.viewmodel.category.com.thechance.viewmodel.myList.listDetails.ListDetailsInteractionListener
import com.thechance.viewmodel.category.uiState.ErrorUIState
import com.thechance.viewmodel.myList.listDetails.listDetailsUIState.ListDetailsUIEvent
import com.thechance.viewmodel.myList.listDetails.listDetailsUIState.ListDetailsUIState
import com.thechance.viewmodel.myList.listDetails.listDetailsUIState.SavedMediaUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListDetailsViewModel @Inject constructor(
    private val getMyMediaListDetailsUseCase: GetMyMediaListDetailsUseCase,
    private val mediaUIStateMapper: MediaUIStateMapper,
) : BaseViewModel(), ListDetailsInteractionListener {

    private val _listDetailsUIState = MutableStateFlow(ListDetailsUIState())
    val listDetailsUIState = _listDetailsUIState.asStateFlow()

    private val _listDetailsUIEvent = MutableStateFlow<Event<ListDetailsUIEvent?>>(Event(null))
    val listDetailsUIEvent = _listDetailsUIEvent.asStateFlow()

    private var listID = -1

    fun setListID(listID: Int) {
        this.listID = listID
        getData()
    }

    override fun getData() {
        _listDetailsUIState.update {
            it.copy(isLoading = true, isEmpty = false, error = emptyList())
        }
        viewModelScope.launch {
            try {
                val result =
                    getMyMediaListDetailsUseCase(listID).map { mediaUIStateMapper.map(it) }
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

