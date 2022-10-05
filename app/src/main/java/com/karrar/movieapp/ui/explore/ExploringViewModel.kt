package com.karrar.movieapp.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ExploringViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel(){
    private val _clickSearchEvent = MutableLiveData<Event<Boolean>>()
    var clickSearchEvent: LiveData<Event<Boolean>> = _clickSearchEvent

    val searchText = MutableStateFlow("")

    fun onClickSearch(){
        _clickSearchEvent.postValue(Event(true))
    }
}