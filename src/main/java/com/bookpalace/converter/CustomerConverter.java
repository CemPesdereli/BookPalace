package com.bookpalace.converter;

import com.bookpalace.dto.request.CustomerSaveRequest;
import com.bookpalace.dto.response.CustomerResponse;
import com.bookpalace.dto.response.ProductResponse;
import com.bookpalace.model.Customer;
import com.bookpalace.model.Product;
import com.bookpalace.model.enums.AccountType;
import com.bookpalace.util.HashUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerConverter {

    public static Customer toCustomer(CustomerSaveRequest request) {
        String hashedPassword = HashUtil.generate(request.getPassword());

        Customer customer = new Customer(request.getName(), request.getSurname(), request.getEmail(), hashedPassword);
        customer.setAccountType(AccountType.STANDARD);
        customer.setIsActive(Boolean.TRUE);
        customer.setCredit(5000);
        return customer;
    }
    public static CustomerResponse toResponse(Customer customer) {
        return CustomerResponse.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .email(customer.getEmail())
                .credit(customer.getCredit())
                .phoneNumber(customer.getPhoneNumber())
                .accountType(customer.getAccountType())
                .build();
    }

    public static List<CustomerResponse> toResponse(List<Customer> customers) {
        return customers
                .stream()
                .map(CustomerConverter::toResponse)
                .collect(Collectors.toList());
    }
}
