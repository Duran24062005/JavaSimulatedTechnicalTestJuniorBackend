package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.example.view.ProductView;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ProductRepository repo = new ProductRepository();
        ProductView view = new ProductView();
        ProductService service = new ProductService(repo, view);

        boolean system = true;
        while (system) {
            switch (view.menu()) {
                case 1:
                    service.showProducts();
                    break;
                case 2:
                    service.showProduct();
                    break;
                case 3:
                    service.createProduct();
                    break;
                case 4:
                    service.updateProduct();
                    break;
                case 5:
                    service.deleteProduct();
                    break;
                case 6:
                    system = false;
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}