package org.example.service;

import org.example.model.ProductModel;
import org.example.repository.ProductRepository;
import org.example.view.ProductView;

public class ProductService {
    private final ProductRepository productRepo;
    private final ProductView view;

    public ProductService(ProductRepository repo, ProductView view) {
        this.productRepo = repo;
        this.view = view;
    }

    public void showProducts(){
        view.showProducts(productRepo.getProducts());
    }

    public void showProduct(){
        int id = view.getInt("Busqueda de producto por id.");
        ProductModel prod = productRepo.getProductById(id);
        view.showProduct(prod);
    }

    public void createProduct(){
        ProductModel newProduct = this.view.getProductData("== Crear nuevo producto ==");
        boolean newPrd = productRepo.createNewProduct(newProduct);
        if (!newPrd) {
            System.out.println("Error al crear el producto. \n");
        }
        System.out.println("Producto creado exitosamente. \n");
    }

    public void updateProduct(){
        int id = this.view.getInt("Ingrese el ID del producto a actualizar.");
        ProductModel dato = view.update();
        boolean deleted = productRepo.updateProduct(id, dato);
        if (!deleted) {
            System.out.println("Error al eliminar el producto o no existe. \n");
        }
        System.out.println("Producto eliminado exitosamente. \n");
    }

    public void deleteProduct(){
        int id = this.view.getInt("Ingrese el ID del producto a eliminar.");
        boolean deleted = productRepo.deleteProduct(id);
        if (!deleted) {
            System.out.println("Error al eliminar el producto o no existe. \n");
        }
        System.out.println("Producto eliminado exitosamente. \n");
    }

    

}
