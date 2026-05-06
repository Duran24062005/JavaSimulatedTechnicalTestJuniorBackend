# System Artifact - Product Console App

Fecha: 2026-05-06

## Contexto

La aplicación es un CRUD de productos por consola. El flujo principal se divide en cuatro capas simples:

- `Main`: inicializa dependencias y controla el menú principal.
- `ProductView`: muestra mensajes y captura datos por consola.
- `ProductService`: coordina casos de uso y valida reglas de aplicación.
- `ProductRepository`: almacena productos en memoria y ejecuta operaciones CRUD.
- `ProductDataLoader`: carga productos de prueba desde un archivo CSV local.

## Decisiones aplicadas

### Entrada por consola

La captura de datos se normalizó usando `Scanner.nextLine()` y parseo explícito. Esto evita que la aplicación se rompa cuando el usuario escribe texto donde se espera un número.

También corrige el caso donde un nombre con espacios o números, por ejemplo `Mazda 3`, era dividido por `scanner.next()` y el `3` quedaba pendiente para ser leído como precio.

### Validaciones

Las reglas actuales son:

- El ID debe ser mayor que cero.
- El nombre del producto no puede estar vacío.
- El precio debe ser mayor que cero.
- El stock debe ser mayor o igual que cero.
- Una operación sobre un producto inexistente no debe lanzar excepción.
- Una opción inválida del menú no debe detener el programa.

### Actualización de productos

La actualización se separó en operaciones explícitas:

- Actualizar precio.
- Actualizar stock.

Esto evita que al actualizar solo un campo se sobrescriba accidentalmente el otro con valores por defecto.

### Repositorio

El repositorio mantiene una lista en memoria. Los IDs nuevos se calculan con base en el mayor ID existente, no con el tamaño de la lista. Esto evita duplicados después de eliminar productos.

### Datos de prueba

La aplicación carga productos semilla desde `data/products.csv` al iniciar. También permite recibir una ruta alternativa como primer argumento de ejecución.

El archivo usa el formato:

```csv
name,price,stock
Mazda 3,25000.0,300
```

La carga valida nombre obligatorio, precio mayor que cero y stock mayor o igual que cero. Los productos cargados entran al repositorio en memoria usando el mismo flujo de asignación de IDs.

## Contratos internos

`ProductView` entrega datos ya parseados desde consola.

`ProductService` decide si la operación debe continuar y coordina mensajes de éxito o error.

`ProductRepository` devuelve `false` cuando una operación no puede ejecutarse, por ejemplo producto inexistente o valor inválido.

`ProductDataLoader` devuelve una lista de productos válidos o lanza un error descriptivo si el CSV existe pero tiene formato inválido.

## Limitaciones actuales

- Los productos no persisten al cerrar la aplicación, incluso si fueron cargados desde CSV.
- No hay pruebas automatizadas todavía.
- No existe una capa de excepciones de dominio; se usan retornos booleanos para indicar éxito o error.
- El modelo sigue siendo un POJO simple sin validaciones internas.

## Riesgos pendientes

- Si el proyecto crece, convendría reemplazar retornos booleanos por resultados más expresivos.
- Si se agrega persistencia real, el repositorio deberá cambiar sin afectar la vista ni el servicio.
- Si se agregan pruebas, conviene cubrir validaciones de consola mediante separación adicional entre lector de entrada y vista.
- Si se requiere CSV avanzado, hará falta soportar comillas, escapes y comas dentro de nombres.
