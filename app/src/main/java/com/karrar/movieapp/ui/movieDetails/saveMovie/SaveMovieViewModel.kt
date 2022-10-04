package com.karrar.movieapp.ui.movieDetails.saveMovie

import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SaveMovieViewModel @Inject constructor(private val movieRepository: MovieRepository
): BaseViewModel(){


}