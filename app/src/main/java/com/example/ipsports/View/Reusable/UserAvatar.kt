package com.example.ipsports.View.Reusable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

//Componente para mostrar la imagen o iniciales del usuario.

@Composable
fun UserAvatar(
    initials: String,        // Iniciales del usuario
    userImage: Painter? = null, // Imagen opcional
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        if (userImage != null) {
            // Mostrar la imagen del usuario si está disponible
            Image(
                painter = userImage,
                contentDescription = "Foto de usuario",
                contentScale = ContentScale.Crop, // Ajustar al tamaño del círculo
                modifier = Modifier.fillMaxSize()
            )
        } else {
            // Mostrar las iniciales del usuario si no hay imagen
            Text(
                text = initials,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}


