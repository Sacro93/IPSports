package com.example.ipsports.View.Event.pg2

import android.app.TimePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ipsports.View.Event.ReusableEvent.EventCreationProgressBar
import com.example.ipsports.View.Event.ReusableEvent.EventInputField
import com.example.ipsports.View.Event.ReusableEvent.FieldType
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.ViewModel.ui.CenterViewModel
import com.example.ipsports.data.model.Center
import com.example.ipsports.data.model.Event
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.google.firebase.Timestamp
import android.app.DatePickerDialog
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventInfoScreen(
    sportId: String,
    onContinue: (Event) -> Unit,
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    //  ViewModel para obtener los centros deportivos
    val centerViewModel: CenterViewModel = hiltViewModel()
    val centersWithSports by centerViewModel.centersWithSports.collectAsStateWithLifecycle()

    //  Estados de datos ingresados por el usuario
    var dateTime by remember { mutableStateOf<Date?>(null) }
    var maxParticipants by remember { mutableStateOf("") }
    var selectedCenter by remember { mutableStateOf<Center?>(null) }
    var invitedFriends by remember { mutableStateOf("") }
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    //  Filtrar los centros deportivos que tienen el deporte seleccionado
    val availableCenters by remember(sportId) {
        derivedStateOf {
            centersWithSports.filter { it.sports.any { sport -> sport.id == sportId } }
                .map { it.center }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Evento de ${sportId.uppercase()}", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
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
                            Color(0xFF337C8D),
                            Color(0xFF15272D),
                            Color(0xFF17272B)
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

                EventCreationProgressBar(currentPage = 3, totalPages = 4)

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Selecciona los detalles del evento",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        // 📅 Selección de Fecha y Hora del Evento
                        EventInputField(
                            label = "Fecha y Hora del Evento",
                            value = dateTime?.let { SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(it) } ?: "",
                            onValueChange = {},
                            fieldType = FieldType.Date,
                            leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Fecha y Hora") },
                            onClick = {
                                val newCalendar = Calendar.getInstance()

                                // 📅 Primero abrimos el DatePickerDialog
                                DatePickerDialog(
                                    context,
                                    { _, year, month, day ->
                                        newCalendar.set(year, month, day)

                                        // ⏰ Luego abrimos el TimePickerDialog cuando el usuario selecciona la fecha
                                        TimePickerDialog(
                                            context,
                                            { _, hour, minute ->
                                                newCalendar.set(Calendar.HOUR_OF_DAY, hour)
                                                newCalendar.set(Calendar.MINUTE, minute)

                                                // ✅ Ahora `dateTime` almacena la fecha y la hora combinadas
                                                dateTime = newCalendar.time
                                            },
                                            newCalendar.get(Calendar.HOUR_OF_DAY),
                                            newCalendar.get(Calendar.MINUTE),
                                            true
                                        ).show()
                                    },
                                    newCalendar.get(Calendar.YEAR),
                                    newCalendar.get(Calendar.MONTH),
                                    newCalendar.get(Calendar.DAY_OF_MONTH)
                                ).show()
                            }
                        )

                        // 📍 Selección de Centro Deportivo
                        OptionCenters(
                            selectedCenter = selectedCenter,
                            centers = availableCenters,
                            onCenterSelected = { selectedCenter = it }
                        )

                        // 👥 Selección de Participantes Máximos
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

                // ✔ **Botón de Confirmar**
                ButtonPrimary(
                    text = "Confirmar",
                    onClick = {
                        if (dateTime != null && selectedCenter != null) {
                            val event = Event(
                                sportId = sportId,
                                centerId = selectedCenter!!.id,
                                userId = userId,
                                date = Timestamp(dateTime!!),
                                maxParticipants = maxParticipants.toIntOrNull() ?: 0,
                                invitedUsers = invitedFriends.split(",").map { it.trim() },
                                status = "pending"
                            )
                            onContinue(event)
                        }
                    },
                    enabled = dateTime != null && selectedCenter != null
                )
            }
        }
    }
}



