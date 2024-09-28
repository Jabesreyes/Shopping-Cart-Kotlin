package com.mock.models

data class Factura(
    val productos: List<ProductoSeleccionado>,
    val total: Double,
    val impuestos: Double,
    val totalConImpuestos: Double
)
