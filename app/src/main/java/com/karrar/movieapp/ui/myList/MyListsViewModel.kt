package com.karrar.movieapp.ui.myList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.CreatedList
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyListsViewModel @Inject constructor(
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

    private val _item = MutableLiveData<Event<CreatedList>>()
    val item: LiveData<Event<CreatedList>>
        get() = _item


    init {
        getData()
    }

    override fun getData() {
        wrapWithState({
            val sessionId = accountRepository.getSessionId()
            sessionId?.let {
                _createdList.postValue(UIState.Loading)
                val response = movieRepository.getAllLists(0, it).toMutableList()
                _createdList.postValue(UIState.Success(response))
            } ?: _createdList.postValue(UIState.Error("Error login"))

        }, {
            _createdList.postValue(UIState.Error(it.message.toString()))
        })
    }

    fun onCreateList() {
        _isCreateButtonClicked.postEvent(true)
    }

    fun onClickAddList() {
        wrapWithState({
           val sessionId = accountRepository.getSessionId()
            sessionId?.let {
                val item = movieRepository.createList(it, listName.value.toString())
                if (item.success == true)
                    addList(CreatedList(item.listId ?: 0, 0, listName.value.toString()))
                listName.postValue(null)
            }
        })
        _onCLickAddEvent.postEvent(true)
    }

    private fun addList(createdLists: CreatedList) {
        val oldList = _createdList.value?.toData()?.toMutableList()
        oldList?.add(0, createdLists)
        _createdList.postValue(UIState.Success(oldList))

    }

    override fun onListClick(item: CreatedList) {
        _item.postValue(Event(item))
    }
}