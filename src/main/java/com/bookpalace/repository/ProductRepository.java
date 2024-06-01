package com.bookpalace.repository;

import com.bookpalace.model.Product;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class ProductRepository {
    private Set<Product> productSet = new HashSet<>();

    public void save(Product product) {
        productSet.add(product);
    }

    public Set<Product> getAll() {
        return productSet;
    }
}
