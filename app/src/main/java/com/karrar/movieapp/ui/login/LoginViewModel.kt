package com.karrar.movieapp.ui.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.FormFiledValidator
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val textValidation: FormFiledValidator,
    private val state: SavedStateHandle
) : BaseViewModel() {

    val args = LoginFragmentArgs.fromSavedStateHandle(state)

    val userName = MutableLiveData<String?>()
    val password = MutableLiveData<String?>()

    private val _passwordHelperText = MutableLiveData("")
    val passwordHelperText = _passwordHelperText.toLiveData()

    private val _userNameHelperText = MutableLiveData("")
    val userNameHelperText = _userNameHelperText.toLiveData()

    private val _loginRequestState = MutableLiveData<UIState<Boolean>>()
    val loginRequestState = _loginRequestState.toLiveData()

    private val _loginEvent = MutableLiveData<Event<Int>>()
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
        val isValidUserNameAndPassword = textValidation.isValidUserNameAndPassword(
            userName.value.toString(),
            password.value.toString()
        )
        loginValidation.postValue(isValidUserNameAndPassword)
    }

    private fun login() {
        wrapWithState({
            accountRepository.loginWithUserNameANdPassword(userName.value.toString(), password.value.toString())
            onLoginSuccessfully()
        }, {
            onLoginError(it.message.toString())
        })

    }

    private fun onLoginSuccessfully() {
        _loginRequestState.postValue(UIState.Success(true))
        _loginEvent.postEvent(args.from)
        resetForm()
    }

    private fun onLoginError(message: String) {
        _loginRequestState.postValue(UIState.Error(message))
        _passwordHelperText.postValue(message)
    }


    private fun resetForm() {
        userName.postValue(null)
        password.postValue(null)
    }

    override fun getData() {
    }

}