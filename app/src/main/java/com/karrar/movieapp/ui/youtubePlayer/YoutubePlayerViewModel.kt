package com.karrar.movieapp.ui.youtubePlayer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.models.Trailer
import com.karrar.movieapp.domain.usecases.GetTrailerUseCase
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YoutubePlayerViewModel @Inject constructor(
    private val getTrailerUseCase: GetTrailerUseCase,
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
        viewModelScope.launch {
            try {
                val response = getTrailerUseCase(args.type, args.movieId)
                _movieTrailer.postValue(UIState.Success(response))
            } catch (t: Throwable) {
                _movieTrailer.postValue(UIState.Error(""))
            }
        }
    }

}