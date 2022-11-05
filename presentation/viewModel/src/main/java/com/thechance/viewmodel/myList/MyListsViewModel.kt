package com.thechance.viewmodel.myList

import androidx.lifecycle.viewModelScope
import com.thechance.ui.utilities.ErrorUI.INTERNET_CONNECTION
import com.thechance.ui.utilities.ErrorUI.NEED_LOGIN
import com.thechance.ui.utilities.ErrorUI.NO_LOGIN
import com.thechance.ui.utilities.Event
import com.thechance.viewmodel.BaseViewModel
import com.thechance.viewmodel.category.uiState.ErrorUIState
import com.thechance.viewmodel.myList.myListUIState.CreateListDialogUIState
import com.thechance.viewmodel.myList.myListUIState.CreatedListUIState
import com.thechance.viewmodel.myList.myListUIState.MyListUIEvent
import com.thechance.viewmodel.myList.myListUIState.MyListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyListsViewModel @Inject constructor(
    private val createMovieListUseCase: com.devfalah.usecases.mylist.CreateMovieListUseCase,
    private val getMyListUseCase: com.devfalah.usecases.mylist.GetMyListUseCase,
    private val createdListUIMapper: CreatedListUIMapper,
) : BaseViewModel(), CreatedListInteractionListener {

    private val _createdListUIState = MutableStateFlow(MyListUIState())
    val createdListUIState = _createdListUIState.asStateFlow()

    private val _createListDialogUIState = MutableStateFlow(CreateListDialogUIState())
    val createListDialogUIState = _createListDialogUIState.asStateFlow()

    private val _myListUIEvent: MutableStateFlow<Event<MyListUIEvent?>> =
        MutableStateFlow(Event(null))
    val myListUIEvent = _myListUIEvent.asStateFlow()

    override fun getData() {
        _createdListUIState.update {
            it.copy(
                isLoading = true,
                isEmpty = false,
                error = emptyList()
            )
        }
        viewModelScope.launch {
            try {
                val list = getMyListUseCase().map { createdListUIMapper.map(it) }
                _createdListUIState.update {
                    it.copy(isLoading = false, isEmpty = list.isEmpty(), createdList = list)
                }
            } catch (t: Throwable) {
                setError(t)
            }
        }
    }

    fun onListNameInputChange(listName: CharSequence) {
        _createListDialogUIState.update { it.copy(mediaListName = listName.toString()) }
    }

    fun onCreateList() {
        _myListUIEvent.update { Event(MyListUIEvent.CreateButtonClicked) }
    }

    fun onClickAddList() {
        viewModelScope.launch {
            try {
                _createdListUIState.update {
                    it.copy(
                        isLoading = false,
                        createdList = createMovieListUseCase(_createListDialogUIState.value.mediaListName)
                            .map { createdListUIMapper.map(it) },
                        error = emptyList()
                    )
                }
            } catch (t: Throwable) {
                _myListUIEvent.update { Event(MyListUIEvent.DisplayError(t.message.toString())) }
            }
            _createListDialogUIState.update { it.copy(mediaListName = "") }
            _myListUIEvent.emit(Event(MyListUIEvent.CLickAddEvent))
        }
    }

    override fun onListClick(item: CreatedListUIState) {
        _myListUIEvent.update { Event(MyListUIEvent.OnSelectItem(item)) }
    }

    private fun setError(t: Throwable) {
        _createdListUIState.update {
            val error = if (t.message == NO_LOGIN) {
                listOf(ErrorUIState(NEED_LOGIN, t.message.toString()))
            } else {
                listOf(ErrorUIState(INTERNET_CONNECTION, t.message.toString()))
            }
            it.copy(isLoading = false, error = error)
        }
    }
}