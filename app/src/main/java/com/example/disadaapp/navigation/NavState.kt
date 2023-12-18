package com.example.disadaapp.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.example.disadaapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.disadaapp.ui.screen.homepage.HomeScreen
import com.example.disadaapp.ui.screen.login.LoginScreen
import com.example.disadaapp.ui.screen.profile.ProfileScreen
import com.example.disadaapp.ui.screen.register.RegisterScreen
import com.example.disadaapp.ui.screen.service.ServiceScreen
import com.example.disadaapp.ui.screen.timeCapsule.TimeCapsuleScreen

class NavState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val drawerState: DrawerState,
    private val scope: CoroutineScope,
    val snackbarHostState: SnackbarHostState,
    private val context: Context,
    private val navController: NavHostController
) {
    @OptIn(ExperimentalMaterial3Api::class)
    fun onMenuClick(){
        scope.launch {
            if (drawerState.isClosed){
                drawerState.open()
            }else{
                drawerState.close()
            }
        }
    }

    @Composable
    fun NavHostContent(){
        NavHost(
            navController = navController,
            startDestination = Route.Home.route ){
                composable(Route.Home.route){
                    HomeScreen()
                }
                composable(Route.Maps.route){
                    ServiceScreen()
                }
                composable(Route.TimeCapsule.route){
                    TimeCapsuleScreen()
                }
                composable(Route.Profile.route){
                    ProfileScreen()
                }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun onItemSelected(item: MenuItem){
        scope.launch {
                drawerState.close()

            // menavigasi berdasarkan Route
            when (item.screen) {
                is Route.Home -> {
                    navController.navigate(item.screen.route)
                }
                is Route.Maps ->{
                    navController.navigate(item.screen.route)
                }
                is Route.TimeCapsule -> {
                    navController.navigate(item.screen.route)
                }
                is Route.Profile ->{
                    navController.navigate(item.screen.route)
                }

                else -> {}
            }
//
//            val snackbarResult = snackbarHostState.showSnackbar(
//                message = context.resources.getString(R.string.coming_soon, item.title),
//                actionLabel = context.resources.getString(R.string.subscribe_question),
//                withDismissAction = true,
//                duration = SnackbarDuration.Short
//            )
//            if (snackbarResult == SnackbarResult.ActionPerformed){
//                Toast.makeText(
//                    context,
//                    context.resources.getString(R.string.subscribed_info),
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun onBackPress(){
        if (drawerState.isOpen){
            scope.launch {
                drawerState.close()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberMyNavDrawerState(
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    coroutinesScope: CoroutineScope = rememberCoroutineScope(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    context: Context = LocalContext.current,
    navController: NavHostController = rememberNavController()
): NavState =

    remember(drawerState,coroutinesScope,snackbarHostState,context, navController){
        NavState(drawerState, coroutinesScope, snackbarHostState, context, navController)

    }