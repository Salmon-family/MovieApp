package com.karrar.movieapp.ui.movieDetails.saveMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.CreatedList
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.checkIfExist
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SaveMovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val accountRepository: AccountRepository,
) : BaseViewModel(), SaveListInteractionListener {

     private val _savedList = MutableLiveData<State<List<CreatedList>>>()
    val savedList = _savedList.toLiveData()


    private val _clickListEvent = MutableLiveData<Event<Int>>()
    var clickListEvent: LiveData<Event<Int>> = _clickListEvent

    private val _message = MutableLiveData<String>()
    var message: LiveData<String> = _message

    init {
        viewModelScope.launch {
            accountRepository.getSessionId().flatMapLatest {
                movieRepository.getAllLists(0, it.toString())
            }.collect {
                _savedList.postValue(it)
            }
        }

    }

    fun checkMovie(movieId: Int) {
        collectResponse(movieRepository
            .getListDetails(_clickListEvent.value!!.peekContent())) {
            if (it.toData()
                    ?.checkIfExist(movieId) == true
            ) _message.postValue("Fail: this movie is already on the list")
            if (it.toData()?.checkIfExist(movieId) == false) addMovieToList(movieId)
        }
    }


    private fun addMovieToList(movieId: Int) {

        viewModelScope.launch {
            accountRepository.getSessionId().flatMapLatest {
                movieRepository.addMovieToList(
                    it.toString(),
                    _clickListEvent.value?.peekContent() ?: 0,
                    movieId
                )
            }.collect {
                _message.postValue("Susses: The movie has been added")

            }
        }

    }


    override fun onClickList(listId: Int) {
        _clickListEvent.postValue(Event(listId))
    }


}