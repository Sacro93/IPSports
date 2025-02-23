package com.example.ipsports.data.Usecase

import com.example.ipsports.data.Auth.AuthRepository
import com.example.ipsports.data.Auth.AuthResult


// creamos clases para encapsular cada funcionalidad.
class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): AuthResult {
        return repository.loginUser(email, password) // ✅ Ahora se llama correctamente la función suspend
    }
}
