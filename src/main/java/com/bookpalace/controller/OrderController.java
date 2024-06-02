package com.bookpalace.controller;


import com.bookpalace.dto.request.OrderSaveRequest;
import com.bookpalace.dto.request.ProductSaveRequest;
import com.bookpalace.dto.response.GenericResponse;
import com.bookpalace.dto.response.OrderResponse;
import com.bookpalace.dto.response.ProductResponse;
import com.bookpalace.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public GenericResponse<OrderResponse> save(@RequestBody OrderSaveRequest request) {
        //productService.save(request);
        return GenericResponse.success(orderService.save(request));
    }

    @GetMapping
    public GenericResponse<List<OrderResponse>> getAll() {
        return GenericResponse.success(orderService.getOrders());
    }

}
