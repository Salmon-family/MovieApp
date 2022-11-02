package com.karrar.movieapp.domain.usecases.login

import javax.inject.Inject

class ValidateLoginFormUseCase @Inject constructor(
    private val validateFiledUseCase: ValidateFiledUseCase,
    private val validatePasswordFiledUseCase: ValidatePasswordFiledUseCase,
){

    operator fun invoke(userName:String,password:String):Boolean{
        return validateFiledUseCase(userName).isValid() && validatePasswordFiledUseCase(password).isValid()
    }
}