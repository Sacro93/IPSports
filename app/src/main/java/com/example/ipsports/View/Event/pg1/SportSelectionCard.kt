package com.example.ipsports.View.Event.pg1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipsports.R
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import com.example.ipsports.data.model.Sport


@Composable
fun SportSelectionCard(
    sport: Sport,  // ✅ Recibe el deporte
    isSelected: Boolean,  // ✅ Indica si está seleccionado
    onClick: (Sport) -> Unit  // ✅ Devuelve el deporte seleccionado
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                else MaterialTheme.colorScheme.surface
            )
            .clickable { onClick(sport) } // ✅ Pasamos el deporte seleccionado
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Imagen de fondo según el deporte
            Image(
                painter = painterResource(id = getSportDrawable(sport.name)), // ✅ Obtiene el drawable correcto
                contentDescription = sport.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )


            // Degradado en la parte inferior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                        )
                    )
            )

            // Nombre del deporte
            Text(
                text = sport.name,
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 8.dp)
            )

            // Icono de selección si está seleccionado
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Seleccionado",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

fun getSportDrawable(sportName: String): Int {
    return when (sportName.lowercase()) { // 🔹 Convertimos el nombre a minúsculas para evitar errores
        "fútbol" -> R.drawable.futboll
        "basketball" -> R.drawable.basket
        "tenis" -> R.drawable.tennis
        "padel" -> R.drawable.paddle
        else -> R.drawable.grupo // 🔹 Imagen por defecto si el deporte no tiene asignada una
    }
}


/*
@Composable
fun SportSelectionCard(
    sport: Sport,         // ✅ Recibe un objeto ListSport completo
    isSelected: Boolean,      // Estado de selección
    onClick: () -> Unit       // Acción al hacer clic
) {
    Surface( // Usar Surface en lugar de Box para mejor manejo de estilos MaterialTheme
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp) // Altura fija para la tarjeta (ajustada un poco más alta para el texto)
            .clip(MaterialTheme.shapes.medium)
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                else MaterialTheme.colorScheme.surface
            )
            .clickable(onClick = onClick)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Imagen de fondo
            sport.iconoDrawableResId?.let { drawableId -> // ✅ Usa iconoDrawableResId de ListSport (maneja nulo)
                Image(
                    painter = painterResource(id = drawableId),
                    contentDescription = sport.nombre, // ✅ Usa sport.nombre para contentDescription
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Box( // Degradado semi-transparente en la parte inferior
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp) // Altura del degradado
                    .align(Alignment.BottomCenter)
                    .background(
                        androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)) // Degradado de transparente a negro semi-transparente
                        )
                    )
            )

            Text( // Nombre del deporte
                text = sport.nombre, // ✅ Usa sport.nombre para el texto
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 8.dp)
            )


            // Check o tilde para estado seleccionado
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Seleccionado",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}


*/