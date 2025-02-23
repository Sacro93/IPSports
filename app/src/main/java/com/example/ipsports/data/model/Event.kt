package com.example.ipsports.data.model

import com.google.firebase.Timestamp


data class Event(
    val id: String = "", // 🔹 Firestore lo autogenera si se deja vacío
    val sportId: String = "",
    val courtId: String = "",
    val userId: String = "", // 🔹 Usuario que crea el evento
    val date: Timestamp = Timestamp.now(),
    val usersInvited: List<String> = emptyList(), // 🔹 Lista de amigos invitados (IDs de usuario)
    val status: String = "Pendiente"
)

