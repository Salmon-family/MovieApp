package com.karrar.movieapp.ui.search

import androidx.lifecycle.*
import androidx.paging.*
import com.karrar.movieapp.domain.usecases.searchUseCase.*
import com.karrar.movieapp.ui.allMedia.Error
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.search.adapters.*
import com.karrar.movieapp.ui.search.mediaSearchUIState.*
import com.karrar.movieapp.ui.search.uiStatMapper.*
import com.karrar.movieapp.utilities.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchHistoryUIStateMapper: SearchHistoryUIStateMapper,
    private val searchMediaUIStateMapper: SearchMediaUIStateMapper,
    private val getSearchForMovieUseCase: GetSearchForMovieUseCase,
    private val getSearchForSeriesUserCase: GetSearchForSeriesUserCase,
    private val getSearchForActorUseCase: GetSearchForActorUseCase,
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
    private val postSaveSearchResultUseCase: PostSaveSearchResultUseCase
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
                getSearchHistoryUseCase().collect { list ->
                    _uiState.update {
                        it.copy(searchHistory = list.map { item -> searchHistoryUIStateMapper.map(item) }, isLoading = false, isEmpty = false)
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
        _uiState.update { it.copy(searchInput = searchTerm.toString(), isLoading = true) }
        viewModelScope.launch {
            when(_uiState.value.searchTypes){
                MediaTypes.MOVIE -> onSearchForMovie()
                MediaTypes.TVS_SHOW -> onSearchForSeries()
                MediaTypes.ACTOR -> onSearchForActor()
            }
        }
    }


    fun onSearchForMovie(){
        viewModelScope.launch {
            _uiState.update { it.copy(
                searchTypes = MediaTypes.MOVIE,
                isLoading = false,
                searchResult= getSearchForMovieUseCase(it.searchInput).map { pagingData ->
                    pagingData.map { item -> searchMediaUIStateMapper.map(item) } }
                ) }
        }
    }

    fun onSearchForSeries(){
        viewModelScope.launch {
            _uiState.update { it.copy(
                searchTypes = MediaTypes.TVS_SHOW,
                isLoading = false,
                searchResult= getSearchForSeriesUserCase(it.searchInput).map { pagingData ->
                    pagingData.map { item -> searchMediaUIStateMapper.map(item) } }
            ) }
        }
    }

    fun onSearchForActor(){
        viewModelScope.launch {
            _uiState.update { it.copy(
                searchTypes = MediaTypes.ACTOR,
                isLoading = false,
                searchResult= getSearchForActorUseCase(it.searchInput).map { pagingData ->
                    pagingData.map { item -> searchMediaUIStateMapper.map(item) } }
                ) }
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
        viewModelScope.launch { postSaveSearchResultUseCase(id, name) }
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