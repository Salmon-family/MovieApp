package com.karrar.movieapp.ui.youtubePlayer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.models.Trailer
import com.karrar.movieapp.domain.usecase.GetMovieTrailerUseCase
import com.karrar.movieapp.domain.usecase.GetSeriesTrailerUseCase
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YoutubePlayerViewModel @Inject constructor(
    private val getMovieTrailerUseCase: GetMovieTrailerUseCase,
    private val getSeriesTrailerUseCase: GetSeriesTrailerUseCase,
    state: SavedStateHandle
) : BaseViewModel() {

    private val args = YoutubePlayerActivityArgs.fromSavedStateHandle(state)

    private var _movieTrailer = MutableLiveData<UIState<Trailer>>()
    val movieTrailer = _movieTrailer.toLiveData()

    init {
        getData()
    }

    override fun getData() {
        _movieTrailer.postValue(UIState.Loading)
        wrapWithState({
            when (args.type) {
                MediaType.MOVIE -> {
                    val response = getMovieTrailerUseCase(args.movieId)
                    _movieTrailer.postValue(UIState.Success(response))
                }
                MediaType.TV_SHOW -> {
                    val response = getSeriesTrailerUseCase(args.movieId)
                    _movieTrailer.postValue(UIState.Success(response))
                }
            }
        }, {
            _movieTrailer.postValue(UIState.Error(""))
        })
    }

}