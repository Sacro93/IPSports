package com.example.ipsports.data.repository


import com.example.ipsports.data.model.Sport
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SportRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val sportsCollection = firestore.collection("sports")

    suspend fun getSports(): List<Sport> = try {
        val result = sportsCollection.get().await()
        result.documents.mapNotNull { it.toObject(Sport::class.java) }
    } catch (e: Exception) {
        emptyList()
    }

}
