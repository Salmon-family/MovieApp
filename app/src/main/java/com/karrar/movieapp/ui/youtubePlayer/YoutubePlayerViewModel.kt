package com.karrar.movieapp.ui.youtubePlayer

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.response.trailerVideosDto.ResultDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YoutubePlayerViewModel @Inject constructor(private val movieRepository: MovieRepository
):ViewModel() {

    private var _movieTrailer = MutableLiveData<List<ResultDto>>()
    val movieTrailer : LiveData<List<ResultDto>> = _movieTrailer

    fun getMovieTrailer(movie_id:Int){
        collectResponse(movieRepository.getMovieTrailer(movie_id)) {
            _movieTrailer.postValue(it.toData()?.results!! as List<ResultDto>?)
        }
    }

    private fun <T> collectResponse(flow: Flow<State<T>>, function: (State<T>) -> Unit) {
        viewModelScope.launch {
            flow.flowOn(Dispatchers.IO)
                .collect { state ->
                    function(state)
                }
        }
    }
}