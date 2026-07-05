package cl.duoc.tiendaapp.data.repository

import cl.duoc.tiendaapp.model.Producto

interface ProductoRepository {
    suspend fun listarProductos(): List<Producto>
    suspend fun obtenerProducto(id: Long): Producto
    suspend fun crearProducto(producto: Producto): Producto
    suspend fun actualizarProducto(id: Long, producto: Producto): Producto
    suspend fun eliminarProducto(id: Long)
}
