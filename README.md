# Java Simulated Technical Test - Junior Backend

Aplicación de consola en Java para gestionar productos en memoria. Permite listar, buscar, crear, actualizar y eliminar productos.

## Requisitos

- Java 21
- Maven

## Estructura principal

```text
src/main/java/org/example
├── Main.java
├── data/ProductDataLoader.java
├── model/ProductModel.java
├── repository/ProductRepository.java
├── service/ProductService.java
└── view/ProductView.java

data/
└── products.csv

docs/
├── Prueba Técnica Simulada — Java Backend Junior (Entrevista Laboral).pdf
├── prd-code-quality-validations.md
├── prd-seed-data-loading.md
└── system-artifsct.md
```

## Enunciado base

El archivo [Prueba Técnica Simulada — Java Backend Junior (Entrevista Laboral).pdf](docs/Prueba%20T%C3%A9cnica%20Simulada%20%E2%80%94%20Java%20Backend%20Junior%20%28Entrevista%20Laboral%29.pdf) contiene el enunciado original del ejercicio. La aplicación implementa el CRUD de productos por consola solicitado allí, manteniendo la solución sin frameworks ni base de datos.

## Ejecución

Compilar el proyecto:

```bash
mvn -q compile
```

Ejecutar la aplicación:

```bash
java -cp target/classes org.example.Main
```

Al iniciar, la aplicación intenta cargar automáticamente los productos de prueba desde:

```text
data/products.csv
```

También puedes indicar otro archivo CSV como argumento:

```bash
java -cp target/classes org.example.Main data/products.csv
```

## Datos de prueba

El archivo [data/products.csv](data/products.csv) contiene productos iniciales para probar el sistema sin tener que ingresarlos manualmente uno por uno.

Formato esperado:

```csv
name,price,stock
Mazda 3,25000.0,300
```

Reglas del archivo:

- La primera línea debe ser el encabezado `name,price,stock`.
- El nombre no puede estar vacío.
- El precio debe ser numérico y mayor que cero.
- El stock debe ser numérico y mayor o igual que cero.
- El separador de columnas es coma.
- Los nombres pueden contener espacios y números, por ejemplo `Mazda 3`.

## Menú de la aplicación

```text
1. Ver todos los productos.
2. Buscar producto por id.
3. Crear nuevo producto.
4. Actualizar un producto.
5. Eliminar un producto.
6. Salir
```

## Validaciones aplicadas

- Si se espera un número y el usuario ingresa texto, la aplicación vuelve a pedir el dato.
- El ID debe ser mayor que cero.
- El nombre del producto es obligatorio.
- El precio debe ser mayor que cero.
- El stock puede ser cero, pero no negativo.
- Si un producto no existe, la aplicación muestra un mensaje y continúa funcionando.
- Una opción inválida del menú no detiene la aplicación.

## Documentación técnica

- [Enunciado de la prueba técnica](docs/Prueba%20T%C3%A9cnica%20Simulada%20%E2%80%94%20Java%20Backend%20Junior%20%28Entrevista%20Laboral%29.pdf)
- [System artifact](docs/system-artifsct.md)
- [PRD de validaciones y calidad](docs/prd-code-quality-validations.md)
- [PRD de carga de datos de prueba](docs/prd-seed-data-loading.md)
