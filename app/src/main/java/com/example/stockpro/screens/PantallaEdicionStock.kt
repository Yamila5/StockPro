package com.example.stockpro.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockpro.viewmodel.StockViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEdicionStock(
    productoId: Int,
    viewModel: StockViewModel,
    onGuardarVolver: () -> Unit
) {
    val producto = viewModel.obtenerProducto(productoId)

    Scaffold(
        containerColor = Color(0xFF151111),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Edicion de Stock",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF151111)
                )
            )
        }
    ) { padding ->
        if (producto == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Producto no encontrado", color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onGuardarVolver) {
                    Text("Volver")
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = producto.nombre,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = producto.descripcion,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFFD7C7C7)
                )

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Stock actual",
                    fontSize = 18.sp,
                    color = Color(0xFFE8DCDC)
                )
                Text(
                    text = producto.stockActual.toString(),
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (producto.stockActual < 5) Color(0xFFFF5A5A) else Color.White
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Button(
                        onClick = {
                            viewModel.actualizarStock(producto.id, producto.stockActual - 1)
                        },
                        enabled = producto.stockActual > 0
                    ) {
                        Text("-1")
                    }

                    Button(
                        onClick = {
                            viewModel.actualizarStock(producto.id, producto.stockActual + 1)
                        }
                    ) {
                        Text("+1")
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onGuardarVolver,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar y Volver")
                }
            }
        }
    }
}