package com.karrar.movieapp.ui.profile

import androidx.lifecycle.ViewModel
import com.karrar.movieapp.data.remote.repository.MovieRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
}