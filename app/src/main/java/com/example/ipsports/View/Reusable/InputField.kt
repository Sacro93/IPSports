package com.example.ipsports.View.Reusable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipsports.View.LoginScreen
import com.example.ipsports.ui.theme.IpSportsTheme

//Componente genérico para campos de texto (email, contraseña, detalles del evento).

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None, // Visual Transformation
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = White) }, // Etiqueta con color blanco
        placeholder = { if (placeholder.isNotEmpty()) Text(text = placeholder, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)) },
        leadingIcon = leadingIcon,
        shape = shape, // Forma personalizada del borde
        singleLine = singleLine,
        visualTransformation = visualTransformation, // Aplica visualTransformation aquí
        modifier = modifier
            .fillMaxWidth()
            .border(2.dp, MaterialTheme.colorScheme.primary, shape), // Borde externo
        textStyle = TextStyle(
            color = White, // Color del texto
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent, // Fondo desenfocado
            focusedContainerColor = Color.Transparent,   // Fondo enfocado
            cursorColor = MaterialTheme.colorScheme.primary, // Color del cursor
            focusedIndicatorColor = MaterialTheme.colorScheme.primary, // Indicador enfocado (línea interna)
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), // Indicador desenfocado
            errorIndicatorColor = MaterialTheme.colorScheme.error // Indicador en caso de error
        )
    )
}


@Preview
@Composable
fun InputFieldPreview() {
    IpSportsTheme {
        InputField(
            value = "",
            onValueChange = {},
            label = "Correo Electrónico",
            placeholder = "Escribe tu correo..."
        )
    }
}