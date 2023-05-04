@file:OptIn(ExperimentalMaterial3Api::class)

package com.test.googlemapsjetpackcompose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.test.googlemapsjetpackcompose.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SecondScreen(navController: NavController){
    Scaffold { SecondBodyContent(navController)}
}

@Composable
fun SecondBodyContent(navController: NavController){
    Column() {
        Text("segunda")
        Button(onClick = {
            navController.navigate(route = AppScreens.MapScreen.route)
        }) {
            Text("Navega")
        }
    }
}

