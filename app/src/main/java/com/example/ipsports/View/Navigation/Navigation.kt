package com.example.ipsports.View.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ipsports.Model.RoutesNavigation.Routes
import com.example.ipsports.View.HomeScreen.HomeScreen
import com.example.ipsports.View.Login.LoginEntryScreen
import com.example.ipsports.View.Login.LoginScreen
import com.example.ipsports.View.Register.RegisterScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.LOGIN_ENTRY) {

        composable(Routes.LOGIN_ENTRY) {
            LoginEntryScreen(
                onNavigateToLogin = { navController.navigate(Routes.LOGIN) },
                onNavigateToRegister = { navController.navigate(Routes.REGISTER) }
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                authViewModel = hiltViewModel(),
                onNavigateToHome = { navController.navigate(Routes.HOME) },
                onRegisterClick = { navController.navigate(Routes.REGISTER) }
            )
        }
        composable(Routes.REGISTER) {
            RegisterScreen(
                authViewModel = hiltViewModel(),
                navController = navController, // ✅ Pasa correctamente el navController
                onBack = {
                    if (!navController.popBackStack(Routes.LOGIN_ENTRY, inclusive = false)) {
                        navController.navigate(Routes.LOGIN_ENTRY) {
                            popUpTo(Routes.LOGIN_ENTRY) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                currentRoute = Routes.HOME,
                onNavigate = { route -> navController.navigate(route) },
                username = "Usuario", // Sustituir con datos reales del usuario
                userImage = null,
                onEditProfile = { /* Acción de editar perfil */ },
                onStatsClick = { /* Acción de estadísticas */ },
                onLogout = {
                    navController.navigate(Routes.LOGIN_ENTRY) { popUpTo(Routes.HOME) { inclusive = true } }
                }
            )
        }
    }
}
