package com.example.ipsports.data.model

data class Center(
    val id: String = "", // Usaremos Firestore para generar el ID
    val name: String = "",
    val sports: List<String> = emptyList(), // Almacenará los IDs de los deportes
    val location: String = ""
)