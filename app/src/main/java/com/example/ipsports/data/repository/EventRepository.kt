package com.example.ipsports.data.repository


import com.example.ipsports.data.model.Event
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val eventsCollection = firestore.collection("events")

    /** Agrega un evento a Firestore y devuelve el ID generado */
    suspend fun addEvent(
        sportId: String,
        centerId: String,
        userId: String,
        invitedFriends: List<String>,
        date: Timestamp
    ): Result<String> {
        return try {
            val newEvent = Event(
                sportId = sportId,
                centerId = centerId,
                userId = userId,
                date = date,
                usersInvited = invitedFriends
            )

            val documentRef = eventsCollection.add(newEvent).await()
            Result.success(documentRef.id) // ✅ Devuelve el ID del evento creado
        } catch (e: Exception) {
            Result.failure(e) // ✅ Devuelve la excepción si hay un error
        }
    }

    suspend fun getEventsByUser(userId: String): List<Event> {
        return try {
            val result = eventsCollection.whereEqualTo("userId", userId).get().await()
            result.documents.mapNotNull { it.toObject(Event::class.java)?.copy(id = it.id) }
        } catch (e: Exception) {
            emptyList() // Retorna lista vacía en caso de error
        }
    }
}
