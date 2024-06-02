package com.bookpalace.model;


import com.bookpalace.model.enums.AccountType;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Customer {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Integer credit;
    private String phoneNumber;
    private List<Order> orderList;
    private Address address;
    private Boolean isActive;
    private AccountType accountType;

    public Customer(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}
