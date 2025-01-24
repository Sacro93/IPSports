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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipsports.View.LoginScreen
import com.example.ipsports.ui.theme.IpSportsTheme

//Componente genérico para campos de texto (email, contraseña, detalles del evento).

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String, // Etiqueta del campo
    placeholder: String = "", // Placeholder opcional
    leadingIcon: (@Composable (() -> Unit))? = null, // Ícono opcional
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp), // Forma del campo
    singleLine: Boolean = true // Define si es de una sola línea
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = White) }, // Color blanco para la etiqueta
        placeholder = { if (placeholder.isNotEmpty()) Text(text = placeholder, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)) },
        leadingIcon = leadingIcon, // Ícono opcional
        shape = shape, // Forma personalizada
        singleLine = singleLine,
        modifier = modifier
            .fillMaxWidth()
            .border(2.dp, MaterialTheme.colorScheme.primary, shape), // Borde externo
        textStyle = TextStyle(
            color = White, // Color del texto que escribe el usuario
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent, // Fondo desenfocado
            focusedContainerColor = Color.Transparent, // Fondo enfocado
            cursorColor = MaterialTheme.colorScheme.primary, // Color del cursor
            focusedIndicatorColor = Color.Transparent, // Indicador enfocado (línea interna)
            unfocusedIndicatorColor = Color.Transparent, // Indicador desenfocado (línea interna)
            disabledIndicatorColor = Color.Transparent, // Indicador deshabilitado
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