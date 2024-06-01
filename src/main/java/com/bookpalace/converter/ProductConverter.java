package com.bookpalace.converter;

import com.bookpalace.dto.request.ProductSaveRequest;
import com.bookpalace.dto.response.ProductResponse;
import com.bookpalace.model.Product;
import com.bookpalace.model.Publisher;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConverter {

    public static Product toProduct(ProductSaveRequest request, Publisher publisher) {
        return Product.builder()
                .name(request.getName())
                .amount(request.getAmount())
                .description(request.getDescription())
                .publisher(publisher)
                .build();
    }

    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .amount(product.getAmount())
                .name(product.getName())
                .description(product.getDescription())
                .publisherName(product.getPublisher().getName())
                .build();
    }

    public static Set<ProductResponse> toResponse(Set<Product> products) {
        return products
                .stream()
                .map(ProductConverter::toResponse)
                .collect(Collectors.toSet());
    }
}
