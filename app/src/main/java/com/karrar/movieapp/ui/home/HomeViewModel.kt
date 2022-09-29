package com.karrar.movieapp.ui.home

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.Type
import com.karrar.movieapp.ui.home.adapters.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Named("MovieRepository") private val movieRepository: MovieRepository,
    @Named("SeriesRepository") private val seriesRepository: SeriesRepository
) : ViewModel(), GenreInteractionListener, MovieInteractionListener,
    PopularMovieInteractionListener, SeriesInteractionListener,
    ActorInteractionListener, AiringTodayInteractionListener {

    val popularMovie = movieRepository.getPopularMovies().asLiveData()
    val trending = movieRepository.getTrendingMovies().asLiveData()
    val nowStreaming = movieRepository.getNowPlayingMovies().asLiveData()
    val upcoming = movieRepository.getUpcomingMovies().asLiveData()
    val actors = movieRepository.getTrendingPersons().asLiveData()

    val genre = movieRepository.getGenreList().asLiveData()


    val onTheAiring = seriesRepository.getOnTheAir().asLiveData()
    val airingToday = seriesRepository.getAiringToday().asLiveData()
    val topRatedTvShow = seriesRepository.getTopRatedTvShow().asLiveData()
    val latestTvShow = seriesRepository.getLatestTvShow().asLiveData()
    val popularTvShow = seriesRepository.getPopularTvShow().asLiveData()

    val updatingRecycler = MediatorLiveData<Boolean>().apply {
        addSource(popularMovie, this@HomeViewModel::updateData)
        addSource(trending, this@HomeViewModel::updateData)
        addSource(nowStreaming, this@HomeViewModel::updateData)
        addSource(upcoming, this@HomeViewModel::updateData)
        addSource(actors, this@HomeViewModel::updateData)
        addSource(onTheAiring, this@HomeViewModel::updateData)
        addSource(airingToday, this@HomeViewModel::updateData)
        addSource(topRatedTvShow, this@HomeViewModel::updateData)
        addSource(latestTvShow, this@HomeViewModel::updateData)
        addSource(popularTvShow, this@HomeViewModel::updateData)
        addSource(genre, this@HomeViewModel::updateData)
    }

    private fun updateData(value: Any) {
        updatingRecycler.postValue(true)
    }

    override fun onClickMovie(movieID: Int, type: Type) {
        Log.e("TEST", "$movieID")
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

    override fun onClickSeries(seriesID: Int) {

    }


}