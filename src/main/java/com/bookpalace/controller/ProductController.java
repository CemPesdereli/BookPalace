package com.bookpalace.controller;

import com.bookpalace.dto.request.ProductSaveRequest;
import com.bookpalace.dto.response.GenericResponse;
import com.bookpalace.dto.response.ProductResponse;
import com.bookpalace.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ProductSaveRequest request) {
        productService.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public GenericResponse<Set<ProductResponse>> getAll() {
        return GenericResponse.success(productService.getAll());
    }

}
