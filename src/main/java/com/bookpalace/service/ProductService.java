package com.bookpalace.service;

import com.bookpalace.converter.ProductConverter;
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

    public void save(ProductSaveRequest request) {

        Optional<Publisher> optionalPublisher = publisherService.getByName(request.getPublisherName());

        if (optionalPublisher.isEmpty()) {
            log.error("publisher bulamadım : {}", request.getPublisherName());
            throw new RuntimeException("publisher bulamadım");
        }

        Product product = ProductConverter.toProduct(request, optionalPublisher.get());

        productRepository.save(product);

        log.info("product created : {}", product.toString());
    }

    public Set<ProductResponse> getAll() {
        return ProductConverter.toResponse(productRepository.getAll());
    }

}
