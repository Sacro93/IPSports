import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
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
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título con ícono
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Event,
                    contentDescription = "Evento",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Resumen del Evento",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tarjeta con la información del evento
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    InfoRow("Deporte", sport, Icons.Default.SportsSoccer)
                    InfoRow("Fecha", date, Icons.Default.CalendarToday)
                    InfoRow("Lugar", location, Icons.Default.LocationOn)
                    InfoRow("Máx. Participantes", maxParticipants.toString(), Icons.Default.People)
                    InfoRow("Cancha", selectedCourt, Icons.Default.Place)

                    // Amigos invitados
                    Text(
                        text = "Amigos Invitados:",
                        style = MaterialTheme.typography.bodyLarge,
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
                                    label = { Text(friend) },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = null
                                        )
                                    }
                                )
                            }
                        }
                    } else {
                        Text("Ninguno", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botones de acción
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ButtonPrimary(
                    text = "Confirmar Evento",
                    onClick = onConfirm,
                    icon = Icons.Default.CheckCircle
                )

                ButtonPrimary(
                    text = "Editar Evento",
                    onClick = onEdit,
                    icon = Icons.Default.Edit
                )

                ButtonSecondary(
                    text = "Compartir Evento",
                    onClick = onShare,
                    icon = Icons.Default.Share
                )
            }
        }
    }
}

// Función para mostrar una fila de información con ícono
@Composable
fun InfoRow(label: String, value: String, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.width(8.dp))
        Text("$label: $value", style = MaterialTheme.typography.bodyLarge)
    }
}

// Componente para botones principales
@Composable
fun ButtonPrimary(text: String, onClick: () -> Unit, icon: ImageVector? = null) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        icon?.let {
            Icon(imageVector = it, contentDescription = null, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(text, fontWeight = FontWeight.Bold)
    }
}

// Componente para botones secundarios
@Composable
fun ButtonSecondary(text: String, onClick: () -> Unit, icon: ImageVector? = null) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        icon?.let {
            Icon(imageVector = it, contentDescription = null, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(text, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun EventSummaryAndConfirmationPreview() {
    MaterialTheme {
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
