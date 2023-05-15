package com.test.pawsonthepavement.screens

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.test.googlemapsjetpackcompose.R
import com.test.pawsonthepavement.ui.theme.GoogleMapsJetpackComposeTheme

@Composable
fun MapsDefaultPreview(navController: NavController){
    // Tema de Material Design
    GoogleMapsJetpackComposeTheme {
        // Contenedor de superficie usando el color de fondo del tema
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Llamado a la función composable que contiene el mapa de Google
            MapScreen(navController)
        }
    }
}

@Composable
fun MapScreen(navController: NavController) {

    // Obtener el objeto Resources a partir del contexto actual
    val resources = LocalContext.current.resources

    // Crear un estado mutable para almacenar la ubicación actual del usuario (LatLng)
    val currentLatLng = remember { mutableStateOf<LatLng?>(null) }

    // Función para obtener la última ubicación conocida del usuario
    fun getLastKnownLocation(fusedLocationProviderClient: FusedLocationProviderClient) {
        // Solicitar la última ubicación conocida del usuario y manejar el resultado con un listener
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            // Si la ubicación no es nula, actualizar el estado mutable currentLatLng con la nueva ubicación
            if (location != null) {
                currentLatLng.value = LatLng(location.latitude, location.longitude)
            }
        }
    }

    // Definición de una ubicación para el marcador del mapa
    val unab = LatLng(-33.0131018380383, -71.5414731476765)

    // Definición de las propiedades del mapa usando el estado mutable
    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.HYBRID)) }

    // Definición de las opciones de UI del mapa usando el estado mutable
    val uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = true)) }

    // Composable que contiene el mapa de Google y los botones
    Box(modifier = Modifier.fillMaxSize()) {
        // Mapa de Google
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = properties,
            uiSettings = uiSettings,
            //initialCameraPosition = currentLatLng.value?.let { CameraPosition(target = it, zoom = 15f) } // Reemplaza 15f con el nivel de zoom que desees
        ) {
            //Se asigna un icono y una escala a cada marcador
            val markerIcon1 =
                remember { scaledBitmapDescriptor(resources, R.drawable.marker_icon1, 0.2f) }
            val markerIcon2 =
                remember { scaledBitmapDescriptor(resources, R.drawable.marker_icon2, 0.2f) }

            // Agregar un marcador al mapa
            Marker(
                position = unab,
                title = "UNAB",
                snippet = "Aquí estás realizando el bootcamp de Android",
                icon = markerIcon1
            )
            // Agregar un marcador en la ubicación actual
            currentLatLng.value?.let { latLng ->
                Marker(
                    position = latLng,
                    title = "Mi ubicación actual",
                    icon = markerIcon2
                )
            }
        }

        // Botones en la parte superior
        Row(
            modifier = Modifier.align(Alignment.TopEnd),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .width(110.dp)
                    .height(50.dp)
                    .padding(end = 10.dp, top = 10.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("ATRÁS")
            }
        }
    }

    // Obteniendo el contexto actual
    val context = LocalContext.current

    // Creando un objeto fusedLocationProviderClient usando el contexto
    val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    // Creando un lanzador para manejar la solicitud de permiso y obtener resultados
    val locationPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Si el permiso es otorgado, obtener la última ubicación conocida
                getLastKnownLocation(fusedLocationProviderClient)
            } else {
                // Si el permiso no es otorgado, manejar el caso (por ejemplo, mostrar un mensaje al usuario)
            }
        }

    // Función para solicitar el permiso de ubicación
    fun requestLocationPermission() {
        when (PackageManager.PERMISSION_GRANTED) {
            // Verificar si el permiso ya fue otorgado
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                // Si el permiso fue otorgado, obtener la última ubicación conocida
                getLastKnownLocation(fusedLocationProviderClient)
            }

            else -> {
                // Si el permiso no fue otorgado, solicitarlo usando el lanzador creado previamente
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    // Solicitando el permiso y obteniendo la ubicación al iniciar el composable
    LaunchedEffect(Unit) {
        requestLocationPermission()
    }
}



// Función para crear un BitmapDescriptor escalado a partir de un recurso de imagen
fun scaledBitmapDescriptor(resources: Resources, resourceId: Int, scale: Float): BitmapDescriptor {
    // Decodificar el recurso de imagen en un objeto Bitmap
    val originalBitmap = BitmapFactory.decodeResource(resources, resourceId)

    // Calcular las nuevas dimensiones para el Bitmap escalado
    val newWidth = (originalBitmap.width * scale).toInt()
    val newHeight = (originalBitmap.height * scale).toInt()

    // Crear un nuevo Bitmap escalado usando las nuevas dimensiones
    val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true)

    // Convertir el Bitmap escalado en un objeto BitmapDescriptor y devolverlo
    return BitmapDescriptorFactory.fromBitmap(scaledBitmap)
}

