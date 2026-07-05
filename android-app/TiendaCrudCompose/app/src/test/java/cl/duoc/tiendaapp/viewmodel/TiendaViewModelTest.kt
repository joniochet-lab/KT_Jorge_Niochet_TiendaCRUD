package cl.duoc.tiendaapp.viewmodel

import cl.duoc.tiendaapp.data.repository.ProductoRepository
import cl.duoc.tiendaapp.model.Producto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TiendaViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun crearProducto_agregaProductoALista() = runTest {
        val repository = FakeProductoRepository()
        val viewModel = TiendaViewModel(repository)
        advanceUntilIdle()

        viewModel.crearProducto(
            Producto(
                nombre = "Mouse",
                descripcion = "Mouse inalámbrico",
                categoria = "Accesorios",
                precio = 12990.0,
                stock = 5
            )
        )
        advanceUntilIdle()

        assertEquals(1, viewModel.uiState.value.productos.size)
        assertEquals("Mouse", viewModel.uiState.value.productos.first().nombre)
    }

    @Test
    fun eliminarProducto_remueveProductoDeLista() = runTest {
        val repository = FakeProductoRepository(
            mutableListOf(
                Producto(
                    id = 1,
                    nombre = "Teclado",
                    descripcion = "Teclado mecánico",
                    categoria = "Accesorios",
                    precio = 39990.0,
                    stock = 3
                )
            )
        )
        val viewModel = TiendaViewModel(repository)
        advanceUntilIdle()

        viewModel.eliminarProducto(1)
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value.productos.isEmpty())
    }
}

private class FakeProductoRepository(
    private val productos: MutableList<Producto> = mutableListOf()
) : ProductoRepository {
    private var nextId = if (productos.isEmpty()) 1L else productos.maxOf { it.id } + 1L

    override suspend fun listarProductos(): List<Producto> = productos.toList()

    override suspend fun obtenerProducto(id: Long): Producto {
        return productos.first { it.id == id }
    }

    override suspend fun crearProducto(producto: Producto): Producto {
        val nuevo = producto.copy(id = nextId++)
        productos.add(nuevo)
        return nuevo
    }

    override suspend fun actualizarProducto(id: Long, producto: Producto): Producto {
        val index = productos.indexOfFirst { it.id == id }
        val actualizado = producto.copy(id = id)
        productos[index] = actualizado
        return actualizado
    }

    override suspend fun eliminarProducto(id: Long) {
        productos.removeAll { it.id == id }
    }
}
