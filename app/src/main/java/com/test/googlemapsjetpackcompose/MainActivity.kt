package com.test.googlemapsjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.test.googlemapsjetpackcompose.ui.theme.GoogleMapsJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Tema de Material Design
            GoogleMapsJetpackComposeTheme {
                // Contenedor de superficie usando el color de fondo del tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Llamado a la función composable que contiene el mapa de Google
                    myGoogleMaps()
                }
            }
        }
    }
}

@Composable
fun myGoogleMaps() {
    // Definición de una ubicación para el marcador del mapa
    val marker = LatLng(-33.02697881819879, -71.54051976575631)

    // Definición de las propiedades del mapa usando el estado mutable
    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.HYBRID)) }

    // Definición de las opciones de UI del mapa usando el estado mutable
    val uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = true)) }

    // Composable que contiene el mapa de Google
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = properties,
        uiSettings = uiSettings
    ) {
        // Agregar un marcador al mapa
        Marker(
            position = marker,
            title = "UNAB",
            snippet = "Aquí estás realizando el bootcamp de Android"
        )
    }
}
