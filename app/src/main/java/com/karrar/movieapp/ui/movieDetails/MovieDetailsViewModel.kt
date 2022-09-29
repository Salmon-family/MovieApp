package com.karrar.movieapp.ui.movieDetails

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.domain.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val movieRepository: MovieRepository
): ViewModel(), CastInteractionListener, MovieInteractionListener{

     var movieDetails = movieRepository.getMovieDetails(760161).asLiveData()
     var movieCast = movieRepository.getMovieCast(760161).asLiveData()
     var similarMovie = movieRepository.getSimilarMovie(760161).asLiveData()



     override fun onClickCast(cast_id: Int) { }
     override fun onClickMovie(cast_id: Int) { }

}