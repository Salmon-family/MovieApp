package com.karrar.movieapp.ui.login

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.AccountRepository
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.FormFiledValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

fun <T> MutableLiveData<T>.toLiveData(): LiveData<T> {
    return this
}


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val textValidation: FormFiledValidator,
) : ViewModel() {

    val userName = MutableLiveData<String?>()
    val password = MutableLiveData<String?>()

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
        addSource(userName, ::checkUserNameValidation)
        addSource(password, ::checkPasswordValidation)
    }

    fun onClickSignUp() {
        _signUpEvent.postValue(Event(true))
    }

    fun onClickLogin() {
        login()

    }


    private fun checkPasswordValidation(password: String?) {
        val passwordFieldState = textValidation.validatePasswordFiledState(password.toString())
        _passwordHelperText.postValue(passwordFieldState.errorMessage())
        checkFormValidation()

    }

    private fun checkUserNameValidation(userName: String?) {
        val userNameFieldState = textValidation.validateFiledState(userName.toString())
        _userNameHelperText.postValue(userNameFieldState.errorMessage())
        checkFormValidation()
    }

    private fun checkFormValidation() {
        val isValidUserNameAndPassword = textValidation.isValidUserNameAndPassword(userName.value.toString(), password.value.toString())
        loginValidation.postValue(isValidUserNameAndPassword)


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