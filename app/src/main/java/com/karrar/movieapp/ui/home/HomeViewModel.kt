package com.karrar.movieapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karrar.movieapp.data.test.Category
import com.karrar.movieapp.data.test.Movie
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.ui.home.adapters.BannerInteractionListener
import com.karrar.movieapp.ui.home.adapters.CategoryInteractionListener
import com.karrar.movieapp.ui.home.adapters.MovieInteractionListener
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel(), CategoryInteractionListener, MovieInteractionListener, BannerInteractionListener {

    val data2 = MutableLiveData<List<Movie>>()

    val data = MutableLiveData<List<Category>>()

    private val list = mutableListOf<Category>()

    private val _clickItemEvent = MutableLiveData<Event<Int>>()
    var clickItemEvent: LiveData<Event<Int>> = _clickItemEvent

    fun onClickMovie() {
        _clickItemEvent.postValue(Event(760161))
    }

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

    override fun onClickCategory(name: String) {
        Log.e("TEST", name)
    }

    override fun onClickMovie(name: String) {
        Log.e("TEST", name)
    }

    fun seeAllCategory() {
        Log.e("TEST", "All category")
    }

    fun seeAllMovie() {
        Log.e("TEST", "All Movie")
    }

    override fun onClickBanner(name: String) {
        Log.e("TEST", "All banner")
    }
}