package com.mock.interfaces

import com.mock.models.Factura

interface FacturacionService {
    fun generarFactura(): Factura
}