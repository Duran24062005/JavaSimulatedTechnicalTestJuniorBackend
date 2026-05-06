package org.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.example.model.ProductModel;

public class ProductRepository {

    private final List<ProductModel> productList;

    public ProductRepository() {
        this.productList = new ArrayList<>();
    }


    public List<ProductModel> getProducts(){
        if (productList.isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(productList);
    }

    public ProductModel getProductById(int id){
        if (this.productList.isEmpty() || productList.size() < id) {
            return null;
        }

        for (ProductModel product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public boolean createNewProduct(ProductModel productData){
        if (productData == null) {
            return false;
        }
        this.productList.add(productData);
        return true;
    }

    public boolean updateProduct(int productId, ProductModel newProductData){
        ProductModel product = this.getProductById(productId);
        if (product == null) {
            return false;
        }

        product.setName(newProductData.getName());
        product.setPrice(newProductData.getPrice());
        product.setStock(newProductData.getStock());
        return true;
    }
    
    public boolean deleteProduct(int productId){
        ProductModel product = this.getProductById(productId);
        if (product == null) {
            return false;
        }

        this.productList.remove(product);
        return true;
    }
}
