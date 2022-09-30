package com.karrar.movieapp.ui.actorDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.utilities.Constants.ACTOR_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActorViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel(), MovieInteractionListener {

    var actorDetails = movieRepository.getActorDetails(ACTOR_ID).asLiveData()
    var actorMovies = movieRepository.getMovieDetails(ACTOR_ID).asLiveData()

    init {
        Log.i("TAG", "ActorViewModel: ${actorDetails.value}")
    }

    override fun onClickMovie(actorId: Int) {}
}