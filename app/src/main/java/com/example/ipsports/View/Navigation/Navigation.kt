package com.example.ipsports.View.Navigation

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ipsports.View.CentersScreen
import com.example.ipsports.View.Event.pg1.SportSelectionScreen
import com.example.ipsports.View.Event.pg2.EventInfoScreen
import com.example.ipsports.View.HomeScreen.HomeScreen
import com.example.ipsports.View.Login.LoginEntryScreen
import com.example.ipsports.View.Login.LoginScreen
import com.example.ipsports.View.Profile.EditProfileScreen
import com.example.ipsports.View.Profile.ProfileScreen
import com.example.ipsports.View.Register.RegisterScreen
import com.example.ipsports.ViewModel.Autenticacion.AuthViewModel
import com.example.ipsports.ViewModel.ui.UserViewModel
import com.example.ipsports.data.Routes

@Composable
fun Navigation(
    navController: NavHostController) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val isLoggedIn by authViewModel.isUserLoggedIn.collectAsStateWithLifecycle()

    LaunchedEffect(isLoggedIn, navController) {
        println("🔥 Navegación - Estado de isLoggedIn: $isLoggedIn")

        if (!isLoggedIn && navController.currentDestination?.route != Routes.LOGIN_ENTRY) {
            navController.navigate(Routes.LOGIN_ENTRY) {
                popUpTo(0) { inclusive = true } // ✅ Asegura que no haya pantallas en el stack
            }
        }
    }
        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn) Routes.HOME else Routes.LOGIN_ENTRY
        ) {
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

            composable("event_info/{selectedSport}") { backStackEntry ->
                val selectedSport =
                    backStackEntry.arguments?.getString("selectedSport") ?: return@composable

                EventInfoScreen(
                    selectedSport = selectedSport, // 🔹 PASAR EL DEPORTE A LA SIGUIENTE PANTALLA
                    onContinue = { /* Lógica para continuar */ },
                    selectedCourt = null,
                    courts = listOf("Cancha 1", "Cancha 2", "Cancha 3"),
                    onCourtSelected = { /* Manejar selección de cancha */ },
                    onBack = { navController.popBackStack() }
                )
            }


            composable(Routes.HOME) {
                HomeScreen(
                    currentRoute = Routes.HOME,
                    onNavigate = { route -> navController.navigate(route) },
                    navController = navController
                )

            }

            // **Pantalla de Selección de Deportes**
            composable(Routes.SPORT_SELECTION) {
                SportSelectionScreen(
                    navController = navController,
                    onBack = { navController.popBackStack() }
                )
            }

            //  **Pantalla de Información del Evento**
            composable(Routes.EVENT_INFO) { backStackEntry ->
                val selectedSport =
                    backStackEntry.arguments?.getString("selectedSport") ?: return@composable
                EventInfoScreen(
                    selectedSport = selectedSport,
                    onContinue = { /* Lógica para continuar */ },
                    selectedCourt = null,
                    courts = listOf("Cancha 1", "Cancha 2", "Cancha 3"),
                    onCourtSelected = { /* Manejar selección de cancha */ },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(Routes.CENTERS) {
                CentersScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            //  Pantalla de Perfil
            composable(Routes.PROFILE) {
                ProfileScreen(
                    onNavigate = { route -> navController.navigate(route) },
                    onEditProfileClick = { navController.navigate(Routes.EDIT_PROFILE) }, // ✅ Ir a editar perfil
                    onHelpClick = { /* Acción de ayuda */ },
                    onTermsClick = { /* Acción de términos y condiciones */ },
                    onNotificationsClick = { /* Acción de notificaciones */ },
                    //          accion de cerrar sesion
                )
            }

            // Pantalla de Edición de Perfil
            composable(Routes.EDIT_PROFILE) {
                val userViewModel: UserViewModel = hiltViewModel()
                val user by userViewModel.user.collectAsState()

                if (user != null) {
                    EditProfileScreen(
                        userViewModel = userViewModel,
                        profileImage = user?.profileImageUrl, // ✅ Pasa la imagen del usuario
                        userData = user!!, // ✅ Pasa el usuario completo
                        onPhotoSelected = { /* Lógica para seleccionar foto */ },
                        onTakePhoto = { /* Lógica para tomar foto */ },
                        onBack = { navController.popBackStack() }
                    )
                } else {
                    CircularProgressIndicator() // ✅ Muestra carga si el usuario aún no está listo
                }
            }


        }
    }

