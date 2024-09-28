package com.mock.services

import com.mock.interfaces.FacturacionService
import com.mock.models.Carrito
import com.mock.models.Factura

class FacturacionServiceImpl(private val carrito: Carrito) : FacturacionService {

    override fun generarFactura(): Factura {
        val total = carrito.productos.sumOf { it.producto.precio * it.cantidad }
        val impuestos = total * 0.13
        val totalConImpuestos = total + impuestos

        return Factura(
            productos = carrito.productos,
            total = total,
            impuestos = impuestos,
            totalConImpuestos = totalConImpuestos
        )
    }
}