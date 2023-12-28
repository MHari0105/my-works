package com.mongodb.Crudapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.Crudapp.entity.Product;
import com.mongodb.Crudapp.exception.NoProductFoundException;
import com.mongodb.Crudapp.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository prodRepo;

    @Override
    public String saveProduct(Product product) {
        Long productId = product.getProdId();
        Optional<Product> existingId = prodRepo.findById(productId);

        if (existingId.isPresent())
            return "Product alraedy exists";

        else {
            prodRepo.save(product);
            return "Product " + productId + " saved successfully";
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return prodRepo.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
       return prodRepo.findById(productId).get();
    }

    @Override
    public String deleteProductById(Long productId) {
        boolean deleted = false;
        if (prodRepo.existsById(productId)) {
            prodRepo.deleteById(productId);
            deleted = true;
        }
        return "Product id " + productId + " deleted status : " + deleted;
    }

    @Override
    public Product updateProduct(Long prodId, Product product) throws NoProductFoundException { 
        Optional<Product> existing = prodRepo.findById(prodId);
        Product existValue = null;

        if (existing.isPresent()) {
            existValue = existing.get();
            existValue.setName(product.getName());
            existValue.setProdCode(product.getProdCode());
            existValue.setPrice(product.getPrice());
            existValue.setDesc(product.getDesc());

            prodRepo.save(existValue);
        }

        if (existValue == null) {
            throw new NoProductFoundException("Product Not Found");
        }

        return existValue;
    }
}
