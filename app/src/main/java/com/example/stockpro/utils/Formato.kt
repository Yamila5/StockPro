package com.example.stockpro.utils

import java.text.NumberFormat
import java.util.Locale

fun formatearDinero(valor: Double): String {
    return NumberFormat.getCurrencyInstance(Locale.US).format(valor)
}