package cl.duoc.tiendaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.tiendaapp.data.repository.ProductoRepository
import cl.duoc.tiendaapp.data.repository.RetrofitProductoRepository
import cl.duoc.tiendaapp.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TiendaViewModel(
    private val repository: ProductoRepository = RetrofitProductoRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductoUiState())
    val uiState: StateFlow<ProductoUiState> = _uiState.asStateFlow()

    init {
        cargarProductos()
    }

    fun limpiarMensaje() {
        _uiState.update { it.copy(mensaje = null) }
    }

    fun cargarProductos() {
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, mensaje = null) }
            try {
                val productos = repository.listarProductos()
                _uiState.update {
                    it.copy(productos = productos, cargando = false, mensaje = null)
                }
            } catch (ex: Exception) {
                _uiState.update {
                    it.copy(
                        cargando = false,
                        mensaje = "No se pudieron cargar los productos. Revisa que el microservicio esté encendido."
                    )
                }
            }
        }
    }

    fun cargarProducto(id: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, mensaje = null) }
            try {
                val producto = repository.obtenerProducto(id)
                _uiState.update {
                    it.copy(productoSeleccionado = producto, cargando = false, mensaje = null)
                }
            } catch (ex: Exception) {
                _uiState.update {
                    it.copy(cargando = false, mensaje = "No se pudo cargar el producto seleccionado.")
                }
            }
        }
    }

    fun crearProducto(producto: Producto, onSuccess: () -> Unit = {}) {
        if (!producto.esValido()) {
            _uiState.update { it.copy(mensaje = "Completa nombre, categoría, precio mayor a 0 y stock válido.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, mensaje = null) }
            try {
                repository.crearProducto(producto.copy(id = 0))
                val productos = repository.listarProductos()
                _uiState.update {
                    it.copy(productos = productos, cargando = false, mensaje = "Producto agregado correctamente.")
                }
                onSuccess()
            } catch (ex: Exception) {
                _uiState.update { it.copy(cargando = false, mensaje = "No se pudo agregar el producto.") }
            }
        }
    }

    fun actualizarProducto(id: Long, producto: Producto, onSuccess: () -> Unit = {}) {
        if (!producto.esValido()) {
            _uiState.update { it.copy(mensaje = "Completa nombre, categoría, precio mayor a 0 y stock válido.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, mensaje = null) }
            try {
                val actualizado = repository.actualizarProducto(id, producto.copy(id = id))
                val productos = repository.listarProductos()
                _uiState.update {
                    it.copy(
                        productos = productos,
                        productoSeleccionado = actualizado,
                        cargando = false,
                        mensaje = "Producto actualizado correctamente."
                    )
                }
                onSuccess()
            } catch (ex: Exception) {
                _uiState.update { it.copy(cargando = false, mensaje = "No se pudo actualizar el producto.") }
            }
        }
    }

    fun eliminarProducto(id: Long, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, mensaje = null) }
            try {
                repository.eliminarProducto(id)
                val productos = repository.listarProductos()
                _uiState.update {
                    it.copy(
                        productos = productos,
                        productoSeleccionado = null,
                        cargando = false,
                        mensaje = "Producto eliminado correctamente."
                    )
                }
                onSuccess()
            } catch (ex: Exception) {
                _uiState.update { it.copy(cargando = false, mensaje = "No se pudo eliminar el producto.") }
            }
        }
    }
}

fun Producto.esValido(): Boolean {
    return nombre.isNotBlank() && categoria.isNotBlank() && precio > 0.0 && stock >= 0
}
