package com.karrar.movieapp.home

import androidx.lifecycle.MutableLiveData
import com.karrar.movieapp.base.BaseViewModel
import com.karrar.movieapp.data.Movie

class HomeViewModel : BaseViewModel() {

    val data2 = mutableListOf(
        Movie("Test 1", ""),
        Movie("Test 2", ""),
        Movie("Test 3", ""),
        Movie("Test 4", ""),
        Movie("Test 5", ""),
        Movie("Test 6", "")
    )

    val data = MutableLiveData<List<String>>()
    private val list = mutableListOf<String>()

    init {
        for (i in 0..10)
            list.add("TEST $i")
        data.postValue( list)
    }

}