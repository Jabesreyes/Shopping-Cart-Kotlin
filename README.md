# Carrito de Compras en Kotlin - Facturación en Consola

## Descripción
Este proyecto es un sistema de carrito de compras desarrollado en Kotlin, con facturación en modo consola. El objetivo es practicar conceptos clave de programación orientada a objetos, manejo de colecciones, y la interacción con interfaces de consola. Los usuarios pueden agregar y eliminar productos del carrito, ver el contenido del carrito, y generar una factura con la compra final.

## Estructura del Proyecto

El proyecto está organizado siguiendo buenas prácticas de programación y principios SOLID, con una clara separación de responsabilidades entre las distintas clases y componentes. A continuación, se describe la estructura del código:

```
/src
  /models           --> Contiene los modelos principales (Producto, Carrito, Factura)
  /services         --> Lógica de negocio para el carrito y la facturación (CarritoService, FacturacionService)
  /interfaces       --> Definición de interfaces para las operaciones del carrito y facturación
  /utils            --> Utilidades y funciones de validación (en caso de ser necesario agregar)
  /ui               --> Manejo de la interacción con el usuario a través de la consola (Menus, Inputs)
  Main.kt           --> Punto de entrada del programa
```

### Descripción de Clases

- **Producto**: Representa un producto disponible en la tienda, con atributos como ID, nombre, precio y cantidad disponible.
- **Carrito**: Representa el carrito de compras del usuario, con la lista de productos seleccionados.
- **Factura**: Representa la factura generada al finalizar la compra, mostrando los productos adquiridos, el total y los impuestos.

### Servicios

- **CarritoService**: Gestiona la lógica relacionada con agregar productos al carrito, eliminar productos, y visualizar el carrito.
- **FacturacionService**: Se encarga de generar la factura de la compra, calculando el total e impuestos aplicables.

### Interfaz de Usuario

- **Menu**: Controla la interacción con el usuario, proporcionando un menú de opciones para agregar productos, eliminar productos, ver el carrito y finalizar la compra generando una factura.

## Requisitos

- Kotlin 2.0 o superior
- JDK 17 o superior

## Cómo Ejecutar la Aplicación

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/Jabesreyes/Shopping-Cart-Kotlin
   ```

2. **Navegar al directorio del proyecto**:
   ```bash
   cd Shopping-Cart-Kotlin
   ```

3. **Compilar y ejecutar el proyecto**:
   Utiliza un IDE que soporte Kotlin (como IntelliJ IDEA) o ejecuta el siguiente comando desde la terminal si tienes Kotlin configurado en tu entorno:

   ```bash
   kotlinc src -include-runtime -d carrito.jar && java -jar carrito.jar
   ```

4. **Interacción con el programa**:
   El programa te presentará un menú en la consola con las siguientes opciones:
   - Ver la lista de productos disponibles
   - Agregar productos al carrito
   - Eliminar productos del carrito
   - Ver el contenido actual del carrito
   - Finalizar la compra y generar la factura

## Ejemplo de Uso

```
--- Menú Principal ---
1. Ver Productos
2. Agregar Producto al Carrito
3. Ver Carrito
4. Eliminar Producto del Carrito
5. Finalizar y Generar Factura
6. Salir
Seleccione una opción: 
```

## Mejoras Futuras

- Implementación de almacenamiento persistente para el carrito y productos.
- Opción de aplicar descuentos o cupones.
- Soporte para múltiples usuarios.

## Contribuciones
Si deseas contribuir a este proyecto, por favor abre un **Pull Request** o **Issue** en el repositorio.

