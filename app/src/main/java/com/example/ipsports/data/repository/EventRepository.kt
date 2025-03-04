package com.example.ipsports.data.repository


import com.example.ipsports.data.model.Event
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val eventsCollection = firestore.collection("events") // 🔹 Definir `eventsCollection`

    /** Agrega un evento a Firestore y devuelve el ID generado */
    suspend fun addEvent(event: Event): Result<String> {
        return try {
            val documentRef = eventsCollection.add(event).await()
            Result.success(documentRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /** 🔹 Obtiene el evento más reciente del usuario */
    suspend fun getLatestEvent(userId: String): Event? {
        return try {
            val result = eventsCollection
                .whereEqualTo("userId", userId)
                .orderBy("date", Query.Direction.DESCENDING) // 🔹 Ordenar por fecha descendente
                .limit(1) // 🔹 Obtener solo el más reciente
                .get()
                .await()

            result.documents.firstOrNull()?.toObject(Event::class.java)?.copy(id = result.documents.first().id)
        } catch (e: Exception) {
            null
        }
    }

    /** 🔹 Obtiene todos los eventos del usuario */
    suspend fun getEventsByUser(userId: String): List<Event> {
        return try {
            val result = eventsCollection.whereEqualTo("userId", userId).get().await()
            result.documents.mapNotNull { it.toObject(Event::class.java)?.copy(id = it.id) }
        } catch (e: Exception) {
            emptyList()
        }
    }

}

