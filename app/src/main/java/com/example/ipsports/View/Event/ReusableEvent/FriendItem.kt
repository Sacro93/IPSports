package com.example.ipsports.View.Event.ReusableEvent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FriendItem(
    name: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() }
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
        Spacer(modifier = Modifier.weight(1f))
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onSelect() },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.White,  // ✅ Fondo Blanco cuando está seleccionado
                uncheckedColor = Color.White, // 🔹 Fondo sin color cuando no está seleccionado
                checkmarkColor = Color.Black, // ✔ Checkmark Negro para más contraste
                disabledCheckedColor = Color.Gray.copy(alpha = 0.5f), // 🔹 Opacidad si está deshabilitado
                disabledUncheckedColor = Color.Transparent // 🔹 Nada visible si está deshabilitado
            ),
            modifier = Modifier
                .size(18.dp) // 🔹 Tamaño más pequeño pero visible
        )
    }
}



@Preview(showBackground = true)
@Composable
fun FriendItemPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF15272D))
            .padding(16.dp)
    ) {
        FriendItem(name = "Juan", isSelected = true, onSelect = {})
        FriendItem(name = "María", isSelected = false, onSelect = {})
        FriendItem(name = "Carlos", isSelected = true, onSelect = {})
        FriendItem(name = "Ana", isSelected = false, onSelect = {})
    }
}


