package com.thechance.movie.ui.login

data class LoginUiState(
    val userName :String = "",
    val password :String = "",
    val userNameHelperText :String = "",
    val passwordHelperText :String = "",
    val isLoading:Boolean = false,
    val isValidForm : Boolean = false,
    val error:String = "",

)