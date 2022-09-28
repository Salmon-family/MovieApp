package com.karrar.movieapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.domain.enums.Type
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Movie
import com.karrar.movieapp.domain.models.PopularMovie
import com.karrar.movieapp.ui.home.adapters.*
import com.karrar.movieapp.utilities.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel(), GenreInteractionListener, MovieInteractionListener,
    PopularMovieInteractionListener,
    ActorInteractionListener, AiringTodayInteractionListener {

    val popularMovie = MutableLiveData<List<PopularMovie>>()
    val airingToday = MutableLiveData<List<Movie>>()
    val trending = MutableLiveData<List<Movie>>()
    val genre = MutableLiveData<List<Genre>>()
    val nowStreaming = MutableLiveData<List<Movie>>()
    val upcoming = MutableLiveData<List<Movie>>()
    val actors = MutableLiveData<List<Actor>>()

    init {
        //TEST DATA
        val popularMovieItem = PopularMovie(
            760161,
            "Orphan: First Kill",
            Constants.IMAGE_BASE_PATH + "/5GA3vV1aWWHTSDO5eno8V5zDo8r.jpg",
            7.3
        )
        popularMovie.postValue(mutableListOf(popularMovieItem, popularMovieItem, popularMovieItem))

        val movie = Movie(
            760161,
            Constants.IMAGE_BASE_PATH + "/g8sclIV4gj1TZqUpnL82hKOTK3B.jpg",
        )

        //************** Trending************\\
        trending.postValue(
            mutableListOf(movie, movie, movie, movie, movie, movie, movie, movie, movie, movie)
        )
        //*************AIR Today****************\\
        airingToday.postValue(mutableListOf(movie, movie, movie, movie, movie, movie))
        //************** Genre *******************]]
        val genreItem = Genre(28, "Action")
        genre.postValue(mutableListOf(genreItem, genreItem, genreItem, genreItem))

        //**************** nowStreaming*****************\\
        nowStreaming.postValue(mutableListOf(movie, movie, movie, movie, movie, movie))
        //***************** upcoming *************\\
        upcoming.postValue(mutableListOf(movie, movie, movie, movie, movie, movie))
        //**************** Actor **************\\
        val actor = Actor(55089, Constants.IMAGE_BASE_PATH + "/zxfMLydSWz5OaZp5EvGTBsuYmpt.jpg")
        actors.postValue(mutableListOf(actor,actor,actor,actor,actor,actor,actor,actor))
    }


    override fun onClickMovie(movieID: Int, type: Type) {
        Log.e("TEST", "$movieID")
    }

    fun seeAllCategory() {
        Log.e("TEST", "All category")
    }

    fun seeAllMovie(types: Type) {
        when (types) {
            Type.TrendingMovieType -> {

            }
            Type.OnTheAirType -> {

            }
            else -> {}
        }
        Log.e("TEST", "All Movie")
    }


    fun seeAllActors() {

    }

    override fun onClickPopularMovie(movieId: Int) {

    }

    override fun onClickActor(actorID: Int) {

    }

    override fun onClickAiringToday(airingTodayID: Int) {
    }

    override fun onClickGenre(genreID: Int) {

    }

}