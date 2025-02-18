package com.example.ipsports.Model.Usecase

import com.example.ipsports.Model.Firebase.AuthRepository
import com.google.firebase.auth.FirebaseUser

//Encapsulamos la lógica en un caso de uso para mantener limpio el ViewModel.
class RegisterUseCase(private val repository: AuthRepository) {
    operator fun invoke(
        email: String,
        password: String,
        name: String,
        surname: String,
        location: String,
        callback: (Result<FirebaseUser?>) -> Unit
    ) {
        repository.registerUser(email, password, name, surname, location, callback)
    }
}
