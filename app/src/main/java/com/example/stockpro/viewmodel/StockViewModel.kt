package com.example.stockpro.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.stockpro.model.Producto

class StockViewModel : ViewModel() {
    val productos = mutableStateListOf(
        Producto(1, "Taladro Industrial", "Herramienta electrica para perforacion.", 85.50, 8),
        Producto(2, "Casco de Seguridad", "Casco reforzado para trabajo en bodega.", 18.75, 4),
        Producto(3, "Guantes Anticorte", "Guantes de proteccion para manipulacion.", 12.00, 15),
        Producto(4, "Caja de Tornillos", "Caja con 100 tornillos galvanizados.", 9.25, 3),
        Producto(5, "Cinta de Embalaje", "Rollo adhesivo para sellado de cajas.", 2.80, 0),
        Producto(6, "Montacargas Manual", "Equipo manual para mover pallets.", 240.00, 2)
    )

    fun obtenerProducto(id: Int): Producto? {
        return productos.find { it.id == id }
    }

    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        val indice = productos.indexOfFirst { it.id == id }
        if (indice != -1 && nuevaCantidad >= 0) {
            productos[indice] = productos[indice].copy(stockActual = nuevaCantidad)
        }
    }

    fun calcularValorTotalInventario(): Double {
        return productos.sumOf { it.precio * it.stockActual }
    }

    fun obtenerProductosEnRiesgo(): List<Producto> {
        return productos.filter { it.stockActual < 5 }
    }

    fun contarProductosSinStock(): Int {
        return productos.count { it.stockActual == 0 }
    }
}
