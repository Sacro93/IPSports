package com.example.ipsports.data.repository

import com.example.ipsports.data.model.CenterWithSports
import com.example.ipsports.data.model.Center
import com.example.ipsports.data.model.Sport
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
            val centers = centerResult.documents.mapNotNull {
                it.toObject(Center::class.java)?.copy(id = it.id)
            }

            val centersWithSports = mutableListOf<CenterWithSports>()

            for (center in centers) {
                val sportsResult =
                    centersCollection.document(center.id).collection("sports").get().await()
                val sports = sportsResult.documents.mapNotNull { it.toObject(Sport::class.java) }
                centersWithSports.add(CenterWithSports(center, sports))
            }

            centersWithSports
        } catch (e: Exception) {
            emptyList() // En caso de error, retorna una lista vacía
        }
    }
}

  /* suspend fun getCentersWithSports(onResult: (List<CenterWithSports>) -> Unit) {
        centersCollection.get()
            .addOnSuccessListener { centerResult ->
                val centers = centerResult.documents.mapNotNull {
                    it.toObject(Center::class.java)?.copy(id = it.id)
                }

                val centersWithSports = mutableListOf<CenterWithSports>()
                var remainingCenters = centers.size

                if (centers.isEmpty()) {
                    onResult(emptyList()) // No hay centros, devolver lista vacía
                    return@addOnSuccessListener
                }

                centers.forEach { center ->
                    centersCollection.document(center.id)
                        .collection("sports") // 🔹 Obtener solo los deportes de este centro
                        .get()
                        .addOnSuccessListener { sportResult ->
                            val sports = sportResult.documents.mapNotNull { it.toObject(Sport::class.java) }
                            centersWithSports.add(CenterWithSports(center, sports))

                            remainingCenters--
                            if (remainingCenters == 0) {
                                onResult(centersWithSports) // ✅ Devuelve la lista cuando termine
                            }
                        }
                        .addOnFailureListener {
                            remainingCenters--
                            if (remainingCenters == 0) {
                                onResult(centersWithSports)
                            }
                        }
                }
            }
            .addOnFailureListener {
                onResult(emptyList()) // Si falla la consulta de centros, devuelve lista vacía
            }
    }

}
*/
