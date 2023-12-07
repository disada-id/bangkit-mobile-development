package com.example.disadaapp.navigation

sealed class Route(val route: String) {

    object Home: Route("home")
    object Maps: Route("maps")
    object TimeCapsule: Route("time capsule")
    object Profile: Route("Profile")

}