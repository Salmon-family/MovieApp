package com.karrar.movieapp.ui.myList

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.SaveListDetails
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    saveStateHandle: SavedStateHandle
) : ViewModel(), ListDetailsInteractionListener {
    private val args = ListDetailsFragmentArgs.fromSavedStateHandle(saveStateHandle)

    init {
        getListDetailsById(args.id.toString())
    }

    val listDetails = MutableLiveData<State<List<SaveListDetails>>>()
    private val _itemId = MutableLiveData<Event<Int>>()
    val itemId: LiveData<Event<Int>>
        get() = _itemId

    private fun getListDetailsById(id: String) {
        collectResponse(
            movieRepository.getSavedListDetails(id)
        ) {
            listDetails.postValue(it)
        }
    }

    override fun onShowListItems(item: SaveListDetails) {
        _itemId.postValue(Event(item.id!!))
    }

    private fun <T> collectResponse(flow: Flow<State<T>>, function: (State<T>) -> Unit) {
        viewModelScope.launch {
            flow.flowOn(Dispatchers.IO)
                .collect { state ->
                    function(state)
                }
        }
    }
}

