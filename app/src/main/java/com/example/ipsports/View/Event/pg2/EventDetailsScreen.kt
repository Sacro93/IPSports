package com.example.ipsports.View.Event.pg2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.View.Reusable.InputField
import com.example.ipsports.ui.theme.IpSportsTheme


@Composable
    fun EventDetailsScreen(
        selectedSport: String,       // Deporte seleccionado previamente
        onContinue: () -> Unit       // Acción al presionar "Continuar"
    ) {
        var date by remember { mutableStateOf("") }
        var time by remember { mutableStateOf("") }
        var location by remember { mutableStateOf("") }
        var maxParticipants by remember { mutableStateOf(8) }
        var rentCourt by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            // Título
            Text(
                text = "Detalles del Evento ($selectedSport)",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Fecha y Hora
            InputField(
                value = date,
                onValueChange = { date = it },
                label = "Fecha del Evento",
                placeholder = "DD/MM/AAAA",
                leadingIcon = {
                    Icon(Icons.Default.DateRange, contentDescription = "Fecha")
                },
                modifier = Modifier.padding(bottom = 16.dp)
            )

            InputField(
                value = time,
                onValueChange = { time = it },
                label = "Hora del Evento",
                placeholder = "HH:MM",
                leadingIcon = {
                    Icon(Icons.Default.AccessTime, contentDescription = "Hora")
                },
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Ubicación
            InputField(
                value = location,
                onValueChange = { location = it },
                label = "Ubicación del Evento",
                placeholder = "Ingrese una dirección",
                leadingIcon = {
                    Icon(Icons.Default.LocationOn, contentDescription = "Ubicación")
                },
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Participantes Máximos
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Participantes Máximos",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                TextButton(onClick = { if (maxParticipants > 2) maxParticipants-- }) {
                    Text("-")
                }
                Text(
                    text = maxParticipants.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                TextButton(onClick = { maxParticipants++ }) {
                    Text("+")
                }
            }

            // Alquiler de Cancha
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Alquiler de Cancha",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = rentCourt,
                    onCheckedChange = { rentCourt = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        uncheckedThumbColor = MaterialTheme.colorScheme.onSurface
                    )
                )
            }

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
fun EventDetailsScreenPreview() {
    IpSportsTheme {
        EventDetailsScreen(
            selectedSport = "Fútbol",
            onContinue = { println("Continuar con los detalles del evento") }
        )
    }
}
