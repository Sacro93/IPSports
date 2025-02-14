package com.example.ipsports.View.Event.ReusableEvent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material.icons.filled.Person
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EventSummaryScreen(
    sport: String,
    date: String,
    location: String,
    maxParticipants: Int,
    selectedCourt: String,
    friends: List<String>,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF22333B).copy(alpha = 0.85f) // Azul grisáceo oscuro, translúcido
        ),
        border = BorderStroke(1.dp, Color(0xFF76A9A0)) // Borde verde perlado sutil
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Resumen del Evento",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFFBFD7EA) // Azul claro que contrasta con fondo oscuro
            )

            InfoRow("Deporte", sport, Icons.Default.SportsSoccer)
            InfoRow("Fecha", date, Icons.Default.CalendarToday)
            InfoRow("Lugar", location, Icons.Default.Place)
            InfoRow("Máx. Participantes", maxParticipants.toString(), Icons.Default.People)
            InfoRow("Cancha", selectedCourt, Icons.Default.Event)

            Text(
                text = "Amigos Invitados:",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White, // Más visible en fondo oscuro
                fontWeight = FontWeight.Bold
            )

            if (friends.isNotEmpty()) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    friends.forEach { friend ->
                        AssistChip(
                            onClick = {},
                            label = { Text(friend, color = Color.White) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    tint = Color(0xFFBFD7EA) // Azul claro
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = Color(
                                    0xFF2E7D32
                                )
                            )
                        )
                    }
                }
            } else {
                Text("Ninguno", style = MaterialTheme.typography.bodyMedium, color = Color.White)
            }
        }
    }
}

// 🔹 **Mejorar `InfoRow` para que los iconos y texto se vean mejor en el nuevo fondo**
@Composable
fun InfoRow(label: String, value: String, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF76A9A0)
        ) // Verde perlado
        Spacer(modifier = Modifier.width(8.dp))
        Text("$label: $value", style = MaterialTheme.typography.bodyLarge, color = Color.White)
    }
}

//  la transparencia en el Card es que en Jetpack Compose Previews
//  el fondo por defecto es blanco, lo que impide que se vea el efecto traslúcido.
//  Pero en la app real sí se verá
//  si el fondo detrás es un gradiente oscuro.
@Preview(showBackground = true)
@Composable
fun EventSummaryScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF17272B)) // Simular fondo oscuro
            .padding(16.dp)
    ) {
        EventSummaryScreen(
            sport = "Fútbol",
            date = "27 de Enero, 2025",
            location = "Club Deportivo Las Palmas",
            maxParticipants = 10,
            selectedCourt = "Cancha 1",
            friends = listOf("Ana", "Luis", "María", "Juan")
        )
    }
}
