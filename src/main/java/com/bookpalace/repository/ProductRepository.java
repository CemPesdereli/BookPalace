package com.bookpalace.repository;

import com.bookpalace.model.Product;
import com.bookpalace.model.enums.ProductType;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private Set<Product> productSet = new HashSet<>();

    public void save(Product product) {
        product.setCategory(product.getCategory().toUpperCase());
        productSet.add(product);
    }

    public Set<Product> getAll() {
        return productSet;
    }

    public Set<Product> getAllBooks() {
        return productSet.stream()
                .filter(product -> product.getCategory().equals(ProductType.BOOK.name()))
                .collect(Collectors.toSet());
    }

    public Set<Product> getAllJournals() {
        return productSet.stream()
                .filter(product -> product.getCategory().equals(ProductType.JOURNAL.name()))
                .collect(Collectors.toSet());
    }
    public Set<Product> getProductsByPublisherId(Long id) {
        return productSet.stream()
                .filter(product -> product.getPublisher().getId()==id)
                .collect(Collectors.toSet());
    }

    public Set<Product> getProductsByPublisherName(String name) {
        return productSet.stream()
                .filter(product -> product.getPublisher().getName().equals(name))
                .collect(Collectors.toSet());
    }




    public Optional<Product> getProductByName(String name) {

        return getAll().stream()
                .filter(product -> product.getName().equals(name))
                .findFirst();
    }
}
