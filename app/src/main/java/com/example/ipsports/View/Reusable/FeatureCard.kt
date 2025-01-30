package com.example.ipsports.View.Reusable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipsports.R
import com.example.ipsports.ui.theme.IpSportsTheme

@Composable
fun FeatureCard(
    imageRes: Int,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp) // Tamaño más compacto
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // Gradiente sobre la imagen
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0xFF457B9D).copy(alpha = 0.6f) // Azul profundo semitransparente
                        )
                    )
                )
        )

        // Título en la parte inferior
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun FeatureCardPreview() {
    IpSportsTheme {
        FeatureCard(
            imageRes = R.drawable.brc,
            title = "Create Your Own Event",
            onClick = {}
        )
    }
}
