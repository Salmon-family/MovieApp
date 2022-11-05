package com.thechance.viewmodel.youtubePlayer

import androidx.lifecycle.viewModelScope
import com.devfalah.types.MediaType
import com.devfalah.usecases.GetTrailerUseCase
import com.thechance.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class YoutubePlayerViewModel @Inject constructor(
    private val getTrailerUseCase: GetTrailerUseCase,
) : BaseViewModel() {

    private val _trailerUIState = MutableStateFlow(TrailerUIState())
    val trailerUIState = _trailerUIState.asStateFlow()

    private lateinit var type: com.thechance.viewmodel.category.com.thechance.viewmodel.utilities.MediaType
    private var movieId by Delegates.notNull<Int>()

    fun setData(type: com.thechance.viewmodel.category.com.thechance.viewmodel.utilities.MediaType, movieId: Int) {
        this.type = type
        this.movieId = movieId
        getData()
    }

    override fun getData() {
        _trailerUIState.update { it.copy(isLoading = true, error = emptyList()) }
        viewModelScope.launch {
            try {
                val response = getTrailerUseCase(type.name, movieId)
                _trailerUIState.update { it.copy(isLoading = false, videoKey = response.videoKey) }
            } catch (t: Throwable) {
                _trailerUIState.update {
                    it.copy(
                        isLoading = false,
                        error = listOf(ErrorUIState(404, t.message.toString()))
                    )
                }
            }
        }
    }

}