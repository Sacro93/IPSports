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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


//Componente para mostrar un amigo en una lista, se usara
// Una lista de amigos para invitarlos a eventos.
//Una sección de amigos conectados o contactos frecuentes..


@Composable
fun FriendItem(
    name: String,
    avatarUrl: String? = null, // Imagen del avatar (opcional, será una inicial si no hay imagen)
    isInvited: Boolean = false, // Estado: si el amigo ya está invitado
    onInviteClick: () -> Unit = {}, // Acción al invitar
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onInviteClick() }, // Acción principal al tocar el elemento
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
            )
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = name.firstOrNull()?.toString()?.uppercase() ?: "?",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        // Espaciado entre avatar y nombre
        Spacer(modifier = Modifier.width(12.dp))

        // Nombre del amigo
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f) // Rellenar el espacio restante
        )

        // Estado de invitación (Botón o texto de estado)
        if (isInvited) {
            Text(
                text = "Invitado",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            IconButton(onClick = { onInviteClick() }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Invitar a $name",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun FriendItemPreview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FriendItem(
            name = "Juan Pérez",
            avatarUrl = null,
            isInvited = false,
            onInviteClick = {}
        )
        FriendItem(
            name = "Ana Gómez",
            avatarUrl = null,
            isInvited = true,
            onInviteClick = {}
        )
    }
}
