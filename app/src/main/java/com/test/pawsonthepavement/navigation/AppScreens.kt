package com.test.pawsonthepavement.navigation

// Clase sellada (sealed class) que define todas las pantallas de la app.
// Cada objeto (object) representa una pantalla y tiene una propiedad "route" que indica su ruta.
sealed class AppScreens(val route: String){
    object FirstScreen: AppScreens("first_screen")
    object SecondScreen: AppScreens("second_screen")
    object MapScreen: AppScreens("map_screen")
    object SplashScreen: AppScreens("splash_screen")
}
