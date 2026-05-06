# PRD - Carga de datos de prueba

Fecha: 2026-05-06

## Problema

Para probar el CRUD era necesario crear productos manualmente cada vez que iniciaba la aplicación. Esto hacía más lenta la validación de listados, búsquedas, actualizaciones y eliminaciones.

## Objetivo

Permitir que la aplicación cargue productos de prueba desde un archivo local para acelerar las pruebas manuales del sistema.

## Alcance

Incluido:

- Crear carpeta `data`.
- Crear archivo `data/products.csv` con productos de prueba.
- Cargar automáticamente el archivo al iniciar la aplicación.
- Permitir indicar una ruta CSV alternativa como argumento de ejecución.
- Validar el formato y los valores del archivo.
- Documentar el flujo en `README.md`.

No incluido:

- Persistencia permanente de cambios realizados durante la ejecución.
- Importación desde base de datos.
- Exportación de productos.
- Soporte completo para CSV avanzado con comillas, escapes o comas dentro del nombre.

## Usuarios afectados

Usuario que prueba la aplicación por consola.

## Comportamiento esperado

Al ejecutar:

```bash
java -cp target/classes org.example.Main
```

La aplicación intenta cargar:

```text
data/products.csv
```

Si el archivo existe y es válido, los productos quedan disponibles en el listado inicial.

Si el archivo no existe, la aplicación inicia sin productos precargados.

Si el archivo existe pero tiene datos inválidos, la aplicación muestra un mensaje de error y continúa funcionando.

## Formato del archivo

```csv
name,price,stock
Mazda 3,25000.0,300
```

Reglas:

- `name` es obligatorio.
- `price` debe ser mayor que cero.
- `stock` debe ser mayor o igual que cero.
- Cada fila debe tener tres columnas.

## Enfoque de implementación

Se agregó `ProductDataLoader` como componente responsable de leer y validar el archivo CSV.

`Main` resuelve la ruta del archivo:

- Usa `data/products.csv` por defecto.
- Usa `args[0]` si se pasa una ruta alternativa.

Después de cargar los productos, `Main` los registra en `ProductRepository` usando el flujo existente de creación.

## Impacto en contratos

No cambia el contrato público del menú.

Se agrega un contrato de archivo local:

```text
name,price,stock
```

El sistema sigue usando almacenamiento en memoria. Los datos cargados son solo una semilla inicial para pruebas.

## Riesgos y casos borde

- Un nombre con coma no está soportado en esta versión.
- Los cambios realizados en consola no se guardan de vuelta en `data/products.csv`.
- Si se ejecuta desde otro directorio de trabajo, la ruta relativa `data/products.csv` debe existir desde esa ubicación.
