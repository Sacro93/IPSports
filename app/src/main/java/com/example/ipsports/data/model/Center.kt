package com.example.ipsports.data.model

data class Center(
    val id: String = "",   // ID del centro en Firestore
    val name: String = "", // Nombre del centro (Ej: "Camp Nou Sports Complex")
    val location: String = "" // Dirección del centro
)
