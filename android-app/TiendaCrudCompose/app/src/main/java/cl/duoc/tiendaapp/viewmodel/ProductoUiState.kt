package cl.duoc.tiendaapp.viewmodel

import cl.duoc.tiendaapp.model.Producto

data class ProductoUiState(
    val productos: List<Producto> = emptyList(),
    val productoSeleccionado: Producto? = null,
    val cargando: Boolean = false,
    val mensaje: String? = null
)
