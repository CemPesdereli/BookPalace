package com.bookpalace.dto.request;

import com.bookpalace.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {

    private String currentEmail;
    private String desiredEmail;
    private Address address;


}
