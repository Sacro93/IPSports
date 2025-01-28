package com.example.ipsports.View.Reusable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipsports.R
import com.example.ipsports.ui.theme.IpSportsTheme


@Composable
fun TopUserMenu(
    userInitials: String,       // Iniciales del usuario
    userImage: Painter? = null, // Imagen opcional del usuario
    onEditProfile: () -> Unit,  // Acción para editar perfil
    onStatsClick: () -> Unit,   // Acción para ver estadísticas
    onLogout: () -> Unit        // Acción para cerrar sesión
) {
    var expanded by remember { mutableStateOf(false) } // Estado para el menú desplegable

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        // Imagen de usuario (Reutilizando UserAvatar)
        UserAvatar(
            initials = userInitials,
            userImage = userImage,
            modifier = Modifier.clickable { expanded = true } // Abre el menú al tocar
        )

        // Menú desplegable
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentSize(Alignment.TopStart)
        ) {
            DropdownMenuItem(
                text = { Text("Editar perfil") },
                onClick = {
                    expanded = false
                    onEditProfile()
                }
            )
            DropdownMenuItem(
                text = { Text("Estadísticas") },
                onClick = {
                    expanded = false
                    onStatsClick()
                }
            )
            DropdownMenuItem(
                text = { Text("Cerrar sesión") },
                onClick = {
                    expanded = false
                    onLogout()
                }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun TopUserMenuPreview() {
    IpSportsTheme {
        TopUserMenu(
            userInitials = "FS", // Iniciales del usuario
            userImage = painterResource(id = R.drawable.perfil), // Imagen de ejemplo
            onEditProfile = { println("Editar perfil") },
            onStatsClick = { println("Ir a estadísticas") },
            onLogout = { println("Cerrar sesión") }
        )
    }
}
