package com.example.ipsports.Model.Usecase

import com.example.ipsports.Model.Firebase.AuthRepository
import com.google.firebase.auth.FirebaseUser


// creamos clases para encapsular cada funcionalidad.
class LoginUseCase(private val repository: AuthRepository) {
    operator fun invoke(email: String, password: String, callback: (Result<FirebaseUser?>) -> Unit) {
        repository.loginUser(email, password, callback)
    }
}
