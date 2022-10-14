package com.karrar.movieapp.ui.youtubePlayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.models.Trailer
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YoutubePlayerViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
    state: SavedStateHandle
) : BaseViewModel() {

    private val args = YoutubePlayerActivityArgs.fromSavedStateHandle(state)

    private var _movieTrailer = MutableLiveData<UIState<Trailer>>()
    val movieTrailer: LiveData<UIState<Trailer>> = _movieTrailer

    init {
        getMovieTrailer(args.movieId)
    }

    private fun getMovieTrailer(movie_id: Int) {
        _movieTrailer.postValue(UIState.Loading)
        wrapWithState({
            when (args.type) {
                MediaType.MOVIE -> {
                    val response = movieRepository.getMovieTrailer(movie_id)
                    _movieTrailer.postValue(UIState.Success(response))
                }
                MediaType.TV_SHOW -> {
                    val response = seriesRepository.getTvShowTrailer(movie_id)
                    _movieTrailer.postValue(UIState.Success(response))
                }
            }
        }, {})
    }

    override fun getData() {
        TODO("Not yet implemented")
    }

}