package com.karrar.movieapp.ui.myList

import com.karrar.movieapp.domain.models.CreatedList


data class MyListUIState(
    val createdList: List<CreatedListUI> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

data class CreatedListUI(
    val listID: Int = 0,
    val name: String = "",
    val mediaCounts: Int = 0
)


fun CreatedList.toUIState(): CreatedListUI {
    return CreatedListUI(
        listID = id,
        name = name,
        mediaCounts = itemCount
    )
}