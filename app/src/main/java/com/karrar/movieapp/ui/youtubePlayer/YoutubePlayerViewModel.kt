package com.karrar.movieapp.ui.youtubePlayer

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.domain.models.Trailer
import com.karrar.movieapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class YoutubePlayerViewModel @Inject constructor(private val movieRepository: MovieRepository
):BaseViewModel() {

    private var _movieTrailer = MutableLiveData<State<Trailer>>()
    val movieTrailer : LiveData<State<Trailer>> = _movieTrailer


    fun getMovieTrailer(movie_id:Int){
        collectResponse(movieRepository.getMovieTrailer(movie_id)) {
            _movieTrailer.postValue(it)
        }
    }


}