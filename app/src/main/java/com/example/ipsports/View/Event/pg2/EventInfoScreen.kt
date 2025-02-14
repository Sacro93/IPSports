package com.example.ipsports.View.Event.pg2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
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
import com.example.ipsports.View.Event.ReusableEvent.OptionCourts
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.ui.theme.IpSportsTheme


@Composable
fun EventInfoScreen(
    onContinue: () -> Unit,
    selectedCourt: String?, // Cancha seleccionada
    courts: List<String>,   // Lista de canchas disponibles
    onCourtSelected: (String) -> Unit// Acción cuando se selecciona una cancha) 
) {
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var maxParticipants by remember { mutableStateOf("") }
    var rentCourt by remember { mutableStateOf(false) }
    var selectedCourt by remember { mutableStateOf<String?>(null) } // Estado de la cancha seleccionada
    val courts = listOf("Cancha 1", "Cancha 2", "Cancha 3") // Ejemplo de canchas disponibles
    var showDialog by remember { mutableStateOf(false) } // Estado para mostrar el pop-up
    var showWarning by remember { mutableStateOf(false) } // Estado para mostrar advertencia



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF337C8D), // Azul verdoso claro (parte superior)
                        Color(0xFF15272D), // Azul grisáceo oscuro (zona media)
                        Color(0xFF17272B)  // Casi negro (base)
                    )
                )
            )
        ,
        contentAlignment = Alignment.Center
    ){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Spacer(modifier = Modifier.height(16.dp))
        EventCreationProgressBar(currentPage = 2, totalPages = 4)
        Spacer(modifier = Modifier.height(25
            .dp))
        // Título
        Text(
            text = "Información del Evento",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo para Fecha
        EventInputField(
            label = "Fecha del Evento",
            value = date,
            onValueChange = { date = it },
            fieldType = FieldType.Date,
            leadingIcon = {
                Icon(Icons.Default.DateRange, contentDescription = "Fecha")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo para Hora
        EventInputField(
            label = "Hora del Evento",
            value = time,
            onValueChange = { time = it },
            fieldType = FieldType.Time,
            leadingIcon = {
                Icon(Icons.Default.AccessTime, contentDescription = "Hora")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Dirección
        EventInputField(
            label = "Dirección",
            value = address,
            onValueChange = { address = it },
            fieldType = FieldType.Text,
            leadingIcon = {
                Icon(Icons.Default.LocationOn, contentDescription = "Dirección")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Participantes Máximos
        EventInputField(
            label = "Participantes Máximos",
            value = maxParticipants,
            onValueChange = { maxParticipants = it },
            fieldType = FieldType.Text,
            leadingIcon = { Icon(Icons.Default.Group, contentDescription = "Participantes") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Selector de Cancha
        OptionCourts(
            selectedCourt = selectedCourt,
            courts = courts,
            onCourtSelected = { selectedCourt = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        ButtonPrimary(
            text = "Agregar Amigos",
            onClick = { /* Lógica para abrir pantalla de selección de amigos */ },
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)

        )

        Spacer(modifier = Modifier.weight(1f))

        // Botón Continuar
        ButtonPrimary(
            text = "Confirmar",
            onClick = onContinue,
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        )



        // 📌 Diálogo de Confirmación
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Confirmar Evento") },
                text = { Text("¿Estás seguro de que quieres confirmar este evento?") },
                confirmButton = {
                    ButtonPrimary(
                        text = "Sí, Confirmar",
                        onClick = {

                            showDialog = false
                        }
                    )
                },
                dismissButton = {
                    ButtonPrimary(
                        text = "Cancelar",
                        onClick = { showDialog = false },
                        isOutlined = true
                    )
                }
            )
        }

        // 📌 Mensaje de Advertencia (Si no hay amigos)
        if (showWarning) {
            AlertDialog(
                onDismissRequest = { showWarning = false },
                title = { Text("Agregar Amigos") },
                text = { Text("No has agregado amigos al evento. ¿Quieres agregarlos ahora?") },
                confirmButton = {
                    ButtonPrimary(
                        text = "Sí, Agregar Amigos",
                        onClick = {
                            //onAddFriends() // Redirigir al usuario a la pantalla de agregar amigos
                            showWarning = false
                        }
                    )
                },
                dismissButton = {
                    ButtonPrimary(
                        text = "Omitir",
                        onClick = { showWarning = false },
                        isOutlined = true
                    )
                }
            )
        }
    }
}
}
@Preview(showBackground = true)
@Composable
fun EventInfoScreenPreview() {
    IpSportsTheme {
        EventInfoScreen(
            onContinue = { println("Continuar a la próxima pantalla") },
            selectedCourt = "Cancha 1", // Valor predeterminado
            courts = listOf("Cancha 1", "Cancha 2", "Cancha 3"), // Lista de canchas ficticias
            onCourtSelected = { println("Cancha seleccionada: $it") } // Acción ficticia
        )
    }
}

