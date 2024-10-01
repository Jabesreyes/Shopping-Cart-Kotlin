package com.mock.ui

import com.mock.models.Carrito
import com.mock.models.Producto
import com.mock.models.ProductoSeleccionado
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

    /**
     * Permite al usuario agregar productos al carrito validando el ID del producto y la cantidad.
     *
     * Se solicita al usuario un ID de producto y una cantidad, ambos validados. Si son validas,
     * la función llama a `agregarProducto` en `CarritoServiceImpl` para incluir el producto al carrito.
     *
     * El proceso se repite mientras el usuario desee agregar más productos.
     */
    private fun agregarProductoAlCarrito() {
        try{
            println("\n--- Agregar Producto al Carrito ---")
            mostrarProductos()

            var continuar: String

            do{
                println("Ingrese el ID del producto que desea agregar: ")
                var idProducto = readLine()?.toIntOrNull()

                // Validar que el ID ingresado sea válido
                var productoSeleccionado = productosDisponibles.find { it.id == idProducto }

                while(productoSeleccionado == null) {
                    println("ID de producto no válido.")
                    println("Ingrese el ID del producto que desea agregar: ")
                    idProducto = readLine()?.toIntOrNull()
                    productoSeleccionado = productosDisponibles.find { it.id == idProducto }
                }

                // Solicitar la cantidad a agregar
                print("Ingrese la cantidad que desea agregar: ")
                var cantidad = readLine()?.toIntOrNull()

                // Validar que la cantidad sea un número válido y mayor que 0
                while (cantidad == null || cantidad <= 0) {
                    println("Cantidad no válida.")
                    print("Ingrese la cantidad que desea agregar: ")
                    cantidad = readLine()?.toIntOrNull()
                }

                carritoService.agregarProducto(productoSeleccionado, cantidad)
                println("¿Desea agregar otro producto al carrito? (s/n)")
                continuar = readLine().toString()
            }while (continuar == "s" || continuar == "S")
            
        }catch (e: Exception){
            println("Error al agregar producto al carrito")
        }

    }

    private fun eliminarProductoDelCarrito() {
        // Implementación del método eliminarProductoDelCarrito
        if (carrito.productos.isEmpty()) {
            println("El carrito está vacío. No hay productos para eliminar.")
            return
        }

        println("\n--- Eliminar Producto del Carrito ---")
        carritoService.verCarrito()  // Mostrar productos en el carrito antes de eliminarlos
        print("Ingrese el ID del producto que desea eliminar: ")

        val idProducto = readLine()?.toIntOrNull()
        if (idProducto != null) {
            carritoService.eliminarProducto(idProducto)
        } else {
            println("ID no válido. Por favor, ingrese un número válido.")
        }
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