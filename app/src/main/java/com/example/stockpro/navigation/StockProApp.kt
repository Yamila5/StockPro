package com.example.stockpro.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stockpro.screens.PantallaCatalogo
import com.example.stockpro.screens.PantallaEdicionStock
import com.example.stockpro.screens.PantallaIngreso
import com.example.stockpro.screens.PantallaReporte
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun StockProApp() {
    val navController = rememberNavController()
    val stockViewModel: StockViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            PantallaIngreso { nombre ->
                navController.navigate("catalogo/$nombre")
            }
        }

        composable(
            route = "catalogo/{nombreOperario}",
            arguments = listOf(navArgument("nombreOperario") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombreOperario = backStackEntry.arguments?.getString("nombreOperario").orEmpty()
            PantallaCatalogo(
                nombreOperario = nombreOperario,
                viewModel = stockViewModel,
                onProductoClick = { productoId ->
                    navController.navigate("edicion/$productoId")
                },
                onReporteClick = {
                    navController.navigate("reporte")
                }
            )
        }

        composable(
            route = "edicion/{productoId}",
            arguments = listOf(navArgument("productoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productoId = backStackEntry.arguments?.getInt("productoId") ?: 0
            PantallaEdicionStock(
                productoId = productoId,
                viewModel = stockViewModel,
                onGuardarVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable("reporte") {
            PantallaReporte(
                viewModel = stockViewModel,
                onVolverCatalogo = {
                    navController.popBackStack()
                }
            )
        }
    }
}