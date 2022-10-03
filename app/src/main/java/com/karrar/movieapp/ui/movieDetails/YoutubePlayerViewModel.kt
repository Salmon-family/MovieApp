package com.karrar.movieapp.ui.movieDetails

import androidx.lifecycle.ViewModel
import com.karrar.movieapp.data.remote.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YoutubePlayerViewModel @Inject constructor(private val movieRepository: MovieRepository
):ViewModel() {


}