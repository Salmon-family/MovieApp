package com.karrar.movieapp.ui.movieDetails.saveMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.checkIfExist
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SaveMovieViewModel @Inject constructor(private val movieRepository: MovieRepository
): BaseViewModel(), SaveListInteractionListener{

    var list = movieRepository.getAllLists(14012083,
            "1d92e6a329c67e2e5e0486a0a93d5980711535b1").asLiveData()

    private val _clickListEvent = MutableLiveData<Event<Int>>()
    var clickListEvent: LiveData<Event<Int>> = _clickListEvent

    private val _message = MutableLiveData<String>()
    var message: LiveData<String> = _message


    fun checkMovie(movie_id: Int) {
        collectResponse(movieRepository
            .getListDetails(_clickListEvent.value!!.peekContent())) {
            if(it.toData()?.checkIfExist(movie_id) == true) _message.postValue("Fail: this movie is already on the list")
            if(it.toData()?.checkIfExist(movie_id) == false) addMovieToList(movie_id)
        }
    }


    private fun addMovieToList(movie_id: Int){
        collectResponse(movieRepository.addMovieToList(
            "1d92e6a329c67e2e5e0486a0a93d5980711535b1",
            _clickListEvent.value?.peekContent() ?: 0,
            movie_id
        )){
            _message.postValue("Susses: The movie has been added")
        }

    }


    override fun onClickList(list_id: Int) {
        _clickListEvent.postValue(Event(list_id))
    }


}