package com.example.ipsports.View.Event.Reusable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipsports.R
import com.example.ipsports.ui.theme.IpSportsTheme

@Composable
fun SportSelectionCard(
    imageRes: Int,        // Recurso de la imagen del deporte
    isSelected: Boolean,  // Estado de selección
    onClick: () -> Unit   // Acción al hacer clic
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp) // Altura fija para la tarjeta
            .clip(MaterialTheme.shapes.medium)
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                else MaterialTheme.colorScheme.surface
            )
            .clickable(onClick = onClick) // Detecta clics en la tarjeta
    ) {
        // Imagen de fondo que se adapta a toda la caja
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop, // Recorta o escala la imagen para llenar la caja
            modifier = Modifier.fillMaxSize()
        )

        // Check o tilde para estado seleccionado
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.TopEnd) // Ubicación en la esquina superior derecha
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Icon(
                    imageVector = Icons.Default.Check, // Ícono de check
                    contentDescription = "Seleccionado",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SportCardPreview() {
    IpSportsTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            SportSelectionCard(
                imageRes = R.drawable.futboll, // Cambiar por un recurso válido
                isSelected = true,
                onClick = { println("Deporte seleccionado: Fútbol") }
            )


        }
    }
}

