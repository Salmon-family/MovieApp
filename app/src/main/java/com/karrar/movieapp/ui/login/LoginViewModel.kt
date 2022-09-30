package com.karrar.movieapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.remote.Either
import com.karrar.movieapp.data.remote.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
) : ViewModel() {

    private val _userName = MutableLiveData("")


    private val _password = MutableLiveData("")
    fun onPasswordChanged(text: String) {
        _password.postValue(text)
    }

    fun onUserNameChanged(text: String) {
        _userName.postValue(text)
    }

    fun onClickLogin() {
        viewModelScope.launch {
            val loginState = loginWithUserNameANdPassword(_userName.value.toString(), _password.value.toString())
            when (loginState) {
                is Either.Left -> onLoginSuccessfully()
                is Either.Right -> onLongError(loginState.value)
            }

        }
    }

    private suspend fun loginWithUserNameANdPassword(
        userName: String,
        password: String,
    ): Either<Boolean, String> {
        return accountRepository.loginWithUserNameANdPassword(
            userName,
            password
        )
    }

    private fun onLoginSuccessfully() {

    }

    private fun onLongError(message:String) {

    }


}