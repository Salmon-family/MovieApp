package com.karrar.movieapp.ui.category.temp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karrar.movieapp.utilities.Constants.MOVIE_CATEGORIES_ID
import com.karrar.movieapp.utilities.Constants.TV_CATEGORIES_ID
import com.karrar.movieapp.utilities.Event

class TempViewModel : ViewModel() {

    private val _moviesEvent = MutableLiveData<Event<Int>>()
    val moviesEvent: LiveData<Event<Int>> = _moviesEvent

    private val _tvEvent = MutableLiveData<Event<Int>>()
    val tvEvent: LiveData<Event<Int>> = _tvEvent

    fun onClickMoviesButton() {
        _moviesEvent.postValue(Event(MOVIE_CATEGORIES_ID))
    }

    fun onClickTvButton() {
        _tvEvent.postValue(Event(TV_CATEGORIES_ID))
    }

}