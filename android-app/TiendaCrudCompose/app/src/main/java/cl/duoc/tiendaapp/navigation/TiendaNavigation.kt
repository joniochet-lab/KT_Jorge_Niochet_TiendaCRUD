package cl.duoc.tiendaapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.duoc.tiendaapp.ui.screens.CatalogoScreen
import cl.duoc.tiendaapp.ui.screens.DetalleProductoScreen
import cl.duoc.tiendaapp.ui.screens.FormularioProductoScreen
import cl.duoc.tiendaapp.ui.screens.InicioScreen
import cl.duoc.tiendaapp.viewmodel.TiendaViewModel

object Rutas {
    const val INICIO = "inicio"
    const val CATALOGO = "catalogo"
    const val NUEVO = "producto/nuevo"
    const val EDITAR = "producto/editar/{productoId}"
    const val DETALLE = "producto/detalle/{productoId}"

    fun editar(productoId: Long): String = "producto/editar/$productoId"
    fun detalle(productoId: Long): String = "producto/detalle/$productoId"
}

@Composable
fun TiendaNavigation(viewModel: TiendaViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutas.INICIO
    ) {
        composable(Rutas.INICIO) {
            InicioScreen(
                onEntrarCatalogo = { navController.navigate(Rutas.CATALOGO) },
                onAgregarProducto = { navController.navigate(Rutas.NUEVO) }
            )
        }

        composable(Rutas.CATALOGO) {
            CatalogoScreen(
                viewModel = viewModel,
                onAgregarProducto = { navController.navigate(Rutas.NUEVO) },
                onEditarProducto = { id -> navController.navigate(Rutas.editar(id)) },
                onVerDetalle = { id -> navController.navigate(Rutas.detalle(id)) }
            )
        }

        composable(Rutas.NUEVO) {
            FormularioProductoScreen(
                viewModel = viewModel,
                productoId = null,
                onVolver = { navController.popBackStack() },
                onGuardado = { navController.navigate(Rutas.CATALOGO) }
            )
        }

        composable(
            route = Rutas.EDITAR,
            arguments = listOf(navArgument("productoId") { type = NavType.LongType })
        ) { backStackEntry ->
            val productoId = backStackEntry.arguments?.getLong("productoId") ?: 0L
            FormularioProductoScreen(
                viewModel = viewModel,
                productoId = productoId,
                onVolver = { navController.popBackStack() },
                onGuardado = { navController.navigate(Rutas.CATALOGO) }
            )
        }

        composable(
            route = Rutas.DETALLE,
            arguments = listOf(navArgument("productoId") { type = NavType.LongType })
        ) { backStackEntry ->
            val productoId = backStackEntry.arguments?.getLong("productoId") ?: 0L
            DetalleProductoScreen(
                viewModel = viewModel,
                productoId = productoId,
                onVolver = { navController.popBackStack() },
                onEditar = { id -> navController.navigate(Rutas.editar(id)) },
                onEliminado = { navController.navigate(Rutas.CATALOGO) }
            )
        }
    }
}
