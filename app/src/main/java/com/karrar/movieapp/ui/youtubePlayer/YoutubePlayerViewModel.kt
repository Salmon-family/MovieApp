package com.karrar.movieapp.ui.youtubePlayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.models.Trailer
import com.karrar.movieapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class YoutubePlayerViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
    state: SavedStateHandle
) : BaseViewModel() {

    private val args = YoutubePlayerActivityArgs.fromSavedStateHandle(state)

    private var _movieTrailer = MutableLiveData<State<Trailer>>()
    val movieTrailer: LiveData<State<Trailer>> = _movieTrailer

    init {
        getMovieTrailer(args.movieId)
    }

    private fun getMovieTrailer(movie_id: Int) {

        viewModelScope.launch {
            when (args.type) {
                MediaType.MOVIE -> movieRepository.getMovieTrailer(movie_id).collect {
                    _movieTrailer.postValue(it)
                }
                MediaType.TV_SHOW -> seriesRepository.getTvShowTrailer(movie_id).collect {
                    _movieTrailer.postValue(it)
                }
            }
        }
    }

}