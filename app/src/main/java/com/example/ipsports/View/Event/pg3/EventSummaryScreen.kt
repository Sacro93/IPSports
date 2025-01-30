package com.example.ipsports.View.Event.pg3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.ui.theme.IpSportsTheme

@Composable
fun EventSummaryAndConfirmationScreen(
    sport: String,
    date: String,
    location: String,
    maxParticipants: Int,
    selectedCourt: String,
    friends: List<String>,
    onEdit: () -> Unit,
    onConfirm: () -> Unit,
    onShare: () -> Unit,
    onReturnToHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título
        Text(
            text = "Resumen del Evento",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Información del evento
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Deporte: $sport", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Fecha: $date", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Lugar: $location", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Participantes Máximos: $maxParticipants",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Cancha: $selectedCourt", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Amigos Invitados: ${if (friends.isNotEmpty()) friends.joinToString(", ") else "Ninguno"}",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botones de acción
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ButtonPrimary(
                text = "Editar",
                onClick = onEdit,
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            )
            ButtonPrimary(
                text = "Confirmar",
                onClick = onConfirm,
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun EventSummaryAndConfirmationPreview() {
    IpSportsTheme {
        EventSummaryAndConfirmationScreen(
            sport = "Fútbol",
            date = "27 de Enero, 2025",
            location = "Club Deportivo Las Palmas",
            maxParticipants = 10,
            selectedCourt = "Cancha 1",
            friends = listOf("Juan", "Ana", "Carlos"),
            onEdit = { println("Editar evento") },
            onConfirm = { println("Evento confirmado") },
            onShare = { println("Compartir evento") },
            onReturnToHome = { println("Volver al inicio") }
        )
    }
}

