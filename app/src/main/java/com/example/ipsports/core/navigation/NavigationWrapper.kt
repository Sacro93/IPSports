package com.example.ipsports.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// va a ser el que se encargue de toda la navigacion
@Composable

fun NavigationWrapper() {

    val navController = rememberNavController()
    //startDestination queremos indicarle que empcemos por una ruta
    NavHost(navController = navController, startDestination = Login) {
        //para poder definir y navegar a ellas
        composable<Login> {

           LoginScreen{navController.navigate(Home)}
        }
        composable<Home> {
            HomeScreen()
        }

    }
}


