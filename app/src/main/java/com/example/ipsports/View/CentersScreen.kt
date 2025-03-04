package com.example.ipsports.View

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ipsports.ViewModel.ui.CenterViewModel
import com.example.ipsports.data.model.CenterWithSports

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CentersScreen(
    onNavigateBack: () -> Unit
) {
    val centerViewModel: CenterViewModel = hiltViewModel()
    val centersWithSports by centerViewModel.centersWithSports.collectAsState()
    val isLoading by centerViewModel.isLoading.collectAsState()



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Clubes Deportivos", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1565C0))
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(centersWithSports) { centerWithSports ->
                        CenterItem(centerWithSports)
                    }
                }
            }
        }
    }
}

@Composable
fun CenterItem(centerWithSports: CenterWithSports) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Acción al seleccionar un club */ },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E88E5))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(centerWithSports.center.name, color = Color.White, style = MaterialTheme.typography.titleMedium)
            Text(centerWithSports.center.location, color = Color.White.copy(alpha = 0.8f))

            Spacer(modifier = Modifier.height(8.dp))

            Text("Deportes:", color = Color.White, fontWeight = FontWeight.Bold)
            centerWithSports.sports.forEach { sport ->
                Text("- ${sport.name}", color = Color.White.copy(alpha = 0.8f))
            }
        }
    }
}

