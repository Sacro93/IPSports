package com.example.ipsports.View.Reusable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview


//Botón estilizado para acciones principales (Ej.: "Iniciar Sesión", "Crear Evento").
@Composable
fun ButtonPrimary(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isOutlined: Boolean = false, // Define si el botón es contorneado
    useGradient: Boolean = false // Activa degradado
) {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF337C8D), // Azul verdoso claro
            Color(0xFF1B5E20)  // Verde oscuro
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                brush = if (useGradient && !isOutlined) gradientBrush else SolidColor(Color.Transparent),
                shape = RoundedCornerShape(24.dp)
            )
            .border(
                width = 2.dp, // Siempre visible
                color = if (isOutlined) MaterialTheme.colorScheme.primary else Color.White, // 🔹 Borde SIEMPRE Blanco
                shape = RoundedCornerShape(24.dp)
            )
    ) {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = Modifier.fillMaxSize(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent, // Se usa `Box` para el fondo
                contentColor = if (isOutlined) MaterialTheme.colorScheme.primary else Color.White,
                disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            ),
            shape = RoundedCornerShape(24.dp),
            border = null // El borde ya está en `Box`
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = if (isOutlined) MaterialTheme.colorScheme.primary else Color.White
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun ButtonPrimaryPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF17272B)) // Simula un fondo oscuro
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔹 Botón sólido tradicional
        ButtonPrimary(text = "Confirmar", onClick = {}, isOutlined = false, useGradient = false)

        // 🔹 Botón con degradado
        ButtonPrimary(text = "Confirmar con Degradado", onClick = {}, useGradient = true)

        // 🔹 Botón outlined (solo borde)
        ButtonPrimary(text = "Editar Evento", onClick = {}, isOutlined = true)
    }
}
