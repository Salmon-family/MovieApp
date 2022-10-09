package com.karrar.movieapp.ui.movieDetails.saveMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.checkIfExist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SaveMovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val accountRepository: AccountRepository,
) : BaseViewModel(), SaveListInteractionListener {

    private val _sessionId = MutableLiveData<String?>()


    var list = movieRepository.getAllLists(
        14012083,
        _sessionId.value.toString()
    ).asLiveData()

    private val _clickListEvent = MutableLiveData<Event<Int>>()
    var clickListEvent: LiveData<Event<Int>> = _clickListEvent

    private val _message = MutableLiveData<String>()
    var message: LiveData<String> = _message


    init {
        getSessionId()
    }

    fun checkMovie(movie_id: Int) {
        collectResponse(
            movieRepository
                .getListDetails(_clickListEvent.value!!.peekContent())
        ) {
            if (it.toData()
                    ?.checkIfExist(movie_id) == true
            ) _message.postValue("Fail: this movie is already on the list")
            if (it.toData()?.checkIfExist(movie_id) == false) addMovieToList(movie_id)
        }
    }


    private fun addMovieToList(movie_id: Int) {
        collectResponse(
            movieRepository.addMovieToList(
                _sessionId.value.toString(),
                _clickListEvent.value?.peekContent() ?: 0,
                movie_id
            )
        ) {
            _message.postValue("Susses: The movie has been added")
        }

    }

    private fun getSessionId() {
        viewModelScope.launch {
            accountRepository.getSessionId().collect {
                _sessionId.value = it.toString()
            }
        }
    }

    override fun onClickList(list_id: Int) {
        _clickListEvent.postValue(Event(list_id))
    }


}