package com.mock.services

import com.mock.interfaces.CarritoService
import com.mock.models.Carrito
import com.mock.models.Producto
import java.text.DecimalFormat

class CarritoServiceImpl(private val carrito: Carrito) : CarritoService {

    private val decimalFormat = DecimalFormat("0.00")

    // Implementar el método de eliminarProducto
    override fun eliminarProducto(idProducto: Int) {
        // Intentamos encontrar el producto por su ID
        val productoAEliminar = carrito.productos.find { it.producto.id == idProducto }

        if (productoAEliminar != null) {
            carrito.productos.removeIf { it.producto.id == idProducto }
            println("El producto '${productoAEliminar.producto.nombre}' ha sido eliminado del carrito.")
        } else {
            println("El producto con ID $idProducto no se encuentra en el carrito.")
        }
    }

    override fun agregarProducto(producto: Producto, cantidad: Int) {
        // Verifica si la cantidad solicitada es mayor a la disponible
        if (cantidad > producto.cantidadDisponible) {
            println("Cantidad no disponible. Solo quedan ${producto.cantidadDisponible} unidades de ${producto.nombre}.")
            return
        }

        // Busca si el producto ya esta en el carrito
        val productoExistente = carrito.productos.find { it.producto.id == producto.id }

        if (productoExistente != null) {
            // Si ya esta en el carrito, aumentar la cantidad
            val nuevaCantidad = productoExistente.cantidad + cantidad
            if (nuevaCantidad > producto.cantidadDisponible) {
                println("No puedes agregar más de ${producto.cantidadDisponible} unidades de ${producto.nombre}.")
            } else {
                productoExistente.cantidad = nuevaCantidad
                println("Se agregaron $cantidad unidades de ${producto.nombre} al carrito.")
            }
        } else {
            // Si no esta en el carrito, agregarlo
            carrito.productos.add(ProductoSeleccionado(producto, cantidad))
            println("${producto.nombre} se ha agregado al carrito con $cantidad unidades.")
        }

        // Actualiza la cantidad disponible del producto
        producto.cantidadDisponible -= cantidad
    }

    override fun eliminarProducto(idProducto: Int) {
        //Implementación del método eliminarProducto
        val productoEnCarrito = carrito.productos.find { it.producto.id == idProducto }
        if (productoEnCarrito != null) {
            carrito.productos.remove(productoEnCarrito)
            println("Producto eliminado del carrito.")
        } else {
            println("Producto no encontrado en el carrito.")
            }
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