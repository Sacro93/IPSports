package com.example.ipsports.View.Reusable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


//Componente para mostrar un amigo en una lista, se usara
// Una lista de amigos para invitarlos a eventos.
//Una sección de amigos conectados o contactos frecuentes..

@Composable
fun FriendListItem(
    name: String,
    description: String? = null,
    avatarUrl: String? = null,       // Imagen del avatar (opcional, será inicial si no hay imagen)
    isSelected: Boolean? = null,    // Null si no se usa selección, true/false para checkbox
    isInvited: Boolean? = null,     // Null si no se usa invitación, true/false para "Invitado"
    onItemClick: () -> Unit = {},   // Acción al tocar el elemento
    onToggleSelection: (() -> Unit)? = null, // Acción para seleccionar/deseleccionar
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick() }, // Acción principal al tocar el elemento
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar del amigo
        if (avatarUrl != null) {
            AsyncImage(
                model = avatarUrl,
                contentDescription = "Avatar de $name",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF457B9D)) // Fondo para la imagen (Azul profundo)
            )
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF00A8CC)) // Azul brillante
            ) {
                Text(
                    text = name.firstOrNull()?.toString()?.uppercase() ?: "?",
                    color = Color.White, // Blanco para iniciales
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        // Espaciado entre avatar y contenido
        Spacer(modifier = Modifier.width(12.dp))

        // Nombre y descripción del amigo
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White // Blanco para el nombre
            )
            if (description != null) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF90A4AE) // Gris claro para la descripción
                )
            }
        }

        // Estado dinámico: Checkbox para selección o texto/ícono para invitación
        when {
            isSelected != null -> {
                Checkbox(
                    checked = isSelected,
                    onCheckedChange = { onToggleSelection?.invoke() },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF00A8CC), // Azul brillante al seleccionar
                        uncheckedColor = Color(0xFF90A4AE), // Gris claro para no seleccionado
                        checkmarkColor = Color.White // Blanco para la marca de verificación
                    )
                )
            }
            isInvited == true -> {
                Text(
                    text = "Invitado",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF457B9D) // Azul profundo para el estado de invitado
                )
            }
            isInvited == false -> {
                IconButton(onClick = { onItemClick() }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Invitar a $name",
                        tint = Color(0xFF00A8CC) // Azul brillante para el botón de invitar
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FriendListItemPreview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FriendListItem(
            name = "Juan Pérez",
            description = "Jugador habitual",
            isSelected = true,
            onToggleSelection = { println("Seleccionado: Juan Pérez") }
        )
        FriendListItem(
            name = "Ana Gómez",
            description = "Jugador ocasional",
            isInvited = false,
            onItemClick = { println("Invitar a Ana Gómez") }
        )
        FriendListItem(
            name = "Pedro López",
            description = "Jugador frecuente",
            avatarUrl = null, // Sin imagen, mostrará iniciales
            isInvited = true // Invitado
        )
    }
}
