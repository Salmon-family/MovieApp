package com.karrar.movieapp.ui.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.login.LoginUseCase
import com.karrar.movieapp.domain.login.ValidateFiledUseCase
import com.karrar.movieapp.domain.login.ValidateLoginFormUseCase
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
    private val validateLoginFormUseCase: ValidateLoginFormUseCase,
) : ViewModel() {

    val args = LoginFragmentArgs.fromSavedStateHandle(state)

    private val _loginEvent = MutableLiveData<Event<Int>>()
    val loginEvent = _loginEvent.toLiveData()

    private val _signUpEvent = MutableLiveData<Event<Boolean>>()
    val signUpEvent = _signUpEvent.toLiveData()

    private val _loginUIState = MutableStateFlow(LoginUiState())
    val loginUIState = _loginUIState.asStateFlow()


    fun onClickSignUp() {
        _signUpEvent.postValue(Event(true))
    }

    fun onClickLogin() {
        login()
    }

     fun onUserNameInputChange(text : CharSequence){
         val userNameFieldState = validateFiledUseCase(text.toString())
         _loginUIState.update {
             it.copy(
                 userName = text.toString(),
                 userNameHelperText = userNameFieldState.errorMessage()?:"",
                 isValidForm = validateLoginFormUseCase(loginUIState.value.userName,loginUIState.value.password)
             )

         }
    }
    fun onPasswordInputChange(text :  CharSequence){
        val passwordFieldState = validatePasswordFiledUseCase(text.toString())
        _loginUIState.update {
            it.copy(
                password = text.toString(),
                passwordHelperText = passwordFieldState.errorMessage()?:"",
                isValidForm = validateLoginFormUseCase(loginUIState.value.userName,loginUIState.value.password)
        ) }
    }





    private fun login() {
        viewModelScope.launch {
            try {
                _loginUIState.update { it.copy(isLoading = true) }
                val isLoginSuccessfully =
                    loginUseCase(loginUIState.value.userName, loginUIState.value.password)
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
        _loginUIState.update { it.copy(userName = "", password = "") }
    }

}