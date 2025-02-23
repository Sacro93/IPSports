package com.example.ipsports.View.Event.pg1
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ipsports.View.Event.ReusableEvent.EventCreationProgressBar
import com.example.ipsports.View.Reusable.ButtonPrimary
import androidx.navigation.NavController
import com.example.ipsports.ViewModel.ui.SportViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportSelectionScreen(
    navController: NavController, // Navegación
    onBack: () -> Unit // Acción para volver atrás
) {
    val viewModel: SportViewModel = hiltViewModel()

    val sportsList by viewModel.sports.collectAsStateWithLifecycle(initialValue = emptyList()) // ✅ Mejor manejo de estados
    val selectedSport by viewModel.selectedSport.collectAsStateWithLifecycle(initialValue = null)
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle(initialValue = true)

    // 🔹 Cargar deportes cuando se entra a la pantalla
    LaunchedEffect(Unit) {
        viewModel.loadSports()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Selecciona un Deporte", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
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
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF337C8D), // Azul verdoso claro (parte superior)
                            Color(0xFF15272D), // Azul grisáceo oscuro (zona media)
                            Color(0xFF17272B)  // Casi negro (base)
                        )
                    )
                )
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                EventCreationProgressBar(currentPage = 2, totalPages = 4)

                Spacer(modifier = Modifier.height(50.dp))

                // 🔹 Manejo de carga
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    LazyColumn {
                        items(sportsList) { sport ->
                            val onClick = rememberUpdatedState { viewModel.selectSport(sport) }

                            SportSelectionCard(
                                sport = sport,
                                isSelected = selectedSport?.name == sport.name,
                                onClick = { onClick.value() } // Usa el estado actualizado
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                ButtonPrimary(
                    text = "Continuar",
                    onClick = {
                        selectedSport?.let {
                            navController.navigate("event_info/${it.name}") // 🔹 Pasamos el deporte seleccionado
                        }
                    },
                    enabled = selectedSport != null, // 🔹 Habilita solo si hay selección
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(280.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}



/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportSelectionScreen(
    navController: NavController, // Navegación
    onBack: () -> Unit,                // Acción para volver atrás
) {

    val viewModel: SportSelectionViewModel = hiltViewModel() // ✅ Obtiene el ViewModel con hiltViewModel()
    val sportsList by viewModel.sportsList.observeAsState(initial = emptyList())
    val selectedSport by viewModel.selectedSport.observeAsState(initial = null)
    val isLoading by viewModel.isLoading.observeAsState(initial = true)



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Selecciona un Deporte", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
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
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF337C8D), // Azul verdoso claro (parte superior)
                            Color(0xFF15272D), // Azul grisáceo oscuro (zona media)
                            Color(0xFF17272B)  // Casi negro (base)
                        )
                    )
                )
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))


                EventCreationProgressBar(currentPage = 2, totalPages = 4)

                Spacer(modifier = Modifier.height(50.dp))


                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally))
                } else {
                    LazyColumn {
                        items(sportsList) { sport ->
                            val onClick = rememberUpdatedState { viewModel.selectSport(sport) }

                            SportSelectionCard(
                                sport = sport,
                                isSelected = selectedSport?.nombre == sport.nombre,
                                onClick = { onClick.value() } // Usa el estado actualizado
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                ButtonPrimary(
                    text = "Continuar",
                    onClick = {
                        selectedSport?.let {
                            navController.navigate("event_info/${it.nombre}") // 🔹 Pasamos el deporte seleccionado
                        }
                    },
                    enabled = selectedSport != null, // 🔹 Habilita solo si hay selección
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(280.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

*/