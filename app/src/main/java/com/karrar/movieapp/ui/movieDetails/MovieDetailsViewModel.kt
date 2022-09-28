package com.karrar.movieapp.ui.movieDetails

import androidx.lifecycle.ViewModel
import com.karrar.movieapp.data.remote.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel


@HiltViewModel
class MovieDetailsViewModel(private val movieRepository: MovieRepository) : ViewModel(){

}