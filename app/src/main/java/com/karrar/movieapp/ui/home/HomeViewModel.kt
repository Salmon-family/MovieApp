package com.karrar.movieapp.ui.home

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.Type
import com.karrar.movieapp.ui.home.adapters.*
import com.karrar.movieapp.utilities.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository
) : ViewModel(), MovieInteractionListener,
    PopularMovieInteractionListener,
    ActorInteractionListener, AiringTodayInteractionListener {

    val popularMovie = movieRepository.getPopularMovies().asLiveData()
    val popularMoviePosition = MutableLiveData<Int>()

    val trending = movieRepository.getTrendingMovies().asLiveData()
    val trendingPosition = MutableLiveData<Int>()

    val nowStreaming = movieRepository.getNowPlayingMovies().asLiveData()
    val nowStreamingPosition = MutableLiveData<Int>()

    val upcoming = movieRepository.getUpcomingMovies().asLiveData()
    val upcomingPosition = MutableLiveData<Int>()

    val mysteryMovie = movieRepository.getMovieListByGenre(Constants.MYSTERY_ID).asLiveData()
    val mysteryMoviePosition = MutableLiveData<Int>()

    val adventureMovie = movieRepository.getMovieListByGenre(Constants.ADVENTURE_ID).asLiveData()
    val adventureMoviePosition = MutableLiveData<Int>()

    val onTheAiring = seriesRepository.getOnTheAir().asLiveData()
    val onTheAiringPosition = MutableLiveData<Int>()

    val actors = movieRepository.getTrendingPersons().asLiveData()
    val airingToday = seriesRepository.getAiringToday().asLiveData()
    val topRatedTvShow = seriesRepository.getTopRatedTvShow().asLiveData()
    val latestTvShow = seriesRepository.getLatestTvShow().asLiveData()
    val popularTvShow = seriesRepository.getPopularTvShow().asLiveData()

    val homeAdapter =
        listOf(
            HorizontalAdapter<PopularMovieAdapter>(Type.PopularMovieType, this),
            HorizontalAdapter<MovieAdapter>(Type.TvShowType, this),
            HorizontalAdapter<MovieAdapter>(Type.OnTheAirType, this),
            HorizontalAdapter<MovieAdapter>(Type.TrendingMovieType, this),
            HorizontalAdapter<AiringTodayAdapter>(Type.AiringTodayType, this),
            HorizontalAdapter<MovieAdapter>(Type.NowStreaming, this),
            HorizontalAdapter<MovieAdapter>(Type.Upcoming, this),
            HorizontalAdapter<MovieAdapter>(Type.MYSTERY_TYPE, this),
            HorizontalAdapter<MovieAdapter>(Type.ADVENTURE_TYPE, this),
            HorizontalAdapter<ActorAdapter>(Type.ActorType, this)
        )


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
        addSource(mysteryMovie, this@HomeViewModel::updateData)
        addSource(adventureMovie, this@HomeViewModel::updateData)
    }

    private fun <T> updateData(value: State<T>) {
        if (
            popularMovie.value is State.Success &&
            trending.value is State.Success &&
            nowStreaming.value is State.Success &&
            upcoming.value is State.Success &&
            mysteryMovie.value is State.Success &&
            adventureMovie.value is State.Success &&
            actors.value is State.Success &&
            airingToday.value is State.Success &&
            topRatedTvShow.value is State.Success &&
            latestTvShow.value is State.Success &&
            popularTvShow.value is State.Success

        ) {
            Log.i("testRecyeler", "before state")
            updatingRecycler.postValue(true)
        }
    }

    override fun onClickMovie(movieID: Int, type: Type) {
    }

    fun seeAllMovie(types: Type) {
    }

    fun seeAllActors() {
    }

    override fun onClickPopularMovie(movieId: Int) {
    }

    override fun onClickActor(actorID: Int) {
    }

    override fun onClickAiringToday(airingTodayID: Int) {
    }

}