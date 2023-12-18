package com.example.disadaapp.ui.screen.profile

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.disadaapp.navigation.Route
import com.example.disadaapp.ui.screen.login.LoginScreen
import com.example.disadaapp.ui.screen.register.RegisterScreen
import kotlinx.coroutines.launch
import androidx.navigation.compose.rememberNavController


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
}
