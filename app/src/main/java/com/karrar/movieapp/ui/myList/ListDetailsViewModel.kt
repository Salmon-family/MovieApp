package com.karrar.movieapp.ui.myList

import android.util.Log
import androidx.lifecycle.*
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
    val mediaType : LiveData<Event<String>>
    get() = _mediaType

    init {
        getListDetailsById(args.id.toString())
    }

    private fun getListDetailsById(id: String) {
        listDetails.postValue(UIState.Loading)
         viewModelScope.launch{
             wrapWithState({
                 listDetails.postValue(UIState.Success(movieRepository.getSavedListDetails(id)))
             },{
                 listDetails.postValue(UIState.Error(it.message.toString()))
             })
          }
    }

    override fun onItemClick(item: SaveListDetails) {
        _mediaType.postValue(Event(item.mediaType!!))
        _itemId.postValue(Event(item.id))
    }

}

