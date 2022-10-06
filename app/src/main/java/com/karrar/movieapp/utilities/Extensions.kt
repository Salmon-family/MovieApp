package com.karrar.movieapp.utilities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.remote.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

fun <T> MutableLiveData<T>.toLiveData(): LiveData<T> {
    return this
}
fun <T> MutableLiveData<Event<T>>.postEvent(content: T) {
    postValue(Event(content))
}

