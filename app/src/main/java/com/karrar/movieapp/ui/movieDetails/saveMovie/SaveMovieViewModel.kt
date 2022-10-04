package com.karrar.movieapp.ui.movieDetails.saveMovie

import androidx.lifecycle.MutableLiveData
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.response.ListDto
import com.karrar.movieapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SaveMovieViewModel @Inject constructor(private val movieRepository: MovieRepository
): BaseViewModel(), SaveListInteractionListener{

    var list = MutableLiveData<List<ListDto>>()

    init {

        var test = mutableListOf( ListDto(1, "action"),
            ListDto(1, "action"))
        list.postValue(test)

    }

    override fun onClickList(movie_id: Int) {  }


}