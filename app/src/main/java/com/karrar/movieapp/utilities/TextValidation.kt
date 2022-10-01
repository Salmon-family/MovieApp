package com.karrar.movieapp.utilities

import javax.inject.Inject

class TextValidation @Inject constructor(){


    fun validFiled(text : String) : String?{
        if (text.isBlank() || text.isEmpty()){
            return "Required"
        }
        return null
    }
    fun validPassword(passwordText: String): String? {
        if(passwordText.length < 8) {
            return "Minimum 8 Character Password"
        }
        if(!passwordText.matches(".*[a-z].*".toRegex())) {
            return "Must Contain 1 Lower-case Character"
        }
        return null
    }
}