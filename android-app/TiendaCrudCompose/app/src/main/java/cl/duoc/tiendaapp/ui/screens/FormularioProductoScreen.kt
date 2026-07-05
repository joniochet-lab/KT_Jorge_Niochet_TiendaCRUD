package cl.duoc.tiendaapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.duoc.tiendaapp.model.Producto
import cl.duoc.tiendaapp.viewmodel.TiendaViewModel

@Composable
fun FormularioProductoScreen(
    viewModel: TiendaViewModel,
    productoId: Long?,
    onVolver: () -> Unit,
    onGuardado: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val esEdicion = productoId != null && productoId > 0

    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }

    LaunchedEffect(productoId) {
        if (esEdicion) {
            viewModel.cargarProducto(productoId ?: 0)
        }
    }

    LaunchedEffect(state.productoSeleccionado?.id) {
        val producto = state.productoSeleccionado
        if (esEdicion && producto != null && producto.id == productoId) {
            nombre = producto.nombre
            descripcion = producto.descripcion
            categoria = producto.categoria
            precio = producto.precio.toString()
            stock = producto.stock.toString()
            imagenUrl = producto.imagenUrl
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = if (esEdicion) "Modificar producto" else "Agregar producto",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoría") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = stock,
            onValueChange = { stock = it },
            label = { Text("Stock") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = imagenUrl,
            onValueChange = { imagenUrl = it },
            label = { Text("URL imagen opcional") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        state.mensaje?.let {
            Text(text = it, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                val producto = Producto(
                    id = productoId ?: 0,
                    nombre = nombre.trim(),
                    descripcion = descripcion.trim(),
                    categoria = categoria.trim(),
                    precio = precio.toDoubleOrNull() ?: 0.0,
                    stock = stock.toIntOrNull() ?: -1,
                    imagenUrl = imagenUrl.trim()
                )

                if (esEdicion) {
                    viewModel.actualizarProducto(productoId ?: 0, producto, onSuccess = onGuardado)
                } else {
                    viewModel.crearProducto(producto, onSuccess = onGuardado)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (esEdicion) "Guardar cambios" else "Crear producto")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}
