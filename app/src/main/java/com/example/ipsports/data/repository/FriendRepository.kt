package com.example.ipsports.data.repository

import com.example.ipsports.data.model.Friend
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FriendRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {
    private val usersCollection = firestore.collection("users")

    /**  Obtiene la lista de amigos del usuario actual */
    suspend fun getFriends(): List<Friend> {
        val userId = auth.currentUser?.uid ?: return emptyList()
        return try {
            val result = usersCollection.document(userId)
                .collection("friends")
                .get()
                .await()

            result.documents.mapNotNull { it.toObject(Friend::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    /** 🔹 Obtiene solo los amigos aceptados del usuario actual */
    suspend fun getAcceptedFriends(): List<Friend> {
        val userId = auth.currentUser?.uid ?: return emptyList()
        return try {
            val result = usersCollection.document(userId)
                .collection("friends")
                .whereEqualTo("status", "accepted")
                .get()
                .await()

            result.documents.mapNotNull { it.toObject(Friend::class.java)?.copy(id = it.id) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    /** 🔹 Obtiene la lista de usuarios disponibles para agregar como amigos */
    suspend fun getAllUsers(): List<Friend> {
        val userId = auth.currentUser?.uid ?: return emptyList()
        return try {
            val result = usersCollection.get().await()
            result.documents
                .mapNotNull { it.toObject(Friend::class.java)?.copy(id = it.id) }
                .filter { it.id != userId }
        } catch (e: Exception) {
            emptyList()
        }
    }

    /** 🔹 Envía solicitud de amistad */
    suspend fun sendFriendRequest(friendId: String): Boolean {
        val userId = auth.currentUser?.uid ?: return false
        return try {
            usersCollection.document(friendId)
                .collection("friend_requests")
                .document(userId)
                .set(mapOf("status" to "pending"))
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
