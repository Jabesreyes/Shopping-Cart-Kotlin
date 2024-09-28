package com.mock.interfaces

import com.mock.models.Producto

interface CarritoService {
    fun agregarProducto(producto: Producto, cantidad: Int)
    fun eliminarProducto(idProducto: Int)
    fun verCarrito()
    fun calcularTotal(): Double
}