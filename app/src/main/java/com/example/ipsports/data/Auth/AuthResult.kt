package com.example.ipsports.data.Auth

import com.google.firebase.auth.FirebaseUser


//na clase sellada (sealed class) para manejar los estados de la autenticación.
sealed class AuthResult {
    object Loading : AuthResult()
    data class Success(val user: FirebaseUser?) : AuthResult()
    data class Failure(val exception: Throwable) : AuthResult()
}
