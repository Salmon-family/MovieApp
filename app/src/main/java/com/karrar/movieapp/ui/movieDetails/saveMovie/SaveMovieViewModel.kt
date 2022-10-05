package com.karrar.movieapp.ui.movieDetails.saveMovie

import android.util.Log
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SaveMovieViewModel @Inject constructor(private val movieRepository: MovieRepository
): BaseViewModel(), SaveListInteractionListener{

    var list = movieRepository
        .getAllLists(14012083,
            "1d92e6a329c67e2e5e0486a0a93d5980711535b1"
        ).asLiveData()


    fun onClick(){
        collectResponse(movieRepository.createList(
            "1d92e6a329c67e2e5e0486a0a93d5980711535b1",
            "شهد",
            "shahad"
        )){
            Log.i("kkk", it.toString())
        }
    }

    override fun onClickList(movie_id: Int) {  }


}