package com.example.shopping_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shopping_app.screens.CheckoutScreen
import com.example.shopping_app.screens.DetailScreen
import com.example.shopping_app.screens.HomeScreen

@Composable
fun Navigation() {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = "home_screen") {
        composable("home_screen") {
            HomeScreen(navHostController = navHostController)
        }
        composable("checkout") {
            CheckoutScreen(navHostController = navHostController)
        }
    }
}

