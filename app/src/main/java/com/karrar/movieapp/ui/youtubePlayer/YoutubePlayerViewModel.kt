package com.karrar.movieapp.ui.youtubePlayer

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.response.trailerVideosDto.ResultDto
import com.karrar.movieapp.domain.models.Trailer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YoutubePlayerViewModel @Inject constructor(private val movieRepository: MovieRepository
):ViewModel() {

    private var _movieTrailer = MutableLiveData<State<Trailer>>()
    val movieTrailer : LiveData<State<Trailer>> = _movieTrailer

    val hhh = "_uX6of3vBu0"

    fun getMovieTrailer(movie_id:Int){
        collectResponse(movieRepository.getMovieTrailer(movie_id)) {
            _movieTrailer.postValue(it)
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