package com.bookpalace.service;

import com.bookpalace.converter.ProductConverter;
import com.bookpalace.exception.KitapYurdumException;
import com.bookpalace.repository.ProductRepository;
import com.bookpalace.dto.request.ProductSaveRequest;
import com.bookpalace.dto.response.ProductResponse;
import com.bookpalace.model.Product;
import com.bookpalace.model.Publisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final PublisherService publisherService;

    public ProductResponse save(ProductSaveRequest request) {

        Optional<Publisher> optionalPublisher = publisherService.getByName(request.getPublisherName());

        if (optionalPublisher.isEmpty()) {
            log.error("publisher bulamadım : {}", request.getPublisherName());
            throw new KitapYurdumException("publisher bulamadım");
        }

        Product product = ProductConverter.toProduct(request, optionalPublisher.get());

        productRepository.save(product);

        log.info("product created : {}", product.toString());
        return ProductConverter.toResponse(product);
    }

    public Set<ProductResponse> getAll() {
        return ProductConverter.toResponse(productRepository.getAll());
    }

    public Set<ProductResponse> getAllBooks() {
        return ProductConverter.toResponse(productRepository.getAllBooks());
    }

    public Set<ProductResponse> getAllJournals() {
        return ProductConverter.toResponse(productRepository.getAllJournals());
    }

    public Set<ProductResponse> getProductsByPublisherId(Long id) {


        return ProductConverter.toResponse(productRepository.getProductsByPublisherId(id));
    }

    public Set<ProductResponse> getProductsByPublisherName(String name) {


        return ProductConverter.toResponse(productRepository.getProductsByPublisherName(name));
    }

    public Product getProductByName(String name) {

        Optional<Product> optionalProduct= productRepository.getProductByName(name);

        if(optionalProduct.isEmpty()) {
            log.error("product bulamadım : {}", name);
            throw new KitapYurdumException("product bulamadım");
        }

        return optionalProduct.get();


    }
}
