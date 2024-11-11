package com.example.ipsports.core.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation


@Composable
fun LoginInsertCredentials(navigateToHome: () -> Unit) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        //hace que la Column ocupe todo el espacio disponible del contenedor padre, tanto en ancho como en alto.
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        //Este parámetro se usa para definir cómo se deben organizar verticalmente los elementos dentro de la Column.
        //Arrangement.Center Centra todos los elementos hijos verticalmente dentro de la Column
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        //horizontalAlignment: Este parámetro define cómo se deben alinear horizontalmente todos los elementos hijos dentro de la Column.
        //Alignment.CenterHorizontally: Centra todos los elementos hijos horizontalmente dentro de la Column. Esto significa que cada elemento hijo estará
        //  centrado horizontalmente respecto a la anchura de la columna.

    ) {
        Text(
            text = "IPSports",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Log in ", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "mail") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            //justificar a la derecha
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Forgot your password?")
            Row(
                //alinear verticalmente
                verticalAlignment = Alignment.CenterVertically
            ) {
                //estado checkbox
                var isChecked by remember { mutableStateOf(false) }
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    enabled = true //habilitar o no el check box
                )
                Text(text = "Recordarme")
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {navigateToHome() },
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .padding(8.dp),
            //  Define la forma del botón, en este caso, con esquinas redondeadas.
            shape = RoundedCornerShape(12.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF64B5F6),
                contentColor = Color.White,
//                disabledContainerColor = Color.Gray,
//                disabledContentColor = Color.LightGray,

            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp
            )
        ) { Text(text = "Inciar sesion", fontSize = 16.sp, fontWeight = FontWeight.Bold) }

    }


    }
