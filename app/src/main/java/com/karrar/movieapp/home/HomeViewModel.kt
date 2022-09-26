package com.karrar.movieapp.home

import androidx.lifecycle.MutableLiveData
import com.karrar.movieapp.base.BaseViewModel
import com.karrar.movieapp.data.Category
import com.karrar.movieapp.data.Movie

class HomeViewModel : BaseViewModel() {

    val data2 = MutableLiveData<List<Movie>>()

    val data = MutableLiveData<List<Category>>()

    private val list = mutableListOf<Category>()

    init {
        for (i in 0..10)
            list.add(Category("TEST $i", i))
        val movies = mutableListOf(
            Movie("Test 1", ""),
            Movie("Test 2", ""),
            Movie("Test 3", ""),
            Movie("Test 4", ""),
            Movie("Test 5", ""),
            Movie("Test 6", "")
        )
        data2.postValue(movies)
        data.postValue(list)
    }

}