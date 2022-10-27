package com.karrar.movieapp.ui.search

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.*
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.explorUsecase.GetSearchUseCase
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.search.adapters.*
import com.karrar.movieapp.utilities.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val getSearchUseCase: GetSearchUseCase

) : BaseViewModel(), MediaSearchInteractionListener, ActorSearchInteractionListener,
    SearchHistoryInteractionListener {

    private val _uiState = MutableStateFlow(MediaSearchUIState())
    val uiState = _uiState.toStateFlow()


    private val _mediaState = MutableLiveData<UIState<Boolean>>()
    val mediaState = _mediaState.toLiveData()

    private val _clickMediaEvent = MutableLiveData<Event<MediaUIState>>()
    val clickMediaEvent = _clickMediaEvent.toLiveData()

    private val _clickActorEvent = MutableLiveData<Event<Int>>()
    val clickActorEvent = _clickActorEvent.toLiveData()

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    val clickBackEvent = _clickBackEvent.toLiveData()

    private val _clickRetryEvent = MutableLiveData<Event<Boolean>>()
    val clickRetryEvent = _clickRetryEvent.toLiveData()


    init {
        getAllSearchHistory()
    }

    override fun getData() {
        _clickRetryEvent.postEvent(true)
    }

    private fun getAllSearchHistory() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getSearchUseCase().collect{ list ->
                _uiState.update { it.copy(searchHistory = list.map { item -> item.toSearchHistory() })
                } }
        }
    }

    fun onSearchInputChange(searchTerm: CharSequence) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            _uiState.update { it.copy(searchInput = searchTerm.toString()) }
            updateSearchResultUIState()
        }
    }

    fun onSelectMediaType(type: MediaTypes) {
        Log.i("sss", type.name)
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            _uiState.update { it.copy(searchTypes = type) }
            updateSearchResultUIState()
        }
    }

    private suspend fun updateSearchResultUIState(){
        _uiState.update {
            it.copy(searchResult= getSearchUseCase.getSearchResult(it.searchTypes, it.searchInput).map { pagingData ->
                pagingData.map { item -> item.toSearchResult() } })
        }
    }

    suspend fun onInternetDisconnect() {}


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
        _uiState.update { it.copy(searchInput = name) }
    }

    fun onClickBack() {
        _clickBackEvent.postEvent(true)
    }

    fun setUiState(loadState: LoadState, itemCount: Int) {
        when (loadState) {
            is LoadState.Loading -> _mediaState.postValue(UIState.Loading)
            is LoadState.Error -> _mediaState.postValue(UIState.Error(""))
            else -> {
                if(loadState is LoadState.NotLoading && itemCount < 1){
                    _mediaState.postValue(UIState.Success(false))
                }else{
                    _mediaState.postValue(UIState.Success(true))
                }
            }
        }
    }

}