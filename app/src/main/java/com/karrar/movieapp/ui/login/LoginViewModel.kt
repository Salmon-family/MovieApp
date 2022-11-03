package com.karrar.movieapp.ui.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.usecases.login.LoginWithUserNameAndPasswordUseCase
import com.karrar.movieapp.domain.usecases.login.ValidateFiledUseCase
import com.karrar.movieapp.domain.usecases.login.ValidateLoginFormUseCase
import com.karrar.movieapp.domain.usecases.login.ValidatePasswordFiledUseCase
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    state: SavedStateHandle,
    private val loginWithUserNameAndPasswordUseCase: LoginWithUserNameAndPasswordUseCase,
    private val validateFiledUseCase: ValidateFiledUseCase,
    private val validatePasswordFiledUseCase: ValidatePasswordFiledUseCase,
    private val validateLoginFormUseCase: ValidateLoginFormUseCase,
) : ViewModel() {

    val args = LoginFragmentArgs.fromSavedStateHandle(state)

    private val _loginUIState = MutableStateFlow(LoginUiState())
    val loginUIState = _loginUIState.asStateFlow()

    private val _loginEvent = MutableStateFlow<Event<LoginUIEvent?>>(Event(null))
    val loginEvent = _loginEvent.asStateFlow()

    fun onClickSignUp() {
        _loginEvent.update { Event(LoginUIEvent.SignUpEvent) }
    }

    fun onClickLogin() {
        login()
    }

    fun onUserNameInputChange(text: CharSequence) {
        val userNameFieldState = validateFiledUseCase(text.toString())
        _loginUIState.update {
            it.copy(
                userName = text.toString(),
                userNameHelperText = userNameFieldState.errorMessage() ?: "",
                isValidForm = validateLoginFormUseCase(
                    loginUIState.value.userName,
                    loginUIState.value.password
                )
            )

        }
    }

    fun onPasswordInputChange(text: CharSequence) {
        val passwordFieldState = validatePasswordFiledUseCase(text.toString())
        _loginUIState.update {
            it.copy(
                password = text.toString(),
                passwordHelperText = passwordFieldState.errorMessage() ?: "",
                isValidForm = validateLoginFormUseCase(
                    loginUIState.value.userName,
                    loginUIState.value.password
                )
            )
        }
    }


    private fun login() {
        viewModelScope.launch {
            try {
                _loginUIState.update { it.copy(isLoading = true) }
                val loginState =
                    loginWithUserNameAndPasswordUseCase(
                        loginUIState.value.userName,
                        loginUIState.value.password
                    )
                if (loginState) {
                    onLoginSuccessfully()
                }
            } catch (e: Throwable) {
                onLoginError(e.message.toString())
            }
        }

    }

    private fun onLoginSuccessfully() {
        _loginUIState.update { it.copy(isLoading = false) }
        _loginEvent.update { Event(LoginUIEvent.LoginEvent(args.from)) }
        resetForm()
    }

    private fun onLoginError(message: String) {
        _loginUIState.update {
            it.copy(
                isLoading = false,
                error = message,
                passwordHelperText = message
            )
        }
    }


    private fun resetForm() {
        _loginUIState.update { it.copy(userName = "", password = "") }
    }

}