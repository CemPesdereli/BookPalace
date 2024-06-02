package com.bookpalace.controller;

import com.bookpalace.dto.request.ProductSaveRequest;
import com.bookpalace.dto.response.CustomerResponse;
import com.bookpalace.dto.response.GenericResponse;
import com.bookpalace.dto.response.ProductResponse;
import com.bookpalace.dto.response.PublisherResponse;
import com.bookpalace.model.Product;
import com.bookpalace.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public GenericResponse<ProductResponse> save(@RequestBody ProductSaveRequest request) {
        //productService.save(request);
        return GenericResponse.success(productService.save(request));
    }

    @GetMapping
    public GenericResponse<Set<ProductResponse>> getAll() {
        return GenericResponse.success(productService.getAll());
    }

    @GetMapping("/books")
    public GenericResponse<Set<ProductResponse>> getAllBooks() {
        return GenericResponse.success(productService.getAllBooks());
    }

    @GetMapping("/journals")
    public GenericResponse<Set<ProductResponse>> getAllJournals() {
        return GenericResponse.success(productService.getAllJournals());
    }

    @GetMapping("/publishers/{publisherId}")
    public GenericResponse<Set<ProductResponse>>  getProductsByPublisherId(@PathVariable Long publisherId) {

       // CustomerResponse customerResponse = productService.getProductsByPublisherId(publisherId);

        return GenericResponse.success(productService.getProductsByPublisherId(publisherId));
    }

    @GetMapping("/publishers/{name}")
    public GenericResponse<Set<ProductResponse>>  getProductsByPublisherName(@PathVariable String name) {
        //CustomerResponse customerResponse = customerService.getByEmail(email);
        return GenericResponse.success(productService.getProductsByPublisherName(name));
    }

}
