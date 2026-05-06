package org.example.view;

import java.util.List;
import java.util.Scanner;

import org.example.model.ProductModel;

public class ProductView {

 private final Scanner scanner;

    public ProductView(){
        this.scanner = new Scanner(System.in);
    }

    public int menu(){
        System.out.println("""
                =====================================================
                = Bienvenido al sistema de gestion de productos ATS =
                =====================================================
                Elija una opción:
                1. Ver todos los productos.
                2. Buscar producto por id.
                3. Crear nuevo producto.
                4. Actualizar un producto.
                5. Eliminar un producto.
                6. Salir
                =====================================================
                """);
        return getInt(">");
    }

    public int getInt(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido.");
            }
        }
    }


    public double getDouble(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine().trim();

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un precio valido.");
            }
        }
    }

    public int getPositiveInt(String message) {
        while (true) {
            int value = getInt(message);

            if (value > 0) {
                return value;
            }

            System.out.println("El valor debe ser mayor que cero.");
        }
    }

    public int getNonNegativeInt(String message) {
        while (true) {
            int value = getInt(message);

            if (value >= 0) {
                return value;
            }

            System.out.println("El valor no puede ser negativo.");
        }
    }

    public double getPositiveDouble(String message) {
        while (true) {
            double value = getDouble(message);

            if (value > 0) {
                return value;
            }

            System.out.println("El valor debe ser mayor que cero.");
        }
    }

    public String getRequiredText(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("El texto no puede estar vacio.");
        }
    }


    public void showProducts(List<ProductModel> data){
        if (data.isEmpty()) {
            System.out.println("\n No hay productos registrados.\n");
            return;
        }
        System.out.println("=================================================");
        System.out.println("\n Productos registrados.");
        for (ProductModel product : data) {
            System.out.println("=================================================");
            System.out.println("Id: " + product.getId());
            System.out.println("Nombre: " + product.getName());
            System.out.println("Precio: " + product.getPrice());
            System.out.println("Stock: " + product.getStock());
            System.out.println("\n");
        }
    }

    public void showProduct(ProductModel product){
        if (product == null) {
            System.out.println("\n No hay producto registrado con ese id.\n");
            return;
        }
        System.out.println("=================================================");
        System.out.println("Id: " + product.getId());
        System.out.println("Nombre: " + product.getName());
        System.out.println("Precio: " + product.getPrice());
        System.out.println("Stock: " + product.getStock());
        
    }

    public ProductModel getProductData(String message){
        System.out.println(message);
        String name = getRequiredText("Nombre:");
        double price = getPositiveDouble("Precio:");
        int stock = getNonNegativeInt("Stock:");

        ProductModel product = new ProductModel();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;
    }

    public int getUpdateOption(){
        System.out.println("""
                ¿Que deseas actualizar, stock o precio?
                1. precio.
                2. stock.
                3. salir.
                """);
        return getInt(">");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
