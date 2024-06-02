package com.bookpalace.service;

import com.bookpalace.converter.CustomerConverter;
import com.bookpalace.converter.OrderConverter;
import com.bookpalace.dto.request.OrderSaveRequest;
import com.bookpalace.dto.response.CustomerResponse;
import com.bookpalace.dto.response.OrderResponse;
import com.bookpalace.model.Customer;
import com.bookpalace.model.Order;
import com.bookpalace.model.Product;
import com.bookpalace.model.Receipt;
import com.bookpalace.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CustomerService customerService;


    public OrderResponse save(OrderSaveRequest request) {

        List<String> requestList = request.getProductNameList();
        List<Product> productList = new ArrayList<>();

        for (String productName : requestList) {

            productList.add(productService.getProductByName(productName));

        }
        Customer customer = customerService.getCustomerByEmail(request.getEmail());

        Order order = OrderConverter.toOrder(customer, productList);

        orderRepository.save(order);

        log.info("order created : {}", order.toString());

        return OrderConverter.toResponse(order);


    }

    public List<OrderResponse> getOrders() {

        return OrderConverter.toResponse(orderRepository.findAll());
    }


}







