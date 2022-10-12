package com.karrar.movieapp.ui.myList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.CreatedList
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.login.toLiveData
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val accountRepository: AccountRepository,
) : BaseViewModel(), CreatedListInteractionListener {

    private val _createdList = MutableLiveData<UIState<MutableList<CreatedList>?>>()
    val createdList = _createdList.toLiveData()

    val listName = MutableLiveData("")

    private val _isCreateButtonClicked = MutableLiveData<Event<Boolean>>()
    val isButtonClicked = _isCreateButtonClicked.toLiveData()

    private val _onCLickAddEvent = MutableLiveData<Event<Boolean>>()
    val onClickAddEvent = _onCLickAddEvent.toLiveData()

    private val _itemId = MutableLiveData<Event<Int>>()
    val itemId: LiveData<Event<Int>>
        get() = _itemId


    init {
        initCreatedList()
    }

    private fun initCreatedList() {
        viewModelScope.launch {
            accountRepository.getSessionId().collectLatest {
                wrapWithState({
                    Log.d("sessionId :",it.toString())
                    val response = movieRepository.getAllLists(0, it.toString()).toMutableList()
                    _createdList.postValue(UIState.Success(response))
                })
            }
        }
    }


    fun onCreateList() {
        _isCreateButtonClicked.postEvent(true)
    }


    fun onClickAddList() {
        collectResponse(accountRepository.getSessionId().flatMapLatest {
            movieRepository.createList(it.toString(), listName.value.toString())
        }) {
            it.toData()?.let { item ->
                if (item.success == true)
                    addList(CreatedList(item.listId ?: 0, 0, listName.value.toString()))
                listName.postValue(null)
            }


        }
        _onCLickAddEvent.postEvent(true)
    }

    private fun addList(createdLists: CreatedList) {
        val oldList = _createdList.value?.toData()?.toMutableList()
        oldList?.add(0, createdLists)
        _createdList.postValue(UIState.Success(oldList))

    }

    override fun onShowListItems(item: CreatedList) {
        _itemId.postValue(Event(item.id!!))
    }
}