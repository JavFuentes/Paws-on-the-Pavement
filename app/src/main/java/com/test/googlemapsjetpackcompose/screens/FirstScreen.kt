@file:OptIn(ExperimentalMaterial3Api::class)

package com.test.googlemapsjetpackcompose.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.test.googlemapsjetpackcompose.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FirstScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold() {
            BodyContent(navController)
        }
    }
}

@Composable
fun BodyContent(navController: NavController) {
    val context = LocalContext.current
    val dogImageIds = getDogImageIds(context = context)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Crea un Box que limita el tamaño para mostrar solo 3 elementos a la vez.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f) // Ajusta este valor según el tamaño de tus elementos.
        ) {
            LazyColumn {
                items(dogImageIds) { imageId ->
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // Utiliza Column para colocar el botón en la parte inferior y centrado horizontalmente.
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                navController.navigate(route = AppScreens.SecondScreen.route)
            }) {
                Text("Iniciar")
            }
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun getDogImageIds(context: Context): List<Int> {
    val dogImageIds = mutableListOf<Int>()
    for (i in 1..14) {
        val imageName = "perro$i"
        val resourceId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        dogImageIds.add(resourceId)
    }
    return dogImageIds
}
