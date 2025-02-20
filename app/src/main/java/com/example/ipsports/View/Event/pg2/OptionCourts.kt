package com.example.ipsports.View.Event.pg2

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OptionCourts(
    selectedCourt: String?, // Cancha seleccionada
    courts: List<String>,   // Lista de canchas disponibles
    onCourtSelected: (String) -> Unit, // Acción cuando se selecciona una cancha
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) } // Estado del menú desplegable

    Column(modifier = modifier) {
        // Contenedor con borde
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), // Borde gris suave
                    shape = RoundedCornerShape(8.dp) // Esquinas redondeadas
                )
                .clickable { expanded = !expanded } // Abre/cierra el menú al tocar
                .padding(horizontal = 16.dp, vertical = 12.dp) // Espaciado interno
        ) {
            // Texto del selector
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedCourt ?: "Seleccionar Cancha",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f) // Ocupa el espacio restante
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Desplegar menú",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // Menú desplegable
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            courts.forEach { court ->
                DropdownMenuItem(
                    text = { Text(court) },
                    onClick = {
                        onCourtSelected(court) // Actualiza la cancha seleccionada
                        expanded = false // Cierra el menú
                    }
                )
            }
        }
    }
}
