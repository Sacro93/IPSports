package com.example.ipsports.View.Event.pg4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.ipsports.View.Event.ReusableEvent.FriendItem
import com.example.ipsports.ViewModel.ui.FriendViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFriendsScreen(
    navController: NavController
) {
    val viewModel: FriendViewModel = hiltViewModel()
    val friendsList by viewModel.friendsList.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    var searchQuery by remember { mutableStateOf("") }
    var selectedFriends by remember { mutableStateOf(setOf<String>()) }

    val filteredFriends = friendsList.filter { it.name.contains(searchQuery, ignoreCase = true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Invitar Amigos", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF15272D))
                .padding(padding)
        ) {
            // 🔎 **Barra de búsqueda**
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar amigos") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                textStyle = TextStyle(color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = 0.1f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.1f),
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(filteredFriends) { friend ->
                        FriendItem(
                            friend = friend,
                            isInvited = selectedFriends.contains(friend.id),
                            onInvite = {
                                selectedFriends = if (selectedFriends.contains(friend.id)) {
                                    selectedFriends - friend.id
                                } else {
                                    selectedFriends + friend.id
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    // 🔹 Guardar la lista de amigos invitados en `savedStateHandle` cuando se cierre la pantalla
    LaunchedEffect(selectedFriends) {
        navController.previousBackStackEntry
            ?.savedStateHandle
            ?.set("invitedFriends", selectedFriends.toList())
    }
}

