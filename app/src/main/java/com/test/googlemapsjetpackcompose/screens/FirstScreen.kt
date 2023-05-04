@file:OptIn(ExperimentalMaterial3Api::class)

package com.test.googlemapsjetpackcompose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.test.googlemapsjetpackcompose.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FirstScreen(navController: NavController) {
    // Se utiliza Scaffold como el contenedor principal de la pantalla.
    Scaffold { BodyContent(navController) }
}


@Composable
fun BodyContent(navController: NavController) {
    // Column es un composable que organiza sus hijos en una columna vertical.
    Column(
        // El hijo ocupará todo el espacio disponible.
        modifier = Modifier.fillMaxSize(),
        // Los hijos se colocan en la parte inferior.
        verticalArrangement = Arrangement.Bottom,
        // Los hijos se centran horizontalmente.
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Button es un composable que muestra un botón que el usuario puede hacer clic.
        // onClick es la función que se ejecutará cuando el usuario haga clic en el botón.
        // navController.navigate se utiliza para navegar a la siguiente pantalla.
        Button(onClick = {
            navController.navigate(route = AppScreens.SecondScreen.route)
        }) {
            // El texto que se muestra en el botón.
            Text("Iniciar")
        }
        // Añade un espacio entre el botón y el borde inferior de la pantalla.
        Spacer(modifier = Modifier.height(60.dp))
    }
}