package com.example.ipsports.data.repository


import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import com.example.ipsports.data.model.User
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

// Solo debe manejar los datos del usuario (Firestore).


class UserRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
) {
    private val usersCollection = firestore.collection("users")

    /** Guarda un nuevo usuario en Firestore */
    suspend fun addUser(user: User): Result<Unit> {
        return try {
            usersCollection.document(user.id).set(user).await()
            Result.success(Unit) // 🔹 Devuelve `Unit` si fue exitoso
        } catch (e: Exception) {
            Log.e("UserRepository", "Error al guardar usuario: ${e.localizedMessage}")
            Result.failure(e)
        }
    }

    /** Obtiene un usuario por su ID */
    suspend fun getUser(userId: String): Result<User?> {
        return try {
            val document = usersCollection.document(userId).get().await()
            Result.success(document.toObject(User::class.java))
        } catch (e: Exception) {
            Log.e("UserRepository", "Error al obtener usuario: ${e.localizedMessage}")
            Result.failure(e)
        }
    }

    /** Actualiza la información del usuario sin sobrescribir campos vacíos */
    suspend fun updateUser(user: User): Result<Unit> {
        return try {
            usersCollection.document(user.id).set(user, SetOptions.merge()).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("UserRepository", "Error al actualizar usuario: ${e.localizedMessage}")
            Result.failure(e)
        }
    }
}
