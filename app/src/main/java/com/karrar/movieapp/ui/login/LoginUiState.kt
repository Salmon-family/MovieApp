package com.karrar.movieapp.ui.login

data class LoginUiState(
    val userNameHelperText :String = "",
    val passwordHelperText :String = "",
    val isLoading:Boolean = false,
    val isValidForm : Boolean = false,
    val error:String = "",

)