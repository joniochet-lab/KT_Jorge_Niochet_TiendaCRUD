package cl.duoc.tiendaapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.duoc.tiendaapp.R
import cl.duoc.tiendaapp.model.Producto
import cl.duoc.tiendaapp.viewmodel.TiendaViewModel

@Composable
fun CatalogoScreen(
    viewModel: TiendaViewModel,
    onAgregarProducto: () -> Unit,
    onEditarProducto: (Long) -> Unit,
    onVerDetalle: (Long) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarProductos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Catálogo de productos",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text("CRUD completo mediante Retrofit + microservicio")

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onAgregarProducto,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar producto")
        }

        Spacer(modifier = Modifier.height(8.dp))

        state.mensaje?.let {
            Text(text = it, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (state.cargando) {
            CircularProgressIndicator()
        }

        if (!state.cargando && state.productos.isEmpty()) {
            Text("No hay productos registrados.")
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(state.productos, key = { it.id }) { producto ->
                ProductoCard(
                    producto = producto,
                    onVerDetalle = { onVerDetalle(producto.id) },
                    onEditar = { onEditarProducto(producto.id) },
                    onEliminar = { viewModel.eliminarProducto(producto.id) }
                )
            }
        }
    }
}

@Composable
private fun ProductoCard(
    producto: Producto,
    onVerDetalle: () -> Unit,
    onEditar: () -> Unit,
    onEliminar: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.imagen_referencial),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(producto.descripcion)
            Text("Categoría: ${producto.categoria}")
            Text("Precio: $${producto.precio.toInt()}")
            Text("Stock: ${producto.stock}")

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(onClick = onVerDetalle, modifier = Modifier.weight(1f)) {
                    Text("Detalle")
                }
                OutlinedButton(onClick = onEditar, modifier = Modifier.weight(1f)) {
                    Text("Editar")
                }
                Button(onClick = onEliminar, modifier = Modifier.weight(1f)) {
                    Text("Eliminar")
                }
            }
        }
    }
}
