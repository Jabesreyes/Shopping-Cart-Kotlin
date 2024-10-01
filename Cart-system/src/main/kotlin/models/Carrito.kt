package com.mock.models

data class Carrito(
    val productos: MutableList<ProductoSeleccionado> = mutableListOf()
)

data class ProductoSeleccionado(
    val producto: Producto,
    var cantidad: Int
)