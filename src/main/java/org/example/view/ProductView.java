package org.example.view;

import java.util.List;
import java.util.Scanner;

import org.example.model.ProductModel;

public class ProductView {

    private Scanner scanner;

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
                6.Salir
                =====================================================
                """);
                int opt = getInt(">");


            return opt;
    }

    public int getInt(String message){
        try {
            System.out.println(message);
            return scanner.nextInt();
        } catch (Exception e) {
                // TODO: handle exception
            System.out.println("Error" + e.getMessage());
        }
        return 0;
    }

    public void showProducts(List<ProductModel> data){
        if (data.isEmpty()) {
            System.out.println("\n No hay productos registrados.\n");
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
        }
        System.out.println("=================================================");
        System.out.println("Id: " + product.getId());
        System.out.println("Nombre: " + product.getName());
        System.out.println("Precio: " + product.getPrice());
        System.out.println("Stock: " + product.getStock());
        
    }

    public ProductModel getProductData(String message){
        System.out.println(message);
        System.out.println("Nombre: ");
        String name = scanner.next();
        System.out.println("Precio: ");
        Double price = scanner.nextDouble();
        System.out.println("Stock: ");
        int stock = scanner.nextInt();

        scanner.nextLine();

        ProductModel product = new ProductModel();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;
    }

    public ProductModel update(){

        System.out.println("""
                ¿Que deseas actualizar, stock o precio?
                1. stock.
                2. precio.
                3. salir.
                """);
        int ints = getInt(">");
        ProductModel prd = new ProductModel();

        switch (ints) {
            case 1:
                System.out.println("Precio: ");
                double price = scanner.nextDouble();
                scanner.nextLine();
                prd.setPrice(price);
                return prd;

            case 2:
                System.out.println("Stock: ");
                int stock = scanner.nextInt();
                scanner.nextLine();
                prd.setStock(stock);
                return prd;
            
            case 3:
                break;
                default:
                    throw new AssertionError();
                }
        return prd;
    }
}
