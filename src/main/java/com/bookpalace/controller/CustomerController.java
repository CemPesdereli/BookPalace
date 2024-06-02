package com.bookpalace.controller;

import com.bookpalace.dto.request.CustomerChangeAccountTypeRequest;
import com.bookpalace.dto.request.CustomerSaveRequest;
import com.bookpalace.dto.request.CustomerUpdateRequest;
import com.bookpalace.dto.response.CustomerResponse;
import com.bookpalace.dto.response.GenericResponse;
import com.bookpalace.model.Customer;
import com.bookpalace.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public GenericResponse<CustomerResponse> save(@RequestBody CustomerSaveRequest request) {
        //customerService.save(request);
      return GenericResponse.success(customerService.save(request));
    }

    @GetMapping
    public GenericResponse<List<CustomerResponse>> getAll() {

        return GenericResponse.success(customerService.getCustomerList());

    }

    @GetMapping("/{id}")
    public GenericResponse<CustomerResponse> getById(@PathVariable Long id) {

        CustomerResponse customerResponse = customerService.getById(id);

        return GenericResponse.success(customerResponse);
    }

    @GetMapping("/email/{email}")
    public GenericResponse<CustomerResponse> getByEmail(@PathVariable String email) {
        CustomerResponse customerResponse = customerService.getByEmail(email);
        return GenericResponse.success(customerResponse);
    }

    @PutMapping
    public GenericResponse<CustomerResponse> updateCustomer(@RequestBody CustomerUpdateRequest request) {

        return GenericResponse.success(customerService.update(request));


    }
    @PostMapping("/changeAcoountType")
    public GenericResponse<CustomerResponse> changeAccountType(@RequestBody CustomerChangeAccountTypeRequest request) {
        //customerService.save(request);
        return GenericResponse.success(customerService.changeAccountTypeByCredit(request));
    }

}
