package com.karrar.movieapp.ui.login

import androidx.lifecycle.*
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

    private val _signUpEvent = MutableLiveData<Event<Boolean>>()
    val signUpEvent = _signUpEvent.toLiveData()


    val loginValidation = MediatorLiveData<Boolean>().apply {
        addSource(userName,this@LoginViewModel::isValidUserName)
        addSource(password,this@LoginViewModel::isValidPassword)
    }

    fun onClickSignUp() {
        _signUpEvent.postValue(Event(true))
    }

    fun onClickLogin() {
            login()

    }

    private fun isValidPassword(password:String) {
        val validPassword = textValidation.validPassword(password)
        _passwordHelperText.postValue(validPassword)
        loginValidation.postValue(validPassword == null)

    }

    private fun isValidUserName(userName:String) {
        val validUserName = textValidation.validFiled(userName)
        _userNameHelperText.postValue(validUserName)
        loginValidation.postValue(validUserName == null)

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