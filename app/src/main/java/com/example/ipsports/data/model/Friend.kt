package com.example.ipsports.data.model

data class Friend(
    val id: String = "",       // ID del amigo (userId en Firestore)
    val name: String = "",     // Nombre del amigo
    val email: String = "",    // Correo del amigo
    val profileImageUrl: String? = null // Foto de perfil (opcional)
)