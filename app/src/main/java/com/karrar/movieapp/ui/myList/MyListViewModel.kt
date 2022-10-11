package com.karrar.movieapp.ui.myList

import androidx.lifecycle.MutableLiveData
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.CreatedList
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.login.toLiveData
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val accountRepository: AccountRepository,
) : BaseViewModel(), CreatedListInteractionListener {

    private val _createdList = MutableLiveData<MutableList<CreatedList>?>()
    val createdList = _createdList.toLiveData()



    val listName = MutableLiveData("")

    private val _isCreateButtonClicked = MutableLiveData<Event<Boolean>>()
    val isButtonClicked = _isCreateButtonClicked.toLiveData()

    private val _onCLickAddEvent = MutableLiveData<Event<Boolean>>()
    val onClickAddEvent = _onCLickAddEvent.toLiveData()


    init {
        initCreatedList()
    }

    private fun initCreatedList() {
        collectResponse(
            accountRepository.getSessionId().flatMapLatest {
                movieRepository.getAllLists(0, it.toString())
            }

        ) { items ->
            items.toData()?.let {
                _createdList.postValue(it.toMutableList())

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
        _createdList.value?.add(0,createdLists)
    }
}