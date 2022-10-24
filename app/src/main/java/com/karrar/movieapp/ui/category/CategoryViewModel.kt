package com.karrar.movieapp.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.usecase.GetCategoryUseCase
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Constants.FIRST_CATEGORY_ID
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


data class CategoryUIState(
    val genre: List<GenreUIState> = emptyList(),
    val media: Flow<PagingData<Media>> = emptyFlow(),// url for medias
    val isLoading: Boolean = false,
    val error: String = ""
)

data class GenreUIState(
    val name: String,
    val isSelected: Boolean
)

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    state: SavedStateHandle
) : BaseViewModel(), MediaInteractionListener, CategoryInteractionListener {

    val args = CategoryFragmentArgs.fromSavedStateHandle(state)

    private val _uiState = MutableStateFlow(CategoryUIState())
    val uiState: StateFlow<CategoryUIState> = _uiState

//    private val _categories = MutableLiveData<List<Genre>>()
//    val categories: LiveData<List<Genre>> = _categories

    private val _allMediaState = MutableLiveData<UIState<Boolean>>(UIState.Loading)
    val allMediaState = _allMediaState.toLiveData()

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    var clickMovieEvent = _clickMovieEvent

    private val _clickRetryEvent = MutableLiveData<Event<Boolean>>()
    val clickRetryEvent = _clickRetryEvent.toLiveData()

    private val _selectedCategory = MutableLiveData(FIRST_CATEGORY_ID)
    val selectedCategory = _selectedCategory.toLiveData()

    init {
        getData()
    }

    override fun getData() {
        setAllMediaList(FIRST_CATEGORY_ID)
        _clickRetryEvent.postEvent(true)
    }

    fun setAllMediaList(genre: Int) {
        _uiState.update { it.copy(isLoading = true) }
        val result = getCategoryUseCase(args.mediaId, genre)
        _uiState.update { it.copy(isLoading = false, media = result) }
    }

    // clicks ...
//    private fun setCategoryType() {
//        wrapWithState({
//            val response = when (args.mediaId) {
//                MOVIE_CATEGORIES_ID -> {
//                    movieRepository.getMovieGenreList()
//                }
//                TV_CATEGORIES_ID -> {
//                    seriesRepository.getTVShowsGenreList()
//                }
//                else -> {
//                    throw Throwable("There is no MOVIE_CATEGORIES_ID ")
//                }
//            }
//            _categories.postValue(response)
//        })
//    }

    override fun onClickMedia(mediaId: Int) {
        _clickMovieEvent.postValue(Event(mediaId))
    }

    override fun onClickCategory(categoryId: Int) {
        _selectedCategory.postValue(categoryId)
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

