package com.karrar.movieapp.utilities

import javax.inject.Inject

class FormFiledValidator @Inject constructor(){


    fun validateFiledState(text : String) : FormFieldState {
        if (text.isBlank() || text.isEmpty()){
            return FormFieldState.InValid("Required")
        }
        return FormFieldState.Valid
    }
    fun validatePasswordFiledState(passwordText: String): FormFieldState {
        if(passwordText.length < 4) {
            return FormFieldState.InValid("Minimum 4 Character Password")
        }
        return FormFieldState.Valid
    }

    fun isValidUserNameAndPassword(userName:String , password:String) : Boolean{
        return validateFiledState(userName).isValid()  and  validatePasswordFiledState(password).isValid()
    }
}