package com.example.ipsports

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.ipsports.View.Navigation.Navigation
import com.example.ipsports.ViewModel.ui.DatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // 🔥 Forzar transparencia en la barra de estado
        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS)
            window.statusBarColor = android.graphics.Color.TRANSPARENT
        } else {
            @Suppress("DEPRECATION") // ✅ Ignorar advertencia solo en versiones antiguas
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
            window.statusBarColor = android.graphics.Color.TRANSPARENT
        }

        setContent {
            val navController = rememberNavController()
            val databaseViewModel: DatabaseViewModel = hiltViewModel()

            LaunchedEffect(Unit) {
                databaseViewModel.isDataLoaded.collect { loaded ->
                    if (loaded) {
                        println("✅ Firestore está listo.")
                    } else {
                        println("⚠️ Firestore no se ha llenado todavía.")
                    }
                }
            }

            Navigation(navController = navController)
        }
    }
}

