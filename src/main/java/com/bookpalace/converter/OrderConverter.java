package com.bookpalace.converter;

import com.bookpalace.dto.request.OrderSaveRequest;
import com.bookpalace.dto.request.PublisherSaveRequest;
import com.bookpalace.dto.response.OrderResponse;
import com.bookpalace.dto.response.PublisherResponse;
import com.bookpalace.model.*;
import com.bookpalace.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class OrderConverter {

    public static Order toOrder(Customer customer, List<Product> productList) {

        String orderCode = generateUniqueOrderCode();

        BigDecimal totalPrice = productList.stream()
                .map(Product::getAmount) // directly map to BigDecimal object
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        customer.setCredit((totalPrice.intValue()/100)*2+(customer.getCredit()));

        return Order.builder()
                .createDate(LocalDateTime.now())
                .productList(productList)
                .orderCode(orderCode)
                .receipt(new Receipt(LocalDateTime.now(),totalPrice.doubleValue()))
                .orderStatus(OrderStatus.INITIAL)

                .build();
    }

    public static OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .createDate(order.getCreateDate())
                .productList(order.getProductList())
                .orderCode(order.getOrderCode())
                .receipt(order.getReceipt())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    public static List<OrderResponse> toResponse(List<Order> orders) {
        return orders
                .stream()
                .map(OrderConverter::toResponse)
                .collect(Collectors.toList());
    }

    private static String generateUniqueOrderCode() {
        StringBuilder codeBuilder = new StringBuilder();


        long currentTimeMillis = Instant.now().toEpochMilli();


        int timePart = (int) (currentTimeMillis % 10000);


        int combinedValue = (1000 + timePart) % 10000;


        String combinedString = String.valueOf(combinedValue);


        int randomStringLength = 6;
        String randomString = generateRandomString(randomStringLength);


        codeBuilder.append(combinedString);
        codeBuilder.append(randomString);

        return codeBuilder.toString();
    }

    private static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }

}

