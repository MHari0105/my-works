package com.mongodb.Crudapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.Crudapp.entity.Product;

public interface ProductRepository extends MongoRepository<Product, Long> {
   
}
