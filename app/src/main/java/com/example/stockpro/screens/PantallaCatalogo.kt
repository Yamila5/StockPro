package com.example.stockpro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockpro.model.Producto
import com.example.stockpro.utils.formatearDinero
import com.example.stockpro.viewmodel.StockViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCatalogo(
    nombreOperario: String,
    viewModel: StockViewModel,
    onProductoClick: (Int) -> Unit,
    onReporteClick: () -> Unit
) {
    var mostrarCriticos by remember { mutableStateOf(false) }
    val productosVisibles = if (mostrarCriticos) {
        viewModel.obtenerProductosEnRiesgo()
    } else {
        viewModel.productos
    }
    Scaffold(
        containerColor = Color(0xFF151111),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Catalogo StockPro",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Operario: $nombreOperario",
                            fontSize = 14.sp,
                            color = Color(0xFFD7C7C7)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF151111)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onReporteClick,
                containerColor = Color(0xFFB83939),
                contentColor = Color.White
            ) {
                Text("$")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { mostrarCriticos = false },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Ver Todo")
                }

                Spacer(modifier = Modifier.width(12.dp))

                OutlinedButton(
                    onClick = { mostrarCriticos = true },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Stock Critico")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 88.dp)
            ) {
                items(productosVisibles, key = { it.id }) { producto ->
                    ProductoCard(
                        producto = producto,
                        onClick = { onProductoClick(producto.id) }
                    )
                }
            }
        }
    }
}
@Composable
fun ProductoCard(producto: Producto, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF3A2D2D)),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column {
            ProductoBanner(producto = producto)

            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = producto.nombre,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = producto.descripcion,
                    color = Color(0xFFD7C7C7),
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Precio unitario: ${formatearDinero(producto.precio)}",
                    color = Color(0xFFE8DCDC)
                )
                Text(
                    text = "Stock actual: ${producto.stockActual}",
                    color = if (producto.stockActual < 5) Color(0xFFFF5A5A) else Color(0xFFE8DCDC),
                    fontWeight = if (producto.stockActual < 5) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun ProductoBanner(producto: Producto) {
    val colores = when (producto.id) {
        1 -> listOf(Color(0xFF344E41), Color(0xFF588157))
        2 -> listOf(Color(0xFF7F5539), Color(0xFFB08968))
        3 -> listOf(Color(0xFF283618), Color(0xFF606C38))
        4 -> listOf(Color(0xFF5F0F40), Color(0xFF9A031E))
        5 -> listOf(Color(0xFF003049), Color(0xFF669BBC))
        else -> listOf(Color(0xFF432818), Color(0xFF99582A))
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .background(Brush.horizontalGradient(colores))
            .padding(14.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = producto.nombre.take(1),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
