package com.example.ipsports.data.DatosDefault

import com.example.ipsports.data.model.Center
import com.example.ipsports.data.model.Sport
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun populateFirestore(firestore: FirebaseFirestore) {
    println("🔥 Intentando poblar Firestore...")

    val sportsCollection = firestore.collection("sports")
    val centersCollection = firestore.collection("centers")

    try {
        val sportsExist = sportsCollection.get().await().isEmpty.not()
        val centersExist = centersCollection.get().await().isEmpty.not()

        if (sportsExist && centersExist) {
            println("✅ Firestore ya tiene datos. No se insertarán nuevamente.")
            return
        }

        println("🔥 Insertando deportes y centros...")

        val sports = listOf(
            Sport(id = "sport1", name = "Fútbol"),
            Sport(id = "sport2", name = "Pádel"),
            Sport(id = "sport3", name = "Tenis"),
            Sport(id = "sport4", name = "Básquet")
        )

        sports.forEach { sport ->
            println("📌 Agregando deporte: ${sport.name}")
            sportsCollection.document(sport.id).set(sport).await()
        }

        val centers = listOf(
            Center(
                name = "Camp Nou Sports Complex",
                sports = listOf("sport1", "sport3", "sport4"), // Fútbol, Tenis, Básquet
                location = "Carrer d'Arístides Maillol, 12, Barcelona"
            ),
            Center(
                name = "Pádel Indoor Barcelona",
                sports = listOf("sport2"), // Pádel
                location = "Carrer de Ganduxer, 25-27, Barcelona"
            ),
            Center(
                name = "Club de Tennis Barcino",
                sports = listOf("sport3"), // Tenis
                location = "Carrer de la Marquesa de Vilallonga, 57, Barcelona"
            ),
            Center(
                name = "Poliesportiu Municipal Sants",
                sports = listOf("sport1", "sport4"), // Fútbol, Básquet
                location = "Carrer de l'Espanya Industrial, 3, Barcelona"
            ),
            Center(
                name = "Pádel Diagonal",
                sports = listOf("sport2"), // Pádel
                location = "Avinguda Diagonal, 685, Barcelona"
            ),
            Center(
                name = "Estadi Olímpic Lluís Companys",
                sports = listOf("sport1", "sport3"), // Fútbol, Tenis
                location = "Passeig Olímpic, 17-19, Barcelona"
            ),
            Center(
                name = "CEM Marítim",
                sports = listOf("sport1", "sport2", "sport3"), // Fútbol, Pádel, Tenis
                location = "Passeig Marítim de la Barceloneta, 33, Barcelona"
            ),
            Center(
                name = "Basquet Club Barcelona",
                sports = listOf("sport4"), // Básquet
                location = "Carrer de Sardenya, 287, Barcelona"
            )
        )

        centers.forEach { center ->
            val docRef = centersCollection.add(center).await()
            println("📌 Centro agregado: ${center.name} (ID: ${docRef.id})")
        }

        println("🔥 Datos insertados exitosamente en Firestore")
    } catch (e: Exception) {
        println("❌ Error en Firestore: ${e.message}")
    }
}

