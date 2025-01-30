package com.example.ipsports.View.Reusable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.ipsports.ui.theme.IpSportsTheme


//Botón estilizado para acciones principales (Ej.: "Iniciar Sesión", "Crear Evento").

@Composable
fun ButtonPrimary(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isOutlined: Boolean = false // Define si el botón es de tipo contorno o sólido
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isOutlined) Color.Transparent else MaterialTheme.colorScheme.onSurface,
            contentColor = if (isOutlined) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        ),
        shape = RoundedCornerShape(24.dp), // Bordes más redondeados
        border = if (isOutlined) {
            BorderStroke(
                width = 2.dp,
                color = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            )
        } else null // Sin borde si no es outlined
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isOutlined) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ButtonPrimaryPreview() {
    IpSportsTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Botón sólido
            ButtonPrimary(
                text = "Complete Setup",
                onClick = {},
                isOutlined = false // Botón sólido
            )

            // Botón contorneado
            ButtonPrimary(
                text = "Start Investing",
                onClick = {},
                isOutlined = true // Botón outlined
            )
        }
    }
}
