package com.example.ipsports.data.DatosDefault

import com.example.ipsports.data.model.Center
import com.example.ipsports.data.model.Sport
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun populateFirestore(firestore: FirebaseFirestore) {
    println("🔥 Poblando Firestore con centros y deportes...")

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

        // 🔹 Lista de deportes
        val sports = listOf(
            Sport(id = "sport1", name = "Fútbol"),
            Sport(id = "sport2", name = "Pádel"),
            Sport(id = "sport3", name = "Tenis"),
            Sport(id = "sport4", name = "Básquet"),
            Sport(id = "sport5", name = "Natación"),
            Sport(id = "sport6", name = "Voleibol"),
            Sport(id = "sport7", name = "Hockey"),
            Sport(id = "sport8", name = "Rugby")
        )

        // 🔹 Insertar deportes en la colección "sports"
        sports.forEach { sport ->
            println("📌 Agregando deporte: ${sport.name}")
            sportsCollection.document(sport.id).set(sport).await()
        }

        // 🔹 Lista de centros deportivos con ubicación
        val centers = listOf(
            Center(id = "center1", name = "Camp Nou Sports Complex", location = "Les Corts, Barcelona"),
            Center(id = "center2", name = "Poliesportiu Municipal Sants", location = "Sants, Barcelona"),
            Center(id = "center3", name = "CEM Marítim", location = "Barceloneta, Barcelona"),
            Center(id = "center4", name = "Padel Indoor Barcelona", location = "Eixample, Barcelona"),
            Center(id = "center5", name = "Club de Tennis Barcino", location = "Sarrià-Sant Gervasi, Barcelona"),
            Center(id = "center6", name = "Estadi Olímpic Lluís Companys", location = "Montjuïc, Barcelona"),
            Center(id = "center7", name = "Nova Icaria Esport & Fit", location = "Poblenou, Barcelona"),
            Center(id = "center8", name = "Poliesportiu La Mina", location = "Sant Adrià del Besòs, Barcelona"),
            Center(id = "center9", name = "Complejo Esportiu Can Dragó", location = "Nou Barris, Barcelona"),
            Center(id = "center10", name = "Club Natació Barcelona", location = "Barceloneta, Barcelona"),
            Center(id = "center11", name = "Club de Tennis La Salut", location = "Gràcia, Barcelona"),
            Center(id = "center12", name = "Palau Sant Jordi", location = "Montjuïc, Barcelona"),
            Center(id = "center13", name = "Poliesportiu Municipal Can Ricart", location = "El Raval, Barcelona"),
            Center(id = "center14", name = "Pavelló Olímpic Vall d’Hebron", location = "Horta-Guinardó, Barcelona"),
            Center(id = "center15", name = "Club Esportiu Laietà", location = "Les Corts, Barcelona")
        )

        // 🔹 Mapeo de deportes por centro
        val centerSportsMap = mapOf(
            "center1" to listOf("sport1", "sport3", "sport4"), // Camp Nou: Fútbol, Tenis, Básquet
            "center2" to listOf("sport1", "sport4", "sport2"), // Poliesportiu Sants: Fútbol, Básquet, Pádel
            "center3" to listOf("sport1", "sport2", "sport3", "sport5"), // CEM Marítim: Fútbol, Pádel, Tenis, Natación
            "center4" to listOf("sport2"), // Padel Indoor: Solo Pádel
            "center5" to listOf("sport3"), // Club Tennis Barcino: Solo Tenis
            "center6" to listOf("sport1", "sport4", "sport6"), // Estadi Olímpic: Fútbol, Básquet, Voleibol
            "center7" to listOf("sport1", "sport4", "sport2"), // Nova Icaria: Fútbol, Básquet, Pádel
            "center8" to listOf("sport1", "sport4"), // Poliesportiu La Mina: Fútbol, Básquet
            "center9" to listOf("sport1", "sport4", "sport2"), // Can Dragó: Fútbol, Básquet, Pádel
            "center10" to listOf("sport5", "sport2", "sport3"), // Club Natació: Natación, Pádel, Tenis
            "center11" to listOf("sport3"), // Club Tennis La Salut: Solo Tenis
            "center12" to listOf("sport4", "sport6", "sport8"), // Palau Sant Jordi: Básquet, Voleibol, Rugby
            "center13" to listOf("sport1", "sport4", "sport7"), // Can Ricart: Fútbol, Básquet, Hockey
            "center14" to listOf("sport4", "sport6"), // Pavelló Olímpic: Básquet, Voleibol
            "center15" to listOf("sport1", "sport3", "sport4", "sport7") // Laietà: Fútbol, Tenis, Básquet, Hockey
        )

        // 🔹 Insertar centros en la colección "centers"
        centers.forEach { center ->
            val centerRef = centersCollection.document(center.id)
            centerRef.set(center).await()

            println("📌 Centro agregado: ${center.name}")

            // 🔹 Insertar deportes en la subcolección "sports" dentro del centro
            centerSportsMap[center.id]?.forEach { sportId ->
                val sport = sports.find { it.id == sportId } ?: return@forEach
                centerRef.collection("sports").document(sport.id).set(sport).await()
                println("   ➡ Agregado deporte ${sport.name} a ${center.name}")
            }
        }

        println("🔥 Datos insertados exitosamente en Firestore")
    } catch (e: Exception) {
        println("❌ Error en Firestore: ${e.message}")
    }
}
