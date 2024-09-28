package com.mock.services

import com.mock.interfaces.CarritoService
import com.mock.models.Carrito
import com.mock.models.Producto
import com.mock.models.ProductoSeleccionado
import java.text.DecimalFormat

class CarritoServiceImpl(private val carrito: Carrito) : CarritoService {

    private val decimalFormat = DecimalFormat("0.00")

    override fun agregarProducto(producto: Producto, cantidad: Int) {
        // Implementación del método agregarProducto
    }

    override fun eliminarProducto(idProducto: Int) {
       // Implementación del método eliminarProducto
    }

    override fun verCarrito() {
        if (carrito.productos.isEmpty()) {
            println("El carrito está vacío")
            return
        }
        println("\n--- Carrito de Compras ---")
        carrito.productos.forEach {
            val totalPorProducto = decimalFormat.format(it.producto.precio * it.cantidad)
            println("${it.producto.nombre} - Cantidad: ${it.cantidad} - Precio Unitario: $${decimalFormat.format(it.producto.precio)} - Total: $$totalPorProducto")
        }
        println("Total: $${decimalFormat.format(calcularTotal())}")
    }

    override fun calcularTotal(): Double {
        return carrito.productos.sumOf { it.producto.precio * it.cantidad }
    }
}