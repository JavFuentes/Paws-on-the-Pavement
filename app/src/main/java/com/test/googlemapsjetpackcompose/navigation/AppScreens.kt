package com.test.googlemapsjetpackcompose.navigation

sealed class AppScreens(val route: String){
    object FirstScreen: AppScreens("first_screen")
    object SecondScreen: AppScreens("second_screen")
    object MapScreen: AppScreens("map_screen")
}
