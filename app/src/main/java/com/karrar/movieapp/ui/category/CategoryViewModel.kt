package com.karrar.movieapp.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.mappers.MediaDataSourceContainer
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Constants.FIRST_CATEGORY_ID
import com.karrar.movieapp.utilities.Constants.MOVIE_CATEGORIES_ID
import com.karrar.movieapp.utilities.Constants.TV_CATEGORIES_ID
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
    private val mediaDataSourceContainer: MediaDataSourceContainer,
    state: SavedStateHandle
) : BaseViewModel(), MediaInteractionListener, CategoryInteractionListener {

    val args = CategoryFragmentArgs.fromSavedStateHandle(state)

    private val _categories = MutableLiveData<List<Genre>>()
    val categories: LiveData<List<Genre>> = _categories

    private val _allMediaState = MutableLiveData<UIState<Boolean>>(UIState.Loading)
    val allMediaState = _allMediaState.toLiveData()

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    var clickMovieEvent: LiveData<Event<Int>> = _clickMovieEvent

    private val _clickRetryEvent = MutableLiveData<Event<Boolean>>()
    val clickRetryEvent = _clickRetryEvent.toLiveData()

    private val _clickCategoryEvent = MutableLiveData<Int>()
    val clickCategoryEvent = _clickCategoryEvent.toLiveData()


    init {
        getData()
    }

    override fun getData() {
        getDataWithID(-1)
        _clickRetryEvent.postEvent(true)
    }

    fun getDataWithID(id: Int) {
        setCategoryType()
        setAllMediaList(id)
    }

    fun setAllMediaList(genre: Int): Flow<PagingData<Media>> {
        return if (genre == -1) {
            movieRepository.getMovieByGenre(12)
        } else {
            movieRepository.getMovieByGenre(genre)
        }
    }

    private fun setMediaList(id: Int) {
        when (args.mediaId) {
            MOVIE_CATEGORIES_ID -> {
                _clickCategoryEvent.postValue(id)
                setAllMediaList(id)
            }
            TV_CATEGORIES_ID -> {
//                val response = seriesRepository.getTvShowsByGenreID(id)
//                viewModelScope.launch {
//                    allMedia = movieRepository.getMediaData().flow
//                }
            }
        }
    }

    private fun setCategoryType() {
        wrapWithState({
            when (args.mediaId) {
                MOVIE_CATEGORIES_ID -> {
                    val response = movieRepository.getMovieGenreList()
                    _categories.postValue(response)
                }
                TV_CATEGORIES_ID -> {
                    val response = seriesRepository.getTVShowsGenreList()
                    _categories.postValue(response)
                }
            }
        })
    }

    override fun onClickMedia(mediaId: Int) {
        _clickMovieEvent.postValue(Event(mediaId))
    }

    override fun onClickCategory(categoryId: Int) {
        when (categoryId) {
            FIRST_CATEGORY_ID -> setAllMediaList(-1)
            else -> setMediaList(categoryId)
        }
    }

    fun setErrorUiState(loadState: LoadState) {
        when (loadState) {
            is LoadState.Error, null -> _allMediaState.postValue(UIState.Error(""))
            else -> {
                _allMediaState.postValue(UIState.Success(true))
            }
        }
    }
}
