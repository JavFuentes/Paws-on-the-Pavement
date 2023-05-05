package com.test.googlemapsjetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.googlemapsjetpackcompose.screens.FirstScreen
import com.test.googlemapsjetpackcompose.screens.SecondScreen
import com.test.googlemapsjetpackcompose.screens.MapScreen

@Composable
fun AppNavigation() {
    // Creamos el NavController con el cual se realizará la navegación entre las pantallas.
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route){
        // Definimos la pantalla inicial de nuestra app.
        composable(route = AppScreens.FirstScreen.route){
            FirstScreen(navController)
        }
        // Definimos la segunda pantalla de nuestra app.
        composable(route = AppScreens.SecondScreen.route){
            SecondScreen(navController)
        }
        // Definimos una pantalla de mapa de nuestra app.
        composable(route = AppScreens.MapScreen.route){
            MapScreen(navController)
        }
    }
}
