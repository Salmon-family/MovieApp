package com.karrar.movieapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karrar.movieapp.data.test.Category
import com.karrar.movieapp.data.test.Movie
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.ui.home.adapters.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel(), CategoryInteractionListener, MovieInteractionListener, PopularMovieInteractionListener,
    ActorInteractionListener,AiringTodayInteractionListener {

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

    fun seeAllActors(){

    }

    override fun onClickPopularMovie(movieId: Int) {

    }

    override fun onClickActor(actorID: Int) {

    }

    override fun onClickAiringToday(airingTodayID: Int) {
    }

}