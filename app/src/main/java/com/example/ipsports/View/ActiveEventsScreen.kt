package com.example.ipsports.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.ipsports.View.HomeScreen.EventCard
import com.example.ipsports.ViewModel.ui.EventViewModel
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveEventsScreen(navController: NavController) {
    val eventViewModel: EventViewModel = hiltViewModel()
    val events by eventViewModel.eventsList.collectAsStateWithLifecycle()
    val isLoading by eventViewModel.isLoading.collectAsStateWithLifecycle()
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    LaunchedEffect(userId) {
        userId?.let { eventViewModel.loadEventsByUser(it) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Eventos Activos", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF15272D))
                .padding(padding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    if (events.isEmpty()) {
                        Text("No tienes eventos activos", color = Color.White, style = MaterialTheme.typography.bodyLarge)
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(events) { event ->
                                EventCard(
                                    sport = event.sportId,
                                    date = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(event.date.toDate()),
                                    location = event.centerId,
                                    participants = event.usersInvited,
                                    maxParticipants = event.maxParticipants
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
