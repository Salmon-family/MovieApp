package com.karrar.movieapp.ui.saved

import android.util.Log
import androidx.lifecycle.*
import com.karrar.movieapp.data.local.database.entity.Lists
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.DataBaseRepositoryImp
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateListDialogViewModel @Inject constructor(
    private val dataBaseRepositoryImp: DataBaseRepositoryImp,
    private val movieRepository: MovieRepository,
    private val accountRepository: AccountRepository,
) : ViewModel() {

    val listName = MutableLiveData("")
    private val _isPrivacyChecked = MutableLiveData<Boolean>()

    private val _onCLickAddEvent = MutableLiveData(Event(false))
    val onClickAddEvent: LiveData<Event<Boolean>>
        get() = _onCLickAddEvent

    fun onClickAddList() {
        addList()
        collectResponse(
            accountRepository.getSessionId().flatMapLatest { sessionId ->
                movieRepository.createList(
                    sessionId.toString(),
                    name = listName.value.toString(),
                    description = "",
                    public = _isPrivacyChecked.value == true
                )
            }
        ) {
            Log.d("my_test", it.toString())

        }
        _onCLickAddEvent.postValue(Event(true))
    }

    fun <T> collectResponse(flow: Flow<State<T>>, function: (State<T>) -> Unit) {
        viewModelScope.launch {
            flow.flowOn(Dispatchers.IO)
                .collect { state ->
                    function(state)
                }
        }
    }

    private fun addList() {
        viewModelScope.launch {
            listName.value?.let { text ->
                _isPrivacyChecked.value?.let {
                    Lists(
                        listId = 0, itemCount = 0,
                        listName = text, isPublic = it, posterPath = ""
                    )
                }
                    ?.let { dataBaseRepositoryImp.insertList(it) }

            }
        }
    }

    fun selectOption(value: Int) {
        if (value == 1)
            _isPrivacyChecked.postValue(true)
        else
            _isPrivacyChecked.postValue(false)
    }
}