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
        if (id <= 0 || this.productList.isEmpty()) {
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
        productData.setId(getNextProductId());
        this.productList.add(productData);
        return true;
    }

    public boolean updateProductPrice(int productId, double price){
        ProductModel product = this.getProductById(productId);
        if (product == null || price <= 0) {
            return false;
        }

        product.setPrice(price);
        return true;
    }

    public boolean updateProductStock(int productId, int stock){
        ProductModel product = this.getProductById(productId);
        if (product == null || stock < 0) {
            return false;
        }

        product.setStock(stock);
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

    private int getNextProductId() {
        int maxId = 0;

        for (ProductModel product : productList) {
            if (product.getId() > maxId) {
                maxId = product.getId();
            }
        }

        return maxId + 1;
    }
}
