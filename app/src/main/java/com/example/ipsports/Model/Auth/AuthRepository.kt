package com.example.ipsports.Model.Auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

//creamos una clase para interactuar con Firebase:
class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    fun register(
        email: String, password: String, name: String, surname: String, location: String,
        callback: (AuthResult) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    user?.sendEmailVerification()?.addOnCompleteListener { verifyTask ->
                        if (verifyTask.isSuccessful) {
                            saveUserData(user.uid, name, surname, email, location) { saveResult ->
                                callback(saveResult)
                            }
                        } else {
                            callback(AuthResult.Failure(verifyTask.exception ?: Exception("Error enviando email de verificación")))
                        }
                    }
                } else {
                    callback(AuthResult.Failure(task.exception ?: Exception("Error registrando usuario")))
                }
            }
    }

    fun loginUser(email: String, password: String, callback: (AuthResult) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    if (isUserVerified()) {
                        callback(AuthResult.Success(user))
                    } else {
                        callback(AuthResult.Failure(Exception("Debe verificar su correo antes de iniciar sesión.")))
                    }
                } else {
                    callback(AuthResult.Failure(task.exception ?: Exception("Error al iniciar sesión.")))
                }
            }
    }


    fun saveUserData(
        userId: String,
        name: String,
        surname: String,
        email: String,
        location: String,
        callback: (AuthResult) -> Unit
    ) {
        val userMap = mapOf(
            "name" to name,
            "surname" to surname,
            "email" to email,
            "location" to (location ?: "Barcelona"), // ✅ Usa "Barcelona" si location es null
            "profileImageUrl" to "https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y",
            "verified" to false // ✅ Se usará para verificar el email más adelante
        )

        firestore.collection("users").document(userId)
            .set(userMap)
            .addOnSuccessListener { callback(AuthResult.Success(null)) }
            .addOnFailureListener { e -> callback(AuthResult.Failure(e)) }
    }

    private fun isUserVerified(): Boolean {
        return firebaseAuth.currentUser?.isEmailVerified ?: false
    }

    fun sendVerificationEmail(callback: (Boolean, String?) -> Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("Auth", "Correo de verificación enviado nuevamente.")
            } else {
                Log.e("Auth", "Error al enviar verificación: ${task.exception?.message}")
            }
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}
