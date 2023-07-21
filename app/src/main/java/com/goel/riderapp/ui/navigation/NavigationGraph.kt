package com.goel.riderapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.goel.riderapp.ui.driver.DriversHomeScreen
import com.goel.riderapp.ui.route.RouteInfoScreen

private const val DRIVER_HOME_SCREEN = "driver_home_screen"
private const val ROUTE_INFO_SCREEN = "route_info_screen"

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = DRIVER_HOME_SCREEN) {
        composable(DRIVER_HOME_SCREEN) {
            DriversHomeScreen { id ->
                navController.navigate("$ROUTE_INFO_SCREEN/$id")
            }
        }
        composable("$ROUTE_INFO_SCREEN/{id}") {
            val id = it.arguments?.getString("id") ?: error("Must pass in an id")
            RouteInfoScreen(id = id) {
                navController.popBackStack()
            }
        }
    }
}
