package com.example.ipsports.Model.Firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

//creamos una clase para interactuar con Firebase:
class AuthRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun registerUser(
        email: String,
        password: String,
        name: String,
        surname: String,
        location: String,
        callback: (Result<FirebaseUser?>) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        saveUserData(it.uid, name, surname, email, location) { saveResult ->
                            if (saveResult.isSuccess) {
                                callback(Result.success(it))
                            } else {
                                callback(Result.failure(saveResult.exceptionOrNull()!!))
                            }
                        }
                    }
                } else {
                    callback(Result.failure(task.exception ?: Exception("Error desconocido")))
                }
            }
    }

    private fun saveUserData(
        userId: String,
        name: String,
        surname: String,
        email: String,
        location: String,
        callback: (Result<Unit>) -> Unit
    ) {
        val userMap = mapOf(
            "name" to name,
            "surname" to surname,
            "email" to email,
            "location" to location,
            "profileImage" to null // No se sube foto al registrarse
        )

        firestore.collection("users").document(userId)
            .set(userMap)
            .addOnSuccessListener { callback(Result.success(Unit)) }
            .addOnFailureListener { e -> callback(Result.failure(e)) }
    }





    fun loginUser(email: String, password: String, callback: (Result<FirebaseUser?>) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(Result.success(auth.currentUser))
            } else {
                callback(Result.failure(task.exception ?: Exception("Error desconocido")))
            }
        }
    }

    fun signInWithGoogle(idToken: String, callback: (Result<FirebaseUser?>) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(Result.success(auth.currentUser))
            } else {
                callback(Result.failure(task.exception ?: Exception("Error en Google Sign-In")))
            }
        }
    }
}