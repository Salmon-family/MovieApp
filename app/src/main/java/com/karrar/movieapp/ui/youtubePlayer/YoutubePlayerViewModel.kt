package com.karrar.movieapp.ui.youtubePlayer

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.models.Trailer
import com.karrar.movieapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class YoutubePlayerViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
    state: SavedStateHandle
):BaseViewModel() {

    private val args = YoutubePlayerActivityArgs.fromSavedStateHandle(state)

    private var _movieTrailer = MutableLiveData<State<Trailer>>()
    val movieTrailer: LiveData<State<Trailer>> = _movieTrailer

    init {
        getMovieTrailer(args.movieId)
    }

    private fun getMovieTrailer(movie_id: Int) {
        when (args.type) {
            MediaType.MOVIE -> collectResponse(movieRepository.getMovieTrailer(movie_id)) {
                _movieTrailer.postValue(it)
            }
            MediaType.TV_SHOW -> collectResponse(seriesRepository.getTvShowTrailer(movie_id)) {
                _movieTrailer.postValue(it)
            }
        }
    }


}