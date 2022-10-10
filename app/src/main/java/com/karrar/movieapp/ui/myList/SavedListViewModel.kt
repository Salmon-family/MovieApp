package com.karrar.movieapp.ui.myList

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.SaveListDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel(), ListDetailsInteractionListener {
    private val _listDetailsPublic = MutableLiveData<State<List<SaveListDetails>>>()
    val listDetailsPublic: LiveData<State<List<SaveListDetails>>>
        get() = _listDetailsPublic

    fun getListDetailsById(id: String) {
        movieRepository.getSavedListDetails(id).asLiveData()
        _listDetailsPublic.postValue(State.Loading)
        viewModelScope.launch {
            movieRepository.getSavedListDetails(id).collect {
                _listDetailsPublic.postValue(it)
            }
        }
    }


    override fun onShowListItems(item: SaveListDetails) {
    }

}