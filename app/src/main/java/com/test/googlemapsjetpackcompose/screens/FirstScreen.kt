@file:OptIn(ExperimentalMaterial3Api::class)

package com.test.googlemapsjetpackcompose.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.wear.tiles.material.Text
import com.test.googlemapsjetpackcompose.navigation.AppScreens
import com.test.googlemapsjetpackcompose.ui.theme.LightBlue
import com.test.googlemapsjetpackcompose.ui.theme.Violet

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
    val dogImageIds = getDogAvatarIds(context = context)

    // Crear un estado mutable para almacenar el ID de la imagen seleccionada.
    var selectedImageId by remember { mutableStateOf(-1) }

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
                        modifier = Modifier
                            .fillMaxWidth()
                            // Añadir un clickable para cambiar el estado de selectedImageId al tocar la imagen.
                            .clickable { if (selectedImageId != imageId) selectedImageId = imageId }
                            // Aplicar un valor alfa para atenuar las imágenes no seleccionadas.
                            .alpha(if (selectedImageId == -1 || selectedImageId == imageId) 1f else 0.4f)
                    )
                }
            }
        }

        //Botón en la parte inferior y centrado horizontalmente.
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (selectedImageId == -1) {
                Text(
                    text = "Seleccione un avatar para comenzar",
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = Color.White,
                    fontSize= 20.sp
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    navController.navigate(
                        route = AppScreens.SecondScreen.route + "/$selectedImageId")
                },

                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                enabled = selectedImageId != -1,

            ) {
                Text("COMENZAR", fontSize= 20.sp)
            }
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun getDogAvatarIds(context: Context): List<Int> {
    // Crea una lista mutable que almacenará los IDs de los recursos de imágenes de perros.
    val dogImageIds = mutableListOf<Int>()
    // Usa un bucle para iterar a través de los números de imagen de 1 a 14.
    for (i in 1..14) {
        // Crea el nombre de archivo de imagen a partir del número de imagen.
        val imageName = "perro$i"
        // Obtiene el ID del recurso de imagen correspondiente al nombre de archivo de imagen utilizando el contexto.
        val resourceId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        // Agrega el ID del recurso de imagen a la lista mutable.
        dogImageIds.add(resourceId)
    }
    // Devuelve la lista de IDs de recursos de imágenes de perros.
    return dogImageIds
}


