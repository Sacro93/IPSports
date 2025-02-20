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
        authRepository.register(email, password, name, surname, location) { result ->
            if (result is AuthResult.Success) {
                val user = authRepository.getCurrentUser() // ✅ Obtiene el usuario actual
                val userId = user?.uid // ✅ Obtiene el ID del usuario autenticado

                if (userId != null) {
                    authRepository.saveUserData(userId, name, surname, email, location) { saveResult ->
                        callback(saveResult) // ✅ Retorna el resultado después de guardar en Firestore
                    }
                } else {
                    callback(AuthResult.Failure(Exception("Error al obtener el ID del usuario")))
                }
            } else {
                callback(result) // ❌ Si hubo un error en el registro, lo retorna directamente
            }
        }
    }
}
