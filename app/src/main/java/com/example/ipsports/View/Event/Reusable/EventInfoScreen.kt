package com.example.ipsports.View.Event.Reusable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ipsports.View.Reusable.ButtonPrimary
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Group
import com.example.ipsports.ui.theme.IpSportsTheme


@Composable
fun EventInfoScreen(onContinue: () -> Unit) {
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var maxParticipants by remember { mutableStateOf("") }
    var rentCourt by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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

        // Alquiler de Cancha
        LabeledSwitch(
            label = "Alquiler de Cancha",
            isChecked = rentCourt,
            onCheckedChange = { rentCourt = it }
        )

        Spacer(modifier = Modifier.weight(1f))

        // Botón Continuar
        ButtonPrimary(
            text = "Continuar",
            onClick = onContinue,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Preview(showBackground = true)
@Composable
fun EventInfoScreenPreview() {
    IpSportsTheme {
        EventInfoScreen(onContinue = { println("Continuar a la próxima pantalla") })
    }
}
