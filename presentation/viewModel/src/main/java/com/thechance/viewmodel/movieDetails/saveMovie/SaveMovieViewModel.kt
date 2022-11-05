package com.thechance.viewmodel.movieDetails.saveMovie

import androidx.lifecycle.viewModelScope
import com.thechance.ui.utilities.Event
import com.thechance.viewmodel.BaseViewModel
import com.thechance.viewmodel.category.uiState.ErrorUIState
import com.thechance.viewmodel.movieDetails.saveMovie.uiState.MySavedListUIState
import com.thechance.viewmodel.movieDetails.saveMovie.uiState.SaveMovieUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class SaveMovieViewModel @Inject constructor(
    private val saveMovieToMyListUseCase: com.devfalah.usecases.mylist.SaveMovieToMyListUseCase,
    private val getMyListUseCase: com.devfalah.usecases.mylist.GetMyListUseCase,
    private val myListItemUIStateMapper: MyListItemUIStateMapper,
) : BaseViewModel(), SaveListInteractionListener {

//    private val args = SaveMovieDialogArgs.fromSavedStateHandle(state)

    private val _myListsUIState = MutableStateFlow(MySavedListUIState())
    val myListsUIState = _myListsUIState.asStateFlow()

    private val _saveMovieUIEvent = MutableStateFlow<Event<SaveMovieUIEvent?>>(Event(null))
    val saveMovieUIEvent = _saveMovieUIEvent.asStateFlow()

    private var movie by Delegates.notNull<Int>()


    fun setMovieID(movieID: Int) {
        movie = movieID
        getData()

    }

    override fun getData() {
        viewModelScope.launch {
            _myListsUIState.update { it.copy(isLoading = true, error = emptyList()) }
            try {
                _myListsUIState.update {
                    it.copy(
                        isLoading = false,
                        myListItemUI = getMyListUseCase().map { myListItemUIStateMapper.map(it) }
                    )
                }
            } catch (t: Throwable) {
                _myListsUIState.update {
                    it.copy(
                        isLoading = false,
                        error = listOf(ErrorUIState(404, t.message.toString()))
                    )
                }
            }
        }
    }

    override fun onClickSaveList(listID: Int) {
        viewModelScope.launch {
            val message = try {
                saveMovieToMyListUseCase(listID, movie)
            } catch (t: Throwable) {
                t.message.toString()
            }
            _saveMovieUIEvent.update { Event(SaveMovieUIEvent.DisplayMessage(message ?: "")) }
        }
    }
}