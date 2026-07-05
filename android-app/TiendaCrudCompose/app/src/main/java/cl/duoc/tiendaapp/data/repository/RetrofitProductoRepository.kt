package cl.duoc.tiendaapp.data.repository

import cl.duoc.tiendaapp.data.remote.RetrofitClient
import cl.duoc.tiendaapp.model.Producto

class RetrofitProductoRepository : ProductoRepository {

    private val api = RetrofitClient.api

    override suspend fun listarProductos(): List<Producto> {
        return api.listarProductos()
    }

    override suspend fun obtenerProducto(id: Long): Producto {
        return api.obtenerProducto(id)
    }

    override suspend fun crearProducto(producto: Producto): Producto {
        return api.crearProducto(producto)
    }

    override suspend fun actualizarProducto(id: Long, producto: Producto): Producto {
        return api.actualizarProducto(id, producto)
    }

    override suspend fun eliminarProducto(id: Long) {
        api.eliminarProducto(id)
    }
}
