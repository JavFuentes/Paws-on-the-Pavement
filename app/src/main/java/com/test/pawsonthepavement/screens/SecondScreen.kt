@file:OptIn(ExperimentalMaterial3Api::class)

package com.test.pawsonthepavement.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.test.pawsonthepavement.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SecondScreen(navController: NavController, text: String?) {
    Scaffold { SecondBodyContent(navController, text) }
}

@Composable
fun SecondBodyContent(navController: NavController, avatarId: String?) {
    Column(
        // El hijo ocupará todo el espacio disponible.
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        // Los hijos se colocan en la parte inferior.
        verticalArrangement = Arrangement.Bottom,
        // Los hijos se centran horizontalmente.
        horizontalAlignment = Alignment.Start
    ) {
        Row {
            //Avatar
            AvatarImage(avatarId)

            // Agrega el dropdown del nombre al lado derecho de la imagen y lo centra verticalmente
            Box(
                modifier = Modifier
                    .padding(top = 20.dp, end = 20.dp)
                    .align(Alignment.CenterVertically)
            ) {
                DropDownName()
            }
        }

        // Añade un espacio flexible para empujar el botón hacia abajo
        Spacer(modifier = Modifier.weight(1f))

        // Botón que abre el mapa
        MapButton(navController)

    }
}

@Composable
fun MapButton(navController: NavController){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                navController.navigate(route = AppScreens.MapScreen.route)
            },
            modifier = Modifier
                // Centrar el botón horizontalmente
                .width(200.dp)
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            // El texto que se muestra en el botón.
            Text("ABRIR MAPA", fontSize = 20.sp)
        }
        // Añade un espacio entre el botón y el borde inferior de la pantalla.
        Spacer(modifier = Modifier.height(60.dp))
    }
}


@Composable
fun AvatarImage(avatarId: String?){
    // Muestra la imagen utilizando el avatarId
    if (avatarId != null) {
        Box(modifier = Modifier.padding(start = 10.dp, top = 30.dp)) {
            Image(
                painter = painterResource(id = avatarId.toInt()),
                contentDescription = "avatar",
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .align(Alignment.TopStart)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownName() {
    // Se crea un estado para controlar si el menú está expandido o no
    var isExpanded by remember { mutableStateOf(false) }

    // Lista de nombres que aparecerán en el menú desplegable
    val list = listOf("Perro callejero", "Perro vago", "Perro abandonado")

    // Se crea un estado para almacenar el nombre seleccionado
    var name by remember { mutableStateOf(list[0]) }

    // Se crea el ExposedDropdownMenuBox que contiene el TextField y el menú desplegable
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }
    ) {
        // Se crea el TextField que muestra el nombre seleccionado
        TextField(
            value = name,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )

        // Se crea el ExposedDropdownMenu que contiene los elementos del menú
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },

            ) {
            // Itera sobre la lista de nombres y crea un DropdownMenuItem para cada uno
            list.forEach { nombre ->
                DropdownMenuItem(
                    // Muestra el nombre como texto dentro del elemento del menú
                    { Text(text = nombre) },
                    // Cuando se hace clic en un elemento del menú, se actualiza el estado de "name" y se cierra el menú
                    onClick = {
                        name = nombre
                        isExpanded = false
                    }
                )
            }
        }
    }
}


