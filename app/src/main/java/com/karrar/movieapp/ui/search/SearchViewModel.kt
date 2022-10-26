package com.karrar.movieapp.ui.search

import androidx.lifecycle.*
import androidx.paging.*
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.domain.usecase.GetSearchUseCase
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.search.adapters.*
import com.karrar.movieapp.utilities.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val getSearchUseCase: GetSearchUseCase

) : BaseViewModel(), MediaSearchInteractionListener, ActorSearchInteractionListener,
    SearchHistoryInteractionListener {

    private val _mediaState = MutableLiveData<UIState<Boolean>>()
    val mediaState = _mediaState.toLiveData()

    private val _searchHistory = MutableLiveData<List<SearchHistory>>()
    val searchHistory = _searchHistory.toLiveData()

    private val _clickMediaEvent = MutableLiveData<Event<Media>>()
    val clickMediaEvent = _clickMediaEvent.toLiveData()

    private val _clickActorEvent = MutableLiveData<Event<Int>>()
    val clickActorEvent = _clickActorEvent.toLiveData()

    private val _clickMoviesCategoryEvent = MutableLiveData<Event<Boolean>>()
    val clickMoviesCategoryEvent = _clickMoviesCategoryEvent.toLiveData()

    private val _clickSeriesCategoryEvent = MutableLiveData<Event<Boolean>>()
    val clickSeriesCategoryEvent = _clickSeriesCategoryEvent.toLiveData()

    private val _clickActorsCategoryEvent = MutableLiveData<Event<Boolean>>()
    val clickActorsCategoryEvent = _clickActorsCategoryEvent.toLiveData()

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    val clickBackEvent = _clickBackEvent.toLiveData()

    private val _clickRetryEvent = MutableLiveData<Event<Boolean>>()
    val clickRetryEvent = _clickRetryEvent.toLiveData()

    val searchText = MutableStateFlow("")
    val mediaType = MutableStateFlow(Constants.MOVIE)

    init {
        getAllSearchHistory()
    }

    private fun getAllSearchHistory() {
        viewModelScope.launch {
            getSearchUseCase().collect {
            _searchHistory.postValue(it)
            }
        }
    }

    override fun getData() {
        _clickRetryEvent.postEvent(true)
    }

    fun searchForActor(text: String): Flow<PagingData<Media>> {
        return movieRepository.searchForActor(text)
    }

    fun searchForMovie(text: String): Flow<PagingData<Media>> {
        return movieRepository.searchForMovie(text)
    }

    fun searchForSeries(text: String): Flow<PagingData<Media>> {
        return movieRepository.searchForSeries(text)
    }

    fun onClickMoviesCategory() {
        viewModelScope.launch {
            if (mediaType.value != Constants.MOVIE) {
                mediaType.emit(Constants.MOVIE)
                _clickMoviesCategoryEvent.postEvent(true)
            }
        }
    }

    fun onClickSeriesCategory() {
        viewModelScope.launch {
            if (mediaType.value != Constants.TV_SHOWS) {
                mediaType.emit(Constants.TV_SHOWS)
                _clickSeriesCategoryEvent.postEvent(true)
            }
        }
    }

    fun onClickActorsCategory() {
        viewModelScope.launch {
            if (mediaType.value != Constants.ACTOR) {
                mediaType.emit(Constants.ACTOR)
                _clickActorsCategoryEvent.postEvent(true)
            }
        }
    }

    override fun onClickMediaResult(media: Media) {
        saveSearchResult(media.mediaID, media.mediaName)
        _clickMediaEvent.postEvent(media)
    }

    override fun onClickActorResult(personID: Int, name: String) {
        saveSearchResult(personID, name)
        _clickActorEvent.postEvent(personID)
    }

    private fun saveSearchResult(id: Int, name: String) {
        viewModelScope.launch {
            movieRepository.insertSearchItem(
                SearchHistoryEntity(
                    id = id.toLong(),
                    search = name
                )
            )
        }
    }

    override fun onClickSearchHistory(name: String) {
        viewModelScope.launch {
            searchText.emit(name)
        }
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