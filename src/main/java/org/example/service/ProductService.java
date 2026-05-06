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
        int id = view.getPositiveInt("Busqueda de producto por id.");
        ProductModel prod = productRepo.getProductById(id);
        view.showProduct(prod);
    }

    public void createProduct(){
        ProductModel newProduct = this.view.getProductData("== Crear nuevo producto ==");

        if (!isValidProduct(newProduct)) {
            view.showMessage("Los datos del producto no son validos. \n");
            return;
        }

        boolean newPrd = productRepo.createNewProduct(newProduct);
        if (!newPrd) {
            view.showMessage("Error al crear el producto. \n");
            return;
        }
        view.showMessage("Producto creado exitosamente. \n");
    }

    public void updateProduct(){
        int id = this.view.getPositiveInt("Ingrese el ID del producto a actualizar.");
        ProductModel product = productRepo.getProductById(id);

        if (product == null) {
            view.showMessage("Error al actualizar el producto o no existe. \n");
            return;
        }

        int option = view.getUpdateOption();

        switch (option) {
            case 1:
                updateProductPrice(id);
                break;
            case 2:
                updateProductStock(id);
                break;
            case 3:
                view.showMessage("Actualizacion cancelada. \n");
                break;
            default:
                view.showMessage("Opcion no valida. \n");
        }
    }

    public void deleteProduct(){
        int id = this.view.getPositiveInt("Ingrese el ID del producto a eliminar.");
        boolean deleted = productRepo.deleteProduct(id);
        if (!deleted) {
            view.showMessage("Error al eliminar el producto o no existe. \n");
            return;
        }
        view.showMessage("Producto eliminado exitosamente. \n");
    }

    private void updateProductPrice(int id) {
        double price = view.getPositiveDouble("Precio:");
        boolean updated = productRepo.updateProductPrice(id, price);

        if (!updated) {
            view.showMessage("Error al actualizar el precio del producto. \n");
            return;
        }

        view.showMessage("Precio actualizado exitosamente. \n");
    }

    private void updateProductStock(int id) {
        int stock = view.getNonNegativeInt("Stock:");
        boolean updated = productRepo.updateProductStock(id, stock);

        if (!updated) {
            view.showMessage("Error al actualizar el stock del producto. \n");
            return;
        }

        view.showMessage("Stock actualizado exitosamente. \n");
    }

    private boolean isValidProduct(ProductModel product) {
        return product != null
                && product.getName() != null
                && !product.getName().isBlank()
                && product.getPrice() > 0
                && product.getStock() >= 0;
    }
}
