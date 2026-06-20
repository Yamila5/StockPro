package com.example.stockpro.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.stockpro.utils.formatearDinero
import com.example.stockpro.viewmodel.StockViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaReporte(
    viewModel: StockViewModel,
    onVolverCatalogo: () -> Unit
) {
    val valorTotal = viewModel.calcularValorTotalInventario()
    val productosSinStock = viewModel.contarProductosSinStock()

    Scaffold(
        containerColor = Color(0xFF151111),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Reporte Financiero",
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF3A2D2D)),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Capital Invertido Total",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = formatearDinero(valorTotal),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFFD166)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Total de productos con stock en cero: $productosSinStock",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFFE8DCDC)
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = onVolverCatalogo,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al Catalogo")
            }
        }
    }
}
