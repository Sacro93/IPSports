package com.example.ipsports.View.Event.Reusable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ipsports.View.Reusable.ButtonPrimary

@Composable
fun EventSummaryScreen(onConfirm: () -> Unit, onDelete: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = "Resumen del Evento", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Resumen
        Text(text = "Nombre: Torneo de Pádel")
        Text(text = "Deporte: Pádel")
        Text(text = "Fecha: 28/01/2025")
        Text(text = "Participantes Máximos: 8")

        Spacer(modifier = Modifier.weight(1f))
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            ButtonPrimary(text = "Confirmar", onClick = onConfirm)
            ButtonPrimary(text = "Eliminar", onClick = onDelete)
        }
    }
}
