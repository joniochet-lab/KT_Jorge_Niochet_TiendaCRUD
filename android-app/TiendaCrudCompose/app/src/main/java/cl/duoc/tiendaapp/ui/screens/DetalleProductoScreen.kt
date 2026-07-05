package cl.duoc.tiendaapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.duoc.tiendaapp.R
import cl.duoc.tiendaapp.viewmodel.TiendaViewModel

@Composable
fun DetalleProductoScreen(
    viewModel: TiendaViewModel,
    productoId: Long,
    onVolver: () -> Unit,
    onEditar: (Long) -> Unit,
    onEliminado: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(productoId) {
        viewModel.cargarProducto(productoId)
    }

    val producto = state.productoSeleccionado

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Detalle del producto",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (producto == null) {
            Text("Cargando producto o producto no disponible.")
        } else {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.imagen_referencial),
                        contentDescription = producto.nombre,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(producto.nombre, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(producto.descripcion)
                    Text("Categoría: ${producto.categoria}")
                    Text("Precio: $${producto.precio.toInt()}")
                    Text("Stock: ${producto.stock}")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onEditar(producto.id) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Modificar producto")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { viewModel.eliminarProducto(producto.id, onSuccess = onEliminado) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Eliminar producto")
            }
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
