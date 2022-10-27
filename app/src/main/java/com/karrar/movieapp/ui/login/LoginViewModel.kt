package com.karrar.movieapp.ui.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.login.LoginUseCase
import com.karrar.movieapp.domain.login.ValidateFiledUseCase
import com.karrar.movieapp.domain.login.ValidatePasswordFiledUseCase
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    state: SavedStateHandle,
    private val loginUseCase: LoginUseCase,
    private val validateFiledUseCase: ValidateFiledUseCase,
    private val validatePasswordFiledUseCase: ValidatePasswordFiledUseCase,
) : BaseViewModel() {

    val args = LoginFragmentArgs.fromSavedStateHandle(state)

    val userName = MutableLiveData<String?>()
    val password = MutableLiveData<String?>()

    private val _loginEvent = MutableLiveData<Event<Int>>()
    val loginEvent = _loginEvent.toLiveData()

    private val _signUpEvent = MutableLiveData<Event<Boolean>>()
    val signUpEvent = _signUpEvent.toLiveData()

    private val _loginUIState = MutableStateFlow(LoginUiState())
    val loginUIState = _loginUIState.asStateFlow()

     val loginValidation = MediatorLiveData<Boolean>().apply {
        addSource(userName, ::checkFormField)
        addSource(password, ::checkFormField)
    }

    fun onClickSignUp() {
        _signUpEvent.postValue(Event(true))
    }

    fun onClickLogin() {
        login()
    }

    private fun checkFormField(text: String?) {
        val userNameFieldState = validateFiledUseCase(userName.value.toString())
        val passwordFieldState = validatePasswordFiledUseCase(password.value.toString())
        _loginUIState.update { it.copy(
            passwordHelperText = passwordFieldState.errorMessage()?:"",
            userNameHelperText = userNameFieldState.errorMessage()?:"",
            isValidForm =userNameFieldState.isValid() && passwordFieldState.isValid()
        ) }
    }



    private fun login() {
        viewModelScope.launch {
            try {
                _loginUIState.update { it.copy(isLoading = true) }
                val isLoginSuccessfully =
                    loginUseCase(userName.value.toString(), password.value.toString())
                if (isLoginSuccessfully) {
                    onLoginSuccessfully()
                }
            } catch (e: Throwable) {
                onLoginError(e.message.toString())
            }
        }

    }

    private fun onLoginSuccessfully() {
        _loginUIState.update { it.copy(isLoading = false) }
        _loginEvent.postEvent(args.from)
        resetForm()
    }

    private fun onLoginError(message: String) {
        _loginUIState.update {
            it.copy(isLoading = false,
                error = message,
                passwordHelperText = message)
        }
    }


    private fun resetForm() {
        userName.postValue(null)
        password.postValue(null)
    }

    override fun getData() {
    }

}