package com.thechance.movie.ui.allMedia

import androidx.paging.PagingData
import com.thechance.movie.ui.models.MediaUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class AllMediaUiState(
    val allMedia : Flow<PagingData<MediaUiState>> = emptyFlow(),
    val isLoading : Boolean = false,
    val error : List<Error> = emptyList(),
    )


data class Error(
    val code : Int,
    val message: String,
)