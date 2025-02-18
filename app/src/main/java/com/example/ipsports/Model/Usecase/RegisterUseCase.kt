package com.example.ipsports.Model.Usecase

import com.example.ipsports.Model.Auth.AuthRepository
import com.example.ipsports.Model.Auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

//Encapsulamos la lógica en un caso de uso para mantener limpio el ViewModel.
class RegisterUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(
        email: String,
        password: String,
        name: String,
        surname: String,
        location: String,
        callback: (AuthResult) -> Unit
    ) {
        authRepository.register(email, password, name, surname, location, callback)
    }
}
