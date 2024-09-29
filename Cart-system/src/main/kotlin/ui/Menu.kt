package com.mock.ui

import com.mock.models.Carrito
import com.mock.models.Producto
import com.mock.services.CarritoServiceImpl
import com.mock.services.FacturacionServiceImpl
import java.text.DecimalFormat

class Menu {

    private val decimalFormat = DecimalFormat("0.00")
    private val carrito = Carrito()
    private val carritoService = CarritoServiceImpl(carrito)
    private val facturacionService = FacturacionServiceImpl(carrito)

    private val productosDisponibles = listOf(
        Producto(1, "Chocolate", 3.50, 10),
        Producto(2, "Galletas", 2.50, 15),
        Producto(3, "Caramelo", 0.50, 20),
        Producto(4, "Paleta", 1.50, 25),
        Producto(5, "Chicle", 0.25, 30)
    )

    fun mostrarMenuPrincipal() {
        while (true) {
            println("\n--- Menú Principal ---")
            println("1. Ver Productos")
            println("2. Agregar Producto al Carrito")
            println("3. Ver Carrito")
            println("4. Eliminar Producto del Carrito")
            println("5. Finalizar y Generar Factura")
            println("6. Salir")
            print("Seleccione una opción: ")

            when (readLine()?.toIntOrNull()) {
                1 -> mostrarProductos()
                2 -> agregarProductoAlCarrito()
                3 -> carritoService.verCarrito()
                4 -> eliminarProductoDelCarrito()
                5 -> finalizarCompra()
                6 -> break
                else -> println("Opción no válida")
            }
        }
    }

    private fun mostrarProductos() {
        println("\n--- Productos Disponibles ---")
        productosDisponibles.forEach {
            println("${it.id}. ${it.nombre} - Precio: $${decimalFormat.format(it.precio)} - Cantidad disponible: ${it.cantidadDisponible}")
        }
    }

    private fun agregarProductoAlCarrito(){
        /**
         * Esta función permite al usuario agregar un producto al carrito de compras.
         * El usuario ingresa el ID del producto y la cantidad deseada, y la funcion valida que ambos
         * sean correctos. Si la entrada es valida, el producto se añade al carrito.
         * Si el usuario cancela (ingresa 0) o si los datos son incorrectos, la operación se detiene.
         */

        println("\n--- Agregar Producto al Carrito ---")

        var idProducto: Int?
        var producto: Producto?

        // Bucle para obtener un ID de producto válido
        do {
            print("Ingrese el ID del producto para agregar al carrito (ingrese 0 para terminar): ")
            idProducto = readLine()?.toIntOrNull()

            // Si el usuario ingresa 0, cancela la operación
            if (idProducto == 0) {
                println("Operación cancelada.")
                return
            }
            // Busca el producto por ID en la lista de productos disponibles
            producto = productosDisponibles.find { it.id == idProducto }

            if (producto == null) {
                println("ID de producto no válido. Intente nuevamente.")
            }
        } while (producto == null)

        var cantidad: Int?

        do {
            print("Ingrese la cantidad que desea agregar al carrito (0 para terminar): ")
            cantidad = readLine()?.toIntOrNull()

            if (cantidad == 0) {
                println("Operación terminada.")
                return
            }

            if (cantidad == null || cantidad < 0) {
                println("Cantidad no válida. Debe ingresar un número positivo.")
            }else if (cantidad > producto.cantidadDisponible){
                println("Cantidad no válida. No hay suficiente stock disponible.")
            }

        } while (cantidad == null || cantidad < 0 || cantidad > producto.cantidadDisponible)

        carritoService.agregarProducto(producto, cantidad)
        // Muestra un mensaje confirmando que el producto fue agregado al carrito
        println("--- Se ha agregado ${cantidad} ${producto.nombre} al carrito  ---")

    }

    private fun eliminarProductoDelCarrito() {
       // Implementación del método eliminarProductoDelCarrito
    }

    private fun finalizarCompra() {
        val factura = facturacionService.generarFactura()
        println("\n--- Factura ---")
        factura.productos.forEach {
            val totalPorProducto = decimalFormat.format(it.producto.precio * it.cantidad)
            println("${it.producto.nombre} - Cantidad: ${it.cantidad} - Precio Unitario: $${decimalFormat.format(it.producto.precio)} - Total: $$totalPorProducto")
        }
        println("Impuestos: $${decimalFormat.format(factura.impuestos)}")
        println("Total con Impuestos: $${decimalFormat.format(factura.totalConImpuestos)}")
        carrito.productos.clear()
    }

}