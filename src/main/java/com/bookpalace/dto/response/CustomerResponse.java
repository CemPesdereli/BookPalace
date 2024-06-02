package com.bookpalace.dto.response;

import com.bookpalace.model.Address;
import com.bookpalace.model.enums.AccountType;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {


    private String name;
    private String surname;
    private String email;
    private Integer credit;
    private String phoneNumber;
    private AccountType accountType;

}
