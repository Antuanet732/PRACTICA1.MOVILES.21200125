package PRACTICA1.MOVILES.x21200125.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import PRACTICA1.MOVILES.x21200125.screens.ActivityTrackerScreen
import PRACTICA1.MOVILES.x21200125.screens.CarCatalogScreen
import PRACTICA1.MOVILES.x21200125.screens.MainMenuScreen
import PRACTICA1.MOVILES.x21200125.screens.WaterCalculatorScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main_menu") {
        composable("main_menu") {
            MainMenuScreen(navController = navController)
        }
        composable("water_calculator") {
            WaterCalculatorScreen(navController = navController)
        }
        composable("activity_tracker") {
            ActivityTrackerScreen(navController = navController)
        }
        composable("car_catalog") {
            CarCatalogScreen(navController = navController)
        }
    }
}