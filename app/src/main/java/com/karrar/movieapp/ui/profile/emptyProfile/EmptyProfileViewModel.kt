package com.karrar.movieapp.ui.profile.emptyProfile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmptyProfileViewModel @Inject constructor() :
    ViewModel() {

    private val _clickLogin = MutableLiveData<Event<Boolean>>()
    val clickLogin = _clickLogin.toLiveData()

    fun logoutEvent() {
        _clickLogin.postEvent(true)

    }
}