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

    public boolean createNewProduct(ProductModel productData){
        if (productData == null) {
            return false;
        }
        this.productList.add(productData);
        return true;
    }
    
}
