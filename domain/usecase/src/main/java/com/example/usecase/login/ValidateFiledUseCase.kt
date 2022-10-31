package com.example.usecase.login

import com.example.usecase.utilites.FormFieldState
import javax.inject.Inject

class ValidateFiledUseCase @Inject constructor() {
    operator fun invoke(text: String): FormFieldState {
        if (text.isBlank() || text.isEmpty()) {
            return FormFieldState.InValid("Required")
        }
        return FormFieldState.Valid
    }
}
