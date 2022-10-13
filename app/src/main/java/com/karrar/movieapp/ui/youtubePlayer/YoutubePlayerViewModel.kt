package com.karrar.movieapp.ui.youtubePlayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.models.Trailer
import com.karrar.movieapp.ui.UIState
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

    private var _movieTrailer = MutableLiveData<UIState<Trailer>>()
    val movieTrailer: LiveData<UIState<Trailer>> = _movieTrailer
    

    init {
        getMovieTrailer(args.movieId)
    }

    private fun getMovieTrailer(movie_id: Int) {
        _movieTrailer.postValue(UIState.Loading)
        viewModelScope.launch {
            when (args.type) {
                MediaType.MOVIE -> wrapWithState({
                    val response = movieRepository.getMovieTrailer(movie_id)
                    _movieTrailer.postValue(UIState.Success(response))
                }, {_movieTrailer.postValue(UIState.Error(""))})
                MediaType.TV_SHOW -> wrapWithState({
                    val response = seriesRepository.getTvShowTrailer(movie_id)
                    _movieTrailer.postValue(UIState.Success(response))
                }, { _movieTrailer.postValue(UIState.Error("")) })
            }
        }
    }

}