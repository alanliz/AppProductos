package com.unison.appproductos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.unison.appproductos.navigation.NavDestinations.ProductFormView
import com.unison.appproductos.views.*
import com.unison.appproductos.viewModels.ProductViewModel

@Composable
fun NavManager(
    navController: NavHostController = rememberNavController(), // Inicializa el NavController si no es proporcionado,
    productViewModel: ProductViewModel = viewModel(),
            modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavDestinations.HomeView, // Definir el destino inicial
        modifier = modifier
    ) {
        // HomeView necesita el navController para la navegación
        composable(NavDestinations.HomeView) {
            HomeView(navController = navController)
        }

        // PresentationView recibe navController en caso de que lo necesite
        composable(NavDestinations.PresentationCardView) {
            PresentationCardView(navController = navController)
        }

        // ListView necesita el productViewModel y posiblemente el navController para la navegación
        composable(NavDestinations.ProductListView) {
            ProductListView(viewModel = productViewModel, navController = navController)
        }

        // Ruta para FormView (creación de producto)
        composable(NavDestinations.ProductFormView) {
            ProductFormView(navController = navController, viewModel = productViewModel)
        }

        // Ruta para editar un producto con parámetro de productId opcional
        composable(
            route = "${NavDestinations.ProductFormView}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") // Obtener el productId como String
            val product = productViewModel.getProductById(productId) // Obtener el producto por ID (puede ser null)

            // Si el productId es válido, se pasa el producto a la FormView; si no, es un producto nuevo (null)
            ProductFormView(navController = navController, product = product, viewModel = productViewModel)
        }
    }
}




