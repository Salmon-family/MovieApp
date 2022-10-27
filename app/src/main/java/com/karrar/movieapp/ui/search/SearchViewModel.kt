package com.karrar.movieapp.ui.search

import androidx.lifecycle.*
import androidx.paging.*
import com.karrar.movieapp.domain.explorUsecase.GetSearchUseCase
import com.karrar.movieapp.ui.allMedia.Error
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.search.adapters.*
import com.karrar.movieapp.ui.search.mediaSearchUIState.MediaSearchUIState
import com.karrar.movieapp.ui.search.mediaSearchUIState.MediaTypes
import com.karrar.movieapp.ui.search.mediaSearchUIState.MediaUIState
import com.karrar.movieapp.utilities.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
) : BaseViewModel(), MediaSearchInteractionListener, ActorSearchInteractionListener,
    SearchHistoryInteractionListener {

    private val _uiState = MutableStateFlow(MediaSearchUIState())
    val uiState = _uiState.toStateFlow()

    private val _clickMediaEvent = MutableLiveData<Event<MediaUIState>>()
    val clickMediaEvent = _clickMediaEvent.toLiveData()

    private val _clickActorEvent = MutableLiveData<Event<Int>>()
    val clickActorEvent = _clickActorEvent.toLiveData()

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    val clickBackEvent = _clickBackEvent.toLiveData()

    private val _clickRetryEvent = MutableLiveData<Event<Boolean>>()
    val clickRetryEvent = _clickRetryEvent.toLiveData()

    private val _mediaType = MutableLiveData<Event<MediaTypes>>(Event(MediaTypes.MOVIE))
    val mediaType = _mediaType.toLiveData()

    val searchText = MutableStateFlow("")


    init {
        getAllSearchHistory()
    }

    override fun getData() {
        _clickRetryEvent.postEvent(true)
    }

    private fun getAllSearchHistory() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                getSearchUseCase().collect { list ->
                    _uiState.update {
                        it.copy(searchHistory = list.map { item -> item.toSearchHistory() }, isLoading = false, isEmpty = false)
                    }
                }
            } catch (e:Throwable) {
                _uiState.update {
                    it.copy(error = listOf(Error(0,e.message.toString())))
                }
            }
        }
    }

    fun onSearchInputChange(searchTerm: CharSequence) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            _uiState.update { it.copy(searchInput = searchTerm.toString(), isLoading = false) }
            updateSearchResultUIState()
            searchText.emit(searchTerm.toString())
        }
    }

    fun onSelectMediaType(type: MediaTypes) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            _uiState.update { it.copy(searchTypes = type, isLoading = false) }
            updateSearchResultUIState()
            _mediaType.postValue(Event(type))
        }
    }

    private suspend fun updateSearchResultUIState(){
        _uiState.update {
            it.copy(searchResult= getSearchUseCase.getSearchResult(it.searchTypes, it.searchInput).map { pagingData ->
                pagingData.map { item -> item.toSearchResult() } })
        }
    }


    override fun onClickMediaResult(media: MediaUIState) {
        saveSearchResult(media.mediaID, media.mediaName)
        _clickMediaEvent.postEvent(media)
    }

    override fun onClickActorResult(personID: Int, name: String) {
        saveSearchResult(personID, name)
        _clickActorEvent.postEvent(personID)
    }

    private fun saveSearchResult(id: Int, name: String) {
        viewModelScope.launch { getSearchUseCase.saveSearchResult(id, name) }
    }

    override fun onClickSearchHistory(name: String) {
        onSearchInputChange(name)
    }

    fun onClickBack() {
        _clickBackEvent.postEvent(true)
    }

    fun setErrorUiState(combinedLoadStates: CombinedLoadStates, itemCount: Int) {
        when (combinedLoadStates.refresh) {
            is LoadState.Loading -> {
                _uiState.update {
                    it.copy(isLoading = true, error = emptyList(), isEmpty = false)
                }
            }
            is LoadState.Error -> {
                _uiState.update {
                    it.copy(isLoading = false, error = listOf(Error(404, "")), isEmpty = false)
                }
            }
            is LoadState.NotLoading -> {
                if( itemCount < 1){
                    _uiState.update { it.copy(isEmpty = true, isLoading = false, error = emptyList()) }
                }else{
                    _uiState.update { it.copy(isEmpty = false , isLoading = false, error = emptyList()) }
                }
            }
        }
    }

 }