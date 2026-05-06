# PRD - Correcciones de validación y calidad de código

Fecha: 2026-05-06

## Problema

La aplicación se rompía o producía resultados incorrectos cuando el usuario ingresaba datos inesperados en consola. El caso más visible era crear un producto con nombre `Mazda 3`: la aplicación guardaba `Mazda` como nombre y tomaba `3` como precio.

También existían riesgos de calidad:

- Uso de `scanner.next()`, `nextInt()` y `nextDouble()` mezclados.
- Posibles `NullPointerException` al mostrar productos inexistentes.
- Mensajes de éxito después de operaciones fallidas.
- Actualización de precio y stock mezclada en un mismo objeto con valores por defecto.
- IDs duplicables después de eliminar productos.

## Objetivo

Mejorar la robustez y mantenibilidad del CRUD de productos por consola, evitando errores por entradas inválidas y dejando reglas básicas documentadas.

## Alcance

Incluido:

- Validar enteros, decimales y textos desde consola.
- Permitir nombres con espacios y números.
- Validar ID, nombre, precio y stock.
- Corregir mensajes de error y éxito.
- Separar actualización de precio y stock.
- Evitar IDs duplicados después de eliminaciones.
- Documentar decisiones técnicas y reglas de validación.

No incluido:

- Persistencia en base de datos o archivos.
- Pruebas unitarias automatizadas.
- Refactor completo a arquitectura por interfaces.
- Manejo avanzado de errores con clases de resultado o excepciones de dominio.

## Usuarios afectados

Usuario de consola que administra productos.

## Comportamiento esperado

### Crear producto

El usuario puede ingresar nombres completos como:

```text
Mazda 3
```

Ese valor se conserva completo como nombre del producto. El número dentro del nombre no se usa como precio.

### Precio

El precio debe ser numérico y mayor que cero. Si el usuario ingresa texto o un valor inválido, la aplicación vuelve a pedir el dato.

### Stock

El stock debe ser numérico y mayor o igual que cero. Si el usuario ingresa texto o un valor negativo, la aplicación vuelve a pedir el dato.

### Búsqueda, actualización y eliminación

El ID debe ser mayor que cero. Si el producto no existe, la aplicación muestra un mensaje y continúa funcionando.

## Enfoque de implementación

`ProductView` concentra la lectura segura desde consola mediante métodos reutilizables:

- `getInt`
- `getDouble`
- `getPositiveInt`
- `getNonNegativeInt`
- `getPositiveDouble`
- `getRequiredText`

`ProductService` valida el flujo de cada caso de uso y evita continuar cuando una operación no puede ejecutarse.

`ProductRepository` expone operaciones específicas para actualizar precio y stock, y calcula nuevos IDs usando el mayor ID existente.

## Impacto en datos y contratos

No cambia la estructura de `ProductModel`.

Sí cambia el contrato interno del repositorio:

- Se reemplaza la actualización genérica por `updateProductPrice` y `updateProductStock`.

Sí cambia el contrato interno de la vista:

- La vista deja de exponer `update()` como construcción parcial de producto.
- Ahora expone `getUpdateOption()` y métodos específicos de lectura validada.

## Validaciones

- Nombre requerido.
- Precio mayor que cero.
- Stock mayor o igual que cero.
- ID mayor que cero.
- Producto existente para actualizar o eliminar.
- Opción inválida no debe detener el programa.

## Riesgos y casos borde

- El uso de `Scanner` sigue acoplado a `System.in`, lo que dificulta pruebas automatizadas de consola.
- Los mensajes siguen estando en la vista y en algunos flujos del servicio; si crece la aplicación, puede convenir centralizarlos.
- El repositorio en memoria no resuelve persistencia ni concurrencia.

## Preguntas abiertas

- ¿Debe permitirse precio `0` para productos promocionales?
- ¿Debe permitirse stock `0` para productos agotados? Actualmente sí.
- ¿Debe existir actualización del nombre del producto?
- ¿Debe agregarse persistencia en archivo o base de datos?
