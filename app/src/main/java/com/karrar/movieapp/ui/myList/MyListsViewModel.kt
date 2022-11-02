package com.karrar.movieapp.ui.myList

import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.usecases.mylist.CreateMovieListUseCase
import com.karrar.movieapp.domain.usecases.mylist.GetMyListUseCase
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.category.uiState.ErrorUIState
import com.karrar.movieapp.ui.myList.myListUIState.CreateListDialogUIState
import com.karrar.movieapp.ui.myList.myListUIState.CreatedListUIState
import com.karrar.movieapp.ui.myList.myListUIState.MyListUIEvent
import com.karrar.movieapp.ui.myList.myListUIState.MyListUIState
import com.karrar.movieapp.utilities.ErrorUI.INTERNET_CONNECTION
import com.karrar.movieapp.utilities.ErrorUI.NEED_LOGIN
import com.karrar.movieapp.utilities.ErrorUI.NO_LOGIN
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyListsViewModel @Inject constructor(
    private val createMovieListUseCase: CreateMovieListUseCase,
    private val getMyListUseCase: GetMyListUseCase,
    private val createdListUIMapper: CreatedListUIMapper,
) : BaseViewModel(), CreatedListInteractionListener {

    private val _createdListUIState = MutableStateFlow(MyListUIState())
    val createdListUIState = _createdListUIState.asStateFlow()

    private val _createListDialogUIState = MutableStateFlow(CreateListDialogUIState())
    val createListDialogUIState = _createListDialogUIState.asStateFlow()

    val myListUIEvent: MutableStateFlow<Event<MyListUIEvent>?> = MutableStateFlow(null)

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
        myListUIEvent.update { Event(MyListUIEvent.CreateButtonClicked) }
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
                myListUIEvent.update { Event(MyListUIEvent.DisplayError(t.message.toString())) }
            }
            _createListDialogUIState.update { it.copy(mediaListName = "") }
        }
//        myListUIEvent.update { Event(MyListUIEvent.CLickAddEvent) }
    }

    override fun onListClick(item: CreatedListUIState) {
        myListUIEvent.update { Event(MyListUIEvent.OnSelectItem(item)) }
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