package com.example.ipsports.data.Auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


//Solo debe manejar la autenticación (FirebaseAuth).
class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {

    // ✅ Flujo de estado de autenticación en tiempo real
    private val _authStateFlow = MutableStateFlow(firebaseAuth.currentUser != null)
    val authStateFlow: StateFlow<Boolean> = _authStateFlow.asStateFlow()

    init {
        firebaseAuth.addAuthStateListener { auth ->
            _authStateFlow.value = auth.currentUser != null
            println("🔥 FirebaseAuth - Usuario autenticado: ${auth.currentUser != null}")
        }
    }

    /** Registra un usuario en Firebase Auth y devuelve `AuthResult` */
    suspend fun register(email: String, password: String): AuthResult {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user
            if (user != null) {
                user.sendEmailVerification().await()
                println("✅ Registro exitoso y correo de verificación enviado")
                AuthResult.Success(user)
            } else {
                AuthResult.Failure(Exception("Usuario nulo después del registro"))
            }
        } catch (e: Exception) {
            AuthResult.Failure(e)
        }
    }

    /** Inicia sesión y verifica si el correo ha sido validado */
    suspend fun loginUser(email: String, password: String): AuthResult {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
            if (user?.isEmailVerified == true) {
                AuthResult.Success(user)
            } else {
                AuthResult.Failure(Exception("Debe verificar su correo antes de iniciar sesión."))
            }
        } catch (e: Exception) {
            AuthResult.Failure(e)
        }
    }

    suspend fun sendVerificationEmail(): Result<Unit> {
        return try {
            val user = firebaseAuth.currentUser
            user?.sendEmailVerification()?.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logoutUser() {
        firebaseAuth.signOut()
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}
