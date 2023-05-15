package com.test.pawsonthepavement.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.test.pawsonthepavement.screens.FirstScreen
import com.test.pawsonthepavement.screens.SecondScreen
import com.test.pawsonthepavement.screens.MapScreen
import com.test.pawsonthepavement.screens.SplashScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {

        // Definimos la pantalla inicial de nuestra app.
        composable(route = AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }

        // Definimos la primera pantalla de nuestra app.
        composable(route = AppScreens.FirstScreen.route) {
            FirstScreen(navController)
        }
        // Definimos la segunda pantalla de nuestra app.
        composable(route = AppScreens.SecondScreen.route + "/{avatar}",
            arguments = listOf(
                navArgument("avatar") {
                    type = NavType.StringType
            })
        ) {
            SecondScreen(navController, it.arguments?.getString("avatar"))
        }
        // Definimos una pantalla de mapa de nuestra app.
        composable(route = AppScreens.MapScreen.route) {
            MapScreen(navController)
        }
    }
}
