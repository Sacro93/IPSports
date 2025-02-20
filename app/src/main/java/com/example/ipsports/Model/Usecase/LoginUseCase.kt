package com.example.ipsports.Model.Usecase

import com.example.ipsports.Model.Auth.AuthRepository
import com.example.ipsports.Model.Auth.AuthResult
import com.google.firebase.auth.FirebaseUser


// creamos clases para encapsular cada funcionalidad.
class LoginUseCase(private val repository: AuthRepository) {
    operator fun invoke(email: String, password: String, callback: (AuthResult) -> Unit) {
        repository.loginUser(email, password, callback) // ✅ Usa AuthResult en lugar de Result<FirebaseUser?>
    }
}
