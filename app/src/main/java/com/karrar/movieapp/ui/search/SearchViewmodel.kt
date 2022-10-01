package com.karrar.movieapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.SeriesRepository
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.home.adapters.MovieInteractionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewmodel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel(), MediaInteractionListener{
    private val _media = MutableLiveData<State<List<Media>>>()
    val media: LiveData<State<List<Media>>> get() = _media

    fun getMedia(query: String){
        viewModelScope.launch {
            movieRepository.getMedia(query).debounce(1000).flowOn(Dispatchers.IO).collect{
                _media.postValue(it)
            }
        }
    }

    override fun onClickMedia(mediaID: Int) {

    }
}