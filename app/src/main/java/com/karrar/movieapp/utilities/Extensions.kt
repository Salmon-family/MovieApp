package com.karrar.movieapp.utilities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.toLiveData(): LiveData<T> {
    return this
}
fun <T> MutableLiveData<Event<T>>.postEvent(content: T) {
    postValue(Event(content))
}