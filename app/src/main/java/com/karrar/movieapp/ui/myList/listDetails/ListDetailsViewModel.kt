package com.karrar.movieapp.ui.myList.listDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.SaveListDetails
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    saveStateHandle: SavedStateHandle
) : BaseViewModel(), ListDetailsInteractionListener {

    private val args = ListDetailsFragmentArgs.fromSavedStateHandle(saveStateHandle)

    val listDetails = MutableLiveData<UIState<List<SaveListDetails>>>()
    private val _itemId = MutableLiveData<Event<Int>>()
    val itemId: LiveData<Event<Int>>
        get() = _itemId

    private val _mediaType = MutableLiveData<Event<String>>()
    val mediaType: LiveData<Event<String>>
        get() = _mediaType

    init {
        getData()
    }

    override fun getData() {
        listDetails.postValue(UIState.Loading)
        viewModelScope.launch {
            wrapWithState({
                listDetails.postValue(UIState.Success(movieRepository.getSavedListDetails(args.id.toString())))
            }, {
                listDetails.postValue(UIState.Error(it.message.toString()))
            })
        }
    }

    override fun onItemClick(item: SaveListDetails) {
        _mediaType.postValue(Event(item.mediaType!!))
        _itemId.postValue(Event(item.id))
    }
}

