package com.example.ipsports.View.Event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.ipsports.View.Event.ReusableEvent.FriendItem
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.View.Event.ReusableEvent.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFriendsScreen(
    availableFriends: List<String> = listOf("Juan", "María", "Carlos", "Ana"),
    onSendRequest: (List<String>) -> Unit = {},
    onBack: () -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFriends by remember { mutableStateOf(setOf<String>()) }

    val filteredFriends = availableFriends.filter { it.contains(searchQuery, ignoreCase = true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF337C8D), // Verde azulado claro
                        Color(0xFF15272D), // Azul grisáceo oscuro
                        Color(0xFF17272B)  // Casi negro (base)
                    )
                )
            )
            .padding(20.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(16.dp))
            SearchBar()

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxHeight(0.7f)
            ) {
                items(filteredFriends) { friend ->
                    FriendItem(
                        name = friend,
                        isSelected = selectedFriends.contains(friend),
                        onSelect = {
                            selectedFriends = if (selectedFriends.contains(friend)) {
                                selectedFriends - friend
                            } else {
                                selectedFriends + friend
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(0.5f))

            ButtonPrimary(
                text = "Enviar Solicitud",
                onClick = { onSendRequest(selectedFriends.toList()) },
                enabled = selectedFriends.isNotEmpty(),
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            ButtonPrimary(
                text = "Volver",
                onClick = onBack,
                isOutlined = false,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddFriendsScreenPreview() {
    AddFriendsScreen(
        availableFriends = listOf("Juan", "María", "Carlos", "Ana"),
        onSendRequest = { println("Solicitudes enviadas a: $it") },
        onBack = { println("Volver a la pantalla anterior") }
    )
}
