package com.bookpalace.converter;

import com.bookpalace.dto.request.CustomerSaveRequest;
import com.bookpalace.model.Customer;
import com.bookpalace.model.enums.AccountType;
import com.bookpalace.util.HashUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerConverter {

    public static Customer toCustomer(CustomerSaveRequest request) {
        String hashedPassword = HashUtil.generate(request.getPassword());

        Customer customer = new Customer(request.getName(), request.getSurname(), request.getEmail(), hashedPassword);
        customer.setAccountType(AccountType.STANDARD);
        customer.setIsActive(Boolean.TRUE);
        return customer;
    }
}
