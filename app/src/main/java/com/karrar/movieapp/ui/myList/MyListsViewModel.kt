package com.karrar.movieapp.ui.myList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.usecase.mylist.CreateMovieListUseCase
import com.karrar.movieapp.domain.usecase.mylist.GetMyListUseCase
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
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

    val listName = MutableLiveData("")

    private val _isCreateButtonClicked = MutableLiveData<Event<Boolean>>()
    val isButtonClicked = _isCreateButtonClicked.toLiveData()

    private val _onCLickAddEvent = MutableLiveData<Event<Boolean>>()
    val onClickAddEvent = _onCLickAddEvent.toLiveData()

    private val _onSelectItem = MutableLiveData<Event<CreatedListUI>>()
    val onSelectItem = _onSelectItem.toLiveData()

    override fun getData() {
        _createdListUIState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val list = getMyListUseCase().map { createdListUIMapper.map(it) }
                _createdListUIState.update {
                    it.copy(isLoading = false, createdList = list, error = "")
                }
            } catch (t: Throwable) {
                _createdListUIState.update {
                    it.copy(isLoading = false, error = t.message.toString())
                }
            }
        }
    }

    fun checkIfLogin() {
        getData()
    }

    fun onCreateList() {
        _isCreateButtonClicked.postEvent(true)
    }

    fun onClickAddList() {
        _createdListUIState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                _createdListUIState.update {
                    it.copy(
                        isLoading = false,
                        createdList = createMovieListUseCase(listName.value.toString())
                            .map { createdListUIMapper.map(it) },
                        error = ""
                    )
                }
            } catch (t: Throwable) {
                _createdListUIState.update {
                    it.copy(isLoading = false, error = t.message.toString())
                }
            }
            listName.postValue(null)
        }
        _onCLickAddEvent.postEvent(true)
    }

    override fun onListClick(itemID: Int) {
        val item = _createdListUIState.value.createdList.find { it.listID == itemID }
        item?.let { _onSelectItem.postValue(Event(it)) }
    }

}