package com.karrar.movieapp.ui.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.local.database.entity.Lists
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.DataBaseRepositoryImp
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.CreatedLists
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class AllSavedListsViewModel @Inject constructor(
    private val dataBaseRepositoryImp: DataBaseRepositoryImp,
    private val movieRepository: MovieRepository,
    private val accountRepository: AccountRepository,
) : ViewModel(), SavedListInteractionListener {


      private val accountId = accountRepository.getSessionId().flatMapLatest { sessionId ->
        accountRepository.getAccountId(sessionId.toString())
    }.asLiveData()

    private val yourAccount: Int = accountId.value?.toData()?.id ?: 0


//    val list = accountRepository.getSessionId().flatMapLatest { sessionId ->
//        movieRepository.getAllLists(
//            yourAccount, /*sessionId ?: ""*/
//            "1d92e6a329c67e2e5e0486a0a93d5980711535b1"
//        )
//    }.asLiveData()

    val list = dataBaseRepositoryImp.getAllSavedLists().asLiveData()

    private val _isCreateButtonClicked = MutableLiveData(Event(false))
    val isButtonClicked: LiveData<Event<Boolean>>
        get() = _isCreateButtonClicked

    private val _isEmptyList = MutableLiveData(false)
    val isEmptyList: LiveData<Boolean>
        get() = _isEmptyList

    private val _itemId = MutableLiveData<Event<Int>>()
    val itemId: LiveData<Event<Int>>
        get() = _itemId

    fun onCreateList() {
        _isCreateButtonClicked.postValue(Event(true))
    }

    override fun onShowListItems(item: Lists) {
        _itemId.postValue(Event(item.listId!!))
    }

}