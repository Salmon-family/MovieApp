package com.karrar.movieapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.AccountRepository
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.TextValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

fun <T> MutableLiveData<T>.toLiveData(): LiveData<T> {
    return this
}


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val textValidation: TextValidation,
) : ViewModel() {

    val userName = MutableLiveData("")
    val password = MutableLiveData("")

    private val _passwordHelperText = MutableLiveData("")
    val passwordHelperText = _passwordHelperText.toLiveData()
    private val _userNameHelperText = MutableLiveData("")
    val userNameHelperText = _userNameHelperText.toLiveData()

    private val _loginRequestState = MutableLiveData<State<Boolean>>()
    val loginRequestState = _loginRequestState.toLiveData()

    private val _loginEvent = MutableLiveData<Event<Boolean>>()
    val loginEvent = _loginEvent.toLiveData()


    fun onClickLogin() {
        if (isValidUserName() && isValidPassword()) {
           login()
        }
    }

    private fun isValidPassword(): Boolean {
        val validPassword = textValidation.validPassword(password.value.toString())
        _passwordHelperText.postValue(validPassword)
        return validPassword == null
    }

    private fun isValidUserName(): Boolean {
        val validUserName = textValidation.validFiled(userName.value.toString())
        _userNameHelperText.postValue(validUserName)
        return validUserName == null
    }



   private fun login() {
        viewModelScope.launch {
            accountRepository.loginWithUserNameANdPassword(userName.value.toString(),
                password.value.toString()).collect {
                when (it) {
                    is State.Error -> onLoginError(it.message)
                    State.Loading -> _loginRequestState.postValue(it)
                    is State.Success -> onLoginSuccessfully()
                }
            }
        }
    }

    private fun onLoginSuccessfully() {
        _loginRequestState.postValue(State.Success(true))
        _loginEvent.postValue(Event(true))
        resetForm()

    }

    private fun onLoginError(message: String) {
        _loginRequestState.postValue(State.Error(message))
        _passwordHelperText.postValue(message)

    }


    private fun resetForm() {
        userName.postValue(null)
        password.postValue(null)
    }

}