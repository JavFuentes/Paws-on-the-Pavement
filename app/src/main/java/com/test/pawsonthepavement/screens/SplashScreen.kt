package com.test.pawsonthepavement.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.test.googlemapsjetpackcompose.R
import com.test.pawsonthepavement.navigation.AppScreens
import kotlinx.coroutines.delay

// SplashScreen Composable: muestra la pantalla de bienvenida y redirige al usuario después de un retardo
@Composable
fun SplashScreen(navController: NavController) {

    // LaunchedEffect: ejecuta una acción cuando cambia el valor de la clave (key1)
    LaunchedEffect(key1 = true){
        // Retardo de 3 segundos (3000 ms)
        delay(3000)
        // Elimina el último destino del back stack
        navController.popBackStack()
        // Navega a la primera pantalla
        navController.navigate(AppScreens.FirstScreen.route)
    }
    // Muestra el contenido de la pantalla de bienvenida
    Splash()
}

// Splash Composable: muestra el contenido de la pantalla de bienvenida
@Composable
fun Splash() {
    Column(
        // Rellena el tamaño máximo disponible
        modifier = Modifier.fillMaxSize(),
        // Alinea el contenido horizontalmente al centro
        horizontalAlignment = Alignment.CenterHorizontally,
        // Alinea el contenido verticalmente al centro
        verticalArrangement = Arrangement.Center
    ) {
        // Muestra la imagen del logotipo
        Image(
            // Carga el recurso de la imagen
            painter = painterResource(id = R.drawable.paws_on_the_pavement_xiaomi_rn8),
            // Descripción de la imagen para accesibilidad
            contentDescription = "logo",
            // Escala el contenido para que llene los límites
            contentScale = ContentScale.FillBounds,
            // Rellena el tamaño máximo disponible
            modifier = Modifier.fillMaxSize()
        )
    }
}

// Vista previa del Composable SplashScreen
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    Splash()
}
