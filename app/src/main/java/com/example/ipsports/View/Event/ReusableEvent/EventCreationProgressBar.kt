package com.example.ipsports.View.Event.ReusableEvent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EventCreationProgressBar(currentPage: Int, totalPages: Int) {
    val progress = currentPage.toFloat() / totalPages

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(3.dp)) // Borde negro
            .clip(RoundedCornerShape(3.dp))
            .background(Color.White) // Fondo blanco
    ) {
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = Color.White, // Barra de progreso blanca
            trackColor = Color(0xFF333333) // Gris oscuro para mejor contraste
        )
    }
}


@Preview(showBackground = true)
@Composable
fun EventCreationProgressBarPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF17272B)) // Fondo oscuro para probar contraste
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔹 Barra Blanca con Borde Negro
        EventCreationProgressBar(currentPage = 2, totalPages = 5)

        // 🔹 Barra Invertida: Progreso Negro, Fondo Blanco
        EventCreationProgressBar(currentPage = 3, totalPages = 5)
    }
}
