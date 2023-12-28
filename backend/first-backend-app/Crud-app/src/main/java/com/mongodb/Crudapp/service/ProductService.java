package com.mongodb.Crudapp.service;

import java.util.List;

import com.mongodb.Crudapp.entity.Product;
import com.mongodb.Crudapp.exception.NoProductFoundException;

public interface ProductService {
    
    String saveProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long productId);
    String deleteProductById(Long productId);
    Product updateProduct(Long prodId, Product product) throws NoProductFoundException;
    
}
