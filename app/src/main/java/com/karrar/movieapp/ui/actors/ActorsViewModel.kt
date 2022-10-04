package com.karrar.movieapp.ui.actors


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.ui.actors.adapters.ActorsInteractionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActorsViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel(), ActorsInteractionListener {
    val trendingActors = movieRepository.getTrendingActors().asLiveData()

    override fun onClickActor(actorID: Int) {
    }
}