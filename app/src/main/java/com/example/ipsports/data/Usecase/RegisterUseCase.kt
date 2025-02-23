package com.example.ipsports.data.Usecase

import com.example.ipsports.data.Auth.AuthRepository
import com.example.ipsports.data.Auth.AuthResult
import com.example.ipsports.data.model.User
import com.example.ipsports.data.repository.UserRepository
import javax.inject.Inject

//Encapsulamos la lógica en un caso de uso para mantener limpio el ViewModel.
class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        name: String,
        surname: String,
        location: String
    ): AuthResult {
        println("🔹 RegisterUseCase iniciado...")

        return try {
            val authResult = authRepository.register(email, password) // ✅ Ahora devuelve `AuthResult`
            if (authResult is AuthResult.Success) {
                println("✅ Registro exitoso en Firebase Auth")

                val user = authRepository.getCurrentUser()
                val userId = user?.uid

                if (userId != null) {
                    val newUser = User(
                        id = userId,
                        name = name,
                        surname = surname,
                        email = email,
                        location = location,
                        profileImageUrl = ""
                    )

                    println("📌 Guardando usuario en Firestore...")

                    val result = userRepository.addUser(newUser)
                    if (result.isSuccess) {
                        println("✅ Usuario guardado en Firestore correctamente")
                        AuthResult.Success(user)
                    } else {
                        val error = result.exceptionOrNull()
                        println("❌ Error guardando en Firestore: ${error?.localizedMessage}")
                        AuthResult.Failure(error ?: Exception("Error desconocido en Firestore"))
                    }
                } else {
                    println("❌ Error: UID del usuario es null")
                    AuthResult.Failure(Exception("Error al obtener el ID del usuario"))
                }
            } else {
                println("❌ Error en AuthRepository.register()")
                authResult
            }
        } catch (e: Exception) {
            println("❌ Error inesperado en RegisterUseCase: ${e.localizedMessage}")
            AuthResult.Failure(e)
        }
    }
}

