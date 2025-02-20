package com.example.ipsports.View.Reusable

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LocationDropdown(
    selectedLocation: String,
    onLocationSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val locations = listOf("Barcelona") // 🔹 Lista con solo "Barcelona"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true }
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0D47A1)) // Azul oscuro
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = selectedLocation.ifEmpty { "Selecciona una ciudad" },
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color(0xFF1565C0)) // Azul medio
        ) {
            locations.forEach { location ->
                DropdownMenuItem(
                    text = { Text(location, color = Color.White) },
                    onClick = {
                        onLocationSelected(location)
                        expanded = false
                    }
                )
            }
        }
    }
}


