package com.example.ipsports.core.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun LoginInsertCredentials(){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

Column(
    //hace que la Column ocupe todo el espacio disponible del contenedor padre, tanto en ancho como en alto.
    modifier = Modifier.fillMaxSize()
        .padding(16.dp),
    //Este parámetro se usa para definir cómo se deben organizar verticalmente los elementos dentro de la Column.
    //Arrangement.Center Centra todos los elementos hijos verticalmente dentro de la Column
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
    //horizontalAlignment: Este parámetro define cómo se deben alinear horizontalmente todos los elementos hijos dentro de la Column.
    //Alignment.CenterHorizontally: Centra todos los elementos hijos horizontalmente dentro de la Column. Esto significa que cada elemento hijo estará
    //  centrado horizontalmente respecto a la anchura de la columna.

) {
    Text(text = "IPSports",
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(16.dp))
    Text(text="Log in ", fontSize = 20.sp)
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
        value = email,
        onValueChange = { email = it},
        label = { Text(text = "mail") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
     modifier = Modifier.fillMaxWidth(),
    singleLine = true
    )
}
}