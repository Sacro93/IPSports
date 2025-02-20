package com.example.ipsports.View.Event.pg2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipsports.View.Event.ReusableEvent.EventCreationProgressBar
import com.example.ipsports.View.Event.ReusableEvent.EventInputField
import com.example.ipsports.View.Event.ReusableEvent.FieldType
import com.example.ipsports.View.Reusable.ButtonPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventInfoScreen(
    onContinue: () -> Unit,
    selectedCourt: String?,
    courts: List<String>,
    onCourtSelected: (String) -> Unit,
    onBack: () -> Unit = {}
) {
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var maxParticipants by remember { mutableStateOf("") }
    var selectedCourt by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var showWarning by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Spacer(modifier = Modifier.height(32.dp))

            TopAppBar(
                title = { Text("Información del Evento", color = Color.White) },
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
                Spacer(modifier = Modifier.height(24.dp))

                EventCreationProgressBar(currentPage = 2, totalPages = 4)

                Spacer(modifier = Modifier.height(32.dp))

                // 📆 **Campos de Información del Evento**
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        EventInputField(
                            label = "Fecha del Evento",
                            value = date,
                            onValueChange = { date = it },
                            fieldType = FieldType.Date,
                            leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Fecha") }
                        )

                        EventInputField(
                            label = "Hora del Evento",
                            value = time,
                            onValueChange = { time = it },
                            fieldType = FieldType.Time,
                            leadingIcon = { Icon(Icons.Default.AccessTime, contentDescription = "Hora") }
                        )

                        EventInputField(
                            label = "Dirección",
                            value = address,
                            onValueChange = { address = it },
                            fieldType = FieldType.Text,
                            leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = "Dirección") }
                        )

                        EventInputField(
                            label = "Participantes Máximos",
                            value = maxParticipants,
                            onValueChange = { maxParticipants = it },
                            fieldType = FieldType.Text,
                            leadingIcon = { Icon(Icons.Default.Group, contentDescription = "Participantes") }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 🏟️ **Selector de Cancha**
                OptionCourts(
                    selectedCourt = selectedCourt,
                    courts = courts,
                    onCourtSelected = { selectedCourt = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // 🏆 **Botón para Agregar Amigos**
                ButtonPrimary(
                    text = "Agregar Amigos",
                    onClick = { /* Lógica para abrir selección de amigos */ },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(180.dp)
                )

                Spacer(modifier = Modifier.height(180.dp))

                // ✔ **Botón de Confirmar**
                ButtonPrimary(
                    text = "Confirmar",
                    onClick = onContinue,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(250.dp)
                )


            }
        }
    }
}

