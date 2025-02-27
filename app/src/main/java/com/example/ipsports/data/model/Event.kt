package com.example.ipsports.data.model

import com.google.firebase.Timestamp


data class Event(
    val id: String = "",             // ID del evento en Firestore
    val sportId: String = "",         // ID del deporte elegido
    val centerId: String = "",        // ID del centro donde se juega
    val courtId: String = "",         // ID de la cancha asignada
    val userId: String = "",          // ID del organizador del evento
    val date: Timestamp = Timestamp.now(), // Fecha y hora del evento
    val maxParticipants: Int = 0,      // Número máximo de jugadores
    val status: String = "pending",   // "pending" | "finished"
    val invitedUsers: List<String> = emptyList() // Lista de IDs de usuarios invitados
)