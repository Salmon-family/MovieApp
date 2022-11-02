package com.karrar.movieapp.domain.usecases.login

import com.karrar.movieapp.utilities.FormFieldState
import javax.inject.Inject

class ValidatePasswordFiledUseCase @Inject constructor(){
    operator fun invoke(passwordText: String) : FormFieldState {
        if(passwordText.length < 4) {
            return FormFieldState.InValid("Minimum 4 Character Password")
        }
        return FormFieldState.Valid
    }
}