package com.karrar.movieapp.utilities

import com.karrar.movieapp.domain.FormFieldState
import javax.inject.Inject

class TextFiledValidation @Inject constructor(){


    fun validateFiledState(text : String) : FormFieldState{
        if (text.isBlank() || text.isEmpty()){
            return FormFieldState.InValid("Required")
        }
        return FormFieldState.Valid
    }
    fun validatePasswordFiledState(passwordText: String): FormFieldState {
        if(passwordText.length < 8) {
            return FormFieldState.InValid("Minimum 8 Character Password")
        }
        if(!passwordText.matches(".*[a-z].*".toRegex())) {
            return FormFieldState.InValid("Must Contain 1 Lower-case Character")
        }
        return FormFieldState.Valid
    }
}