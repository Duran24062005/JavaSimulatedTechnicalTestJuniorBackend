package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import org.example.model.ProductModel;
import org.example.repository.ProductRepository;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ProductRepository repo = new ProductRepository();

        // System.out.println(repo.getProducts());

        ProductModel coche = new ProductModel();
        coche.setId(1);
        coche.setName("Mazda");
        coche.setPrice(1200.0);
        coche.setStock(5);

        repo.createNewProduct(coche);

        for (ProductModel data : repo.getProducts()) {
            System.out.println(data.getName());
        }
    }
}