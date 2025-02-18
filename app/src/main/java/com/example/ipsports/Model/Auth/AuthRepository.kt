package com.example.ipsports.Model.Auth

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
                    if (user?.isEmailVerified == true) {
                        callback(AuthResult.Success(user))
                    } else {
                        callback(AuthResult.Failure(Exception("Debe verificar su correo antes de iniciar sesión.")))
                    }
                } else {
                    callback(AuthResult.Failure(task.exception ?: Exception("Error al iniciar sesión.")))
                }
            }
    }

    private fun saveUserData(
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
            "location" to location,
            "profileImage" to null,
            "verified" to false // 🔹 Nuevo campo para saber si verificó el email
        )

        firestore.collection("users").document(userId)
            .set(userMap)
            .addOnSuccessListener { callback(AuthResult.Success(null)) }
            .addOnFailureListener { e -> callback(AuthResult.Failure(e)) }
    }

    fun isUserVerified(): Boolean {
        return firebaseAuth.currentUser?.isEmailVerified ?: false
    }

    fun sendVerificationEmail(callback: (Boolean, String?) -> Unit) {
        val user = firebaseAuth.currentUser
        if (user != null) {
            user.sendEmailVerification()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callback(true, null)
                    } else {
                        callback(false, task.exception?.message)
                    }
                }
        } else {
            callback(false, "Usuario no autenticado")
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}
