package com.example.ipsports.data.repository

import com.example.ipsports.data.model.Center
import com.example.ipsports.data.model.CenterWithSports
import com.example.ipsports.data.model.Sport
import com.example.ipsports.data.model.SportInCenter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CenterRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val centersCollection = firestore.collection("centers")

    suspend fun getCentersWithSports(): List<CenterWithSports> {
        return try {
            val centerResult = centersCollection.get().await()
            val centers = centerResult.documents.mapNotNull { doc ->
                doc.toObject(Center::class.java)?.copy(id = doc.id)
            }

            val centersWithSports = centers.map { center ->
                val sportsResult = centersCollection.document(center.id)
                    .collection("sports") // 🔹 Subcolección de deportes en cada centro
                    .get()
                    .await()

                val sports = sportsResult.documents.mapNotNull { it.toObject(SportInCenter::class.java) }
                CenterWithSports(center, sports)
            }

            centersWithSports
        } catch (e: Exception) {
            println("❌ Error al obtener centros con deportes: ${e.localizedMessage}")
            emptyList()
        }
    }
}
