package com.example.tuku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Inisialisasi navController
            val navController = rememberNavController()

            // Inisialisasi ViewModel
            val cartViewModel = CartViewModel()

            // Navigasi utama
            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") { SplashScreen(navController) }
                composable("list") { ListScreen(navController) }
                composable(
                    route = "detail/{itemId}",
                    arguments = listOf(navArgument("itemId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val itemId = backStackEntry.arguments?.getInt("itemId")
                    DetailScreen(navController = navController, itemId = itemId, cartViewModel = cartViewModel)
                }
                composable("cart") { CartScreen(cartViewModel = cartViewModel) } // Layar keranjang
            }
        }
    }
}
