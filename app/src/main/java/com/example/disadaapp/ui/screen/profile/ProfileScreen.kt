package com.example.disadaapp.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.disadaapp.navigation.Route
import com.example.disadaapp.ui.screen.login.LoginScreen
import com.example.disadaapp.ui.screen.register.RegisterScreen
import kotlinx.coroutines.launch


@Composable
fun ProfileScreen() {
    val navController = rememberNavController() // Inisialisasi NavHostController
    val coroutineScope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = Route.Register.route) {

        composable(Route.Register.route) {
            RegisterScreen(
                onRegistrationSuccess = {
                    // Panggil fungsi ini saat pendaftaran berhasil
                    coroutineScope.launch {
                        navController.navigate(Route.Login.route) {
                            popUpTo(Route.Register.route) { inclusive = true }
                        }
                    }
                }
            )
        }
        composable(Route.Login.route) {
            // Tampilkan tampilan LoginScreen di sini
            LoginScreen()
        }
    }
    ProfileContent()
}

@Composable
fun ProfileContent() {

}
