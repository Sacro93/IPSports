package com.example.ipsports.View.Reusable

//Un encabezado reutilizable con título o acciones (como botones "Atrás" o "Guardar").

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    title: String,
    onBackClick: (() -> Unit)? = null, // Callback para el botón "Atrás" (opcional)
    modifier: Modifier = Modifier,
    actions: @Composable (RowScope.() -> Unit)? = null // Acciones opcionales en el header
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        navigationIcon = {
            if (onBackClick != null) { // Solo muestra el botón si el callback no es nulo
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },

                actions = actions ?: {}, // Espacio para acciones adicionales
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    )
}
@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Column {
        // Header con botón "Atrás"
        Header(
            title = "Detalles del Evento",
            onBackClick = { /* Acción para el botón "Atrás" */ }
        )

        // Header con acciones personalizadas
        Header(
            title = "Perfil del Usuario",
            actions = {
                IconButton(onClick = { /* Acción Guardar */ }) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = "Guardar",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        )
    }
}
