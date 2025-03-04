package com.example.ipsports.View.Event.pg3

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Place
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Sports
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ipsports.View.Event.ReusableEvent.EventCreationProgressBar
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.ViewModel.ui.EventViewModel
import com.example.ipsports.data.model.Event
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.remember


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun EventSummaryScreen(
    sport: String,
    date: String,
    location: String,
    maxParticipants: Int,
    selectedCourt: String,
    friends: List<String>,
    navController: NavController,
    currentPage: Int = 4,
    totalPages: Int = 4,
    onBack: () -> Unit = {}
) {

    val viewModel: EventViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Resumen del Evento", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF337C8D), // Azul verdoso claro
                            Color(0xFF15272D), // Azul grisáceo oscuro
                            Color(0xFF17272B)  // Casi negro
                        )
                    )
                )
                .padding(padding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // 🔹 Barra de progreso del evento
                EventCreationProgressBar(currentPage = currentPage, totalPages = totalPages)

                Spacer(modifier = Modifier.height(35.dp))

                // 🔹 Tarjeta de resumen del evento
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.3f))
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

                        InfoRow("Deporte", sport, Icons.Default.Sports)
                        InfoRow("Fecha", date, Icons.Default.CalendarToday)
                        InfoRow("Lugar", location, Icons.Default.Place)
                        InfoRow("Máx. Participantes", maxParticipants.toString(), Icons.Default.People)
                        InfoRow("Cancha", selectedCourt, Icons.Default.Event)

                        Text(
                            text = "Amigos Invitados:",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White,
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
                                            containerColor = Color(0xFF2E7D32)
                                        )
                                    )
                                }
                            }
                        } else {
                            Text("Ninguno", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    ButtonPrimary(
                        text = "Confirmar Evento",
                        onClick = {
                            val event = Event(
                                sportId = sport,
                                centerId = location,
                                userId = FirebaseAuth.getInstance().currentUser?.uid ?: "",
                                date = Timestamp.now(),
                                maxParticipants = maxParticipants,
                                usersInvited = friends,
                                status = "pending"
                            )

                            viewModel.addEvento(event) { // ✅ Ahora usa `addEvento()` para guardar en Firestore
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("✅ ¡Evento creado exitosamente!")
                                    navController.navigate("home") // ✅ Navegar solo después de que se guarde en Firestore
                                }
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(250.dp)
                    )

                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

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
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("$label: $value", style = MaterialTheme.typography.bodyLarge, color = Color.White)
    }
}

