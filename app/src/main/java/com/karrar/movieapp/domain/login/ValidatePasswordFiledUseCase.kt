package com.karrar.movieapp.domain.login

import com.karrar.movieapp.utilities.FormFieldState
import javax.inject.Inject

class ValidatePasswordFiledUseCase @Inject constructor(){
    operator fun invoke(passwordText: String) : FormFieldState {
        if(passwordText.length < 8) {
            return FormFieldState.InValid("Minimum 8 Character Password")
        }
        if(!passwordText.matches(".*[a-z].*".toRegex())) {
            return FormFieldState.InValid("Must Contain 1 Lower-case Character")
        }
        return FormFieldState.Valid
    }
}